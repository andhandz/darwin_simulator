package agh.ics.oop;

import java.util.*;

public class Map implements IWorldMap,IPositionChangeObserver,IEnergyChangeObserver{
    private final LinkedHashMap<Vector2d, List<Animal>> animals = new LinkedHashMap<>();
    private final LinkedHashMap <Vector2d, Plant> plants = new LinkedHashMap<>();
    private final int mapSize;
    private final int jungleSize;

    @Override
    public LinkedHashMap<Vector2d, List<Animal>> getAnimals() {
        return animals;
    }

    @Override
    public LinkedHashMap<Vector2d, Plant> getPlants() {
        return plants;
    }

    public Map(int mapSize, int jungleSize, int animalAmount, int startEnergy, int plantsAmount, int plantEnergy){
        this.mapSize=mapSize;
        this.jungleSize=jungleSize;
        if(jungleSize*2>mapSize){
            throw new IllegalArgumentException("Jungle is too big!");
        }
        if(jungleSize*10<mapSize){
            throw new IllegalArgumentException("Jungle is too small!");
        }
        placeInitialAnimals(animalAmount,startEnergy);
        placeInitialPlants(plantsAmount,plantEnergy);
    }

    @Override
    public void placeInitialAnimals(int amount, int energy){
        if(amount<10){
            throw new IllegalArgumentException("Not enough animals on start!");
        }
        Random draw = new Random();
        for(int i=0;i<amount;i++){
            int x = draw.nextInt(jungleSize);
            int y = draw.nextInt(jungleSize);
            Vector2d cords = getJungleLowerLeft().add(new Vector2d(x,y));
            placeAnimal(new Animal(energy,cords),cords);
        }
    }

    @Override
    public ArrayList<Vector2d> openSpots(Vector2d lowerLeft, Vector2d upperRight){
        ArrayList<Vector2d> openSpots = new ArrayList<>();
        for (int i= lowerLeft.x;i< upperRight.x;i++){
            for(int j = lowerLeft.y;j< upperRight.y;j++){
                Vector2d spot = new Vector2d(i,j);
                if(!isOccupiedByPlant(spot)){
                    openSpots.add(spot);
                }
            }
        }
        return openSpots;
    }

    @Override
    public void placeInitialPlants(int amount, int energy){
        int i=0;
        Random draw = new Random();
        ArrayList<Vector2d> jungleOpenSpots = openSpots(getJungleLowerLeft(),getJungleUpperRight());
        while(i<amount/2 && jungleOpenSpots.size()>0){
            jungleOpenSpots = openSpots(getJungleLowerLeft(),getJungleUpperRight());
            choosingPlantSpotProcess(jungleOpenSpots,draw,energy);
            i++;
        }
        while(i<amount){
            ArrayList<Vector2d> openSpots = openSpots(new Vector2d(0,0),new Vector2d(mapSize,mapSize));
            if(openSpots.size()==0){
                throw new IllegalArgumentException("Map is too small!") ;
            }
            choosingPlantSpotProcess(openSpots,draw,energy);
            i++;
        }
    }

    @Override
    public void choosingPlantSpotProcess(ArrayList<Vector2d> spots, Random draw, int energy){
        int rnd = draw.nextInt(spots.size());
        Vector2d newPlace = spots.get(rnd);
        placePlant(new Plant(newPlace,energy));
    }

    @Override
    public void placeAnimal(Animal animal, Vector2d position) {
        List<Animal> animalsAt = AnimalAt(position);
        if(animalsAt == null){
            animalsAt = new ArrayList<>();
        }
        animalsAt.add(animal);
        animals.put(position,animalsAt);
        animal.addObserver(this);
        animal.addEnergyObserver(this);
    }

    @Override
    public void placePlant(Plant plant){
        plants.put(plant.getPosition(),plant);
    }

    @Override
    public Vector2d getJungleLowerLeft(){
        int cord = (mapSize-jungleSize)/2;
        return new Vector2d(cord,cord);
    }

    @Override
    public Vector2d getJungleUpperRight(){
        return getJungleLowerLeft().add(new Vector2d(jungleSize,jungleSize));
    }

    @Override
    public void removePlant(Plant plant){
        plants.remove(plant.getPosition());
    }

    @Override
    public void removeAnimal(Animal animal, Vector2d position){
        List <Animal> animalsAt = AnimalAt(position);
        animalsAt.remove(animal);
        animals.remove(position);
        animals.put(position,animalsAt);
        animal.removeObserver(this);
        animal.removeEnergyObserver(this);
    }

    @Override
    public List<Animal> AnimalAt(Vector2d position) {
        if(animals.get(position) == null){
            return null;
        }
        return new ArrayList<>(animals.get(position));
    }

    @Override
    public Plant PlantAt(Vector2d position){
        return plants.get(position);
    }

    @Override
    public boolean isOccupiedByPlant(Vector2d position) {
        return PlantAt(position)!=null;
    }

    @Override
    public boolean isOccupiedByAnimal(Vector2d position){
        return AnimalAt(position)!=null;
    }

    @Override
    public void updateAnimalPosition(Animal animal){
        Random draw = new Random();
        removeAnimal(animal,animal.getPosition());
        animal.move(animal.getGenotype().getGens()[draw.nextInt(32)],mapSize);
        placeAnimal(animal,animal.getPosition());
    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        removeAnimal(animal,oldPosition);
        placeAnimal(animal,newPosition);
    }

    @Override
    public void energyChanged(Animal animal) {
        removeAnimal(animal,animal.getPosition());
        placeAnimal(animal,animal.getPosition());
    }

    public int getMapSize() {
        return mapSize;
    }
}
