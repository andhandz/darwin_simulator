package agh.ics.oop;

import agh.ics.oop.gui.IDayObserver;

import java.util.*;

import static java.lang.Thread.sleep;

public class SimulationEngine implements Runnable  {
    private final Parameters params;
    private final IWorldMap map;
    private final ArrayList<IDayObserver> observers = new ArrayList<>();
    private final Statistics stats= new Statistics();
    private int moveDelay = 0;
    private boolean running = true;
    public SimulationEngine(Parameters params){
        this.params=params;
        stats.setAnimalCounter(params.animalAmount);
        stats.setAvgEnergy(params.startEnergy);
        stats.setPlantCounter(params.plantsAmount);
        if(params.minimalEnergyForChild<2){
            throw  new IllegalArgumentException("Set higher value of energy for copulate!");
        }
        this.map= new Map(params.mapSize, params.jungleSize, params.animalAmount, params.startEnergy, params.plantsAmount, params.plantEnergy);
    }

    public void dailyPlants(){
        ArrayList<Vector2d> openSpots = map.openSpots(new Vector2d(0,0), new Vector2d(params.mapSize, params.mapSize ));
        Random draw = new Random();
        if(openSpots.size()!=0) {
            int firstDraw = draw.nextInt(openSpots.size());
            map.placePlant(new Plant(openSpots.get(firstDraw), params.plantEnergy));
            stats.setPlantCounter(stats.getPlantCounter()+1);
        }
        ArrayList<Vector2d> jungleOpenSpots = map.openSpots(map.getJungleLowerLeft(),map.getJungleUpperRight());
        if(jungleOpenSpots.size()!=0) {
            int secondDraw = draw.nextInt(jungleOpenSpots.size());
            map.placePlant(new Plant(jungleOpenSpots.get(secondDraw), params.plantEnergy));
            stats.setPlantCounter(stats.getPlantCounter()+1);
        }
    }

    public void dailyFunreal(){
        for(Vector2d key : map.getAnimals().keySet()){
            map.getAnimals().get(key).removeIf(animal -> animal.getEnergy()==0);
        }
    }

    public void chooseStrongest(List<Animal> animals){
        int max = 0;
        for(Animal animal : animals){
            if(animal.getEnergy()>max){
                max=animal.getEnergy();
            }
        }
        int finalMax = max;
        animals.removeIf(animal -> animal.getEnergy()!= finalMax);
    }

    public void fightForPlant(){
        ArrayList<Plant> plantsToRemove = new ArrayList<>();
        for(Vector2d key: map.getPlants().keySet()){
            if(map.isOccupiedByAnimal(key)){
                List<Animal> strongest = map.AnimalAt(key);
                chooseStrongest(strongest);
                if(strongest.size()!=0) {
                    int energyPerAnimal = map.PlantAt(key).getEnergy() / strongest.size();
                    for (Animal animal : strongest) {
                        animal.setEnergy(animal.getEnergy() + energyPerAnimal);
                    }
                    plantsToRemove.add(map.PlantAt(key));
                    stats.setPlantCounter(stats.getPlantCounter()-1);
                }
            }
        }
        for(Plant plant : plantsToRemove){
            map.removePlant(plant);
        }
    }

    public void potentialLovers(List<Animal> animals){
        animals.removeIf(animal -> animal.getEnergy() < params.minimalEnergyForChild);
    }

    public List<Animal> chooseParents(List<Animal> animals){
        int first = 0;
        int second = 0;
        for(Animal animal : animals){
            if(animal.getEnergy()>first){
                second = first;
                first=animal.getEnergy();
            }
            else if(animal.getEnergy()>second){
                second = animal.getEnergy();
            }
        }
        int j=0;
        List <Animal> parents = new ArrayList<>();
        while(j<2){
            for(Animal animal : animals){
                if(animal.getEnergy() == first){
                    parents.add(animal);
                    j++;
                }
            }
            for(Animal animal : animals){
                if(animal.getEnergy() == second){
                    parents.add(animal);
                    j++;
                }
            }
        }
        return parents;
    }

    public void hormoneAttack(){
        List<List<Animal>> allParents = new ArrayList<>();
        for(Vector2d key : map.getAnimals().keySet()){
            if(map.getAnimals().get(key).size()>=2){
                List <Animal> potentialLovers = map.getAnimals().get(key);
                potentialLovers(potentialLovers);
                if(potentialLovers.size()>=2){
                    potentialLovers=chooseParents(potentialLovers);
                    allParents.add(potentialLovers);
                }
            }
        }
        for(List<Animal> parents : allParents){
            Animal mum = parents.get(0);
            Animal dad = parents.get(1);
            Animal child = mum.bornAnimal(dad, stats.getDay());
            map.placeAnimal(child,child.getPosition());
        }
    }

    public void deathToStats(Animal animal){
        if(animal.getEnergy()==0){
            stats.setAnimalsDeaths(stats.getAnimalsDeaths()+1);
            stats.setDeathAnimalsDays(stats.getDeathAnimalsDays()+(stats.getDay()-animal.getDayOfBorn()));
            stats.setAvgLivingTime(stats.getDeathAnimalsDays()/stats.getAnimalsDeaths());
        }
    }

    public void dailyMove(){
        ArrayList<Animal> animalsToUpdate = new ArrayList<>();
        for(Vector2d key : map.getAnimals().keySet()){
            animalsToUpdate.addAll(map.getAnimals().get(key));
        }
        int j=0;
        int i=0;
        int energySum=0;
        for(Animal animal: animalsToUpdate){
            j++;
            map.updateAnimalPosition(animal);
            animal.setEnergy(animal.getEnergy()-1);
            energySum+=animal.getEnergy();
            deathToStats(animal);
            if(animal.getEnergy()>0 && animal.getDayOfBorn()!=0){
                i++;
            }
        }
        stats.setAnimalCounter(j);
        stats.setChildrenCounter(i);
        if(stats.getAnimalCounter()>0) {
            stats.setAvgEnergy(energySum/stats.getAnimalCounter());
        }
        else{
            stats.setAvgEnergy(0);
        }
    }

   public void day(){
        stats.setDay(stats.getDay()+1);
        dailyPlants();
        dailyMove();
        fightForPlant();
        hormoneAttack();
        dailyFunreal();

   }

    @Override
    public void run() {
        while(map.getPlants().size()!=0){
            try {
                sleep(this.moveDelay);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted -> " + ex);
            }
            if(running) {
                update();
                day();
            }
        }
    }

    public void setMoveDelay(int i) {
        this.moveDelay = i;
    }

    public void addObserver(IDayObserver app) {
        this.observers.add(app);
    }

    private void update(){
        for(IDayObserver observer: observers){
            observer.nextDay();
        }
    }

    public IWorldMap getMap() {
        return map;
    }

    public Statistics getStats() {
        return stats;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
