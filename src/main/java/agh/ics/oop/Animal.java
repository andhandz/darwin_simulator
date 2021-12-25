package agh.ics.oop;

import java.util.ArrayList;

public class Animal implements IMapElement{
    private Vector2d position;
    private MapDirection orientation;
    private final Genotype genotype;
    private int energy;
    private final ArrayList<IPositionChangeObserver> positionObservers = new ArrayList<>();
    private final ArrayList<IEnergyChangeObserver> energyObservers = new ArrayList<>();
    private final int dayOfBorn;

    public MapDirection getOrientation() {
        return orientation;
    }

    public Animal(int startEnergy, Vector2d initalPosition ){
        this.dayOfBorn=0;
        this.energy=startEnergy;
        this.position=initalPosition;
        this.orientation=MapDirection.N;
        this.genotype=new Genotype();
    }

    public Animal(int startEnergy, Vector2d initalPosition, Genotype genotype, int dayOfBorn){
        this.dayOfBorn=dayOfBorn;
        this.genotype = genotype;
        this.energy=startEnergy;
        this.position=initalPosition;
        this.orientation=MapDirection.N;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy){
        this.energy=energy;
        energyChanged();
    }

    public String toString() {
        return this.orientation.toString();
    }

    public void move(int moveGen, int mapSize){
        for(int i=0;i<moveGen;i++){
            this.orientation=this.orientation.next();
        }
        if(moveGen==0 || moveGen==4){
            Vector2d oldPosition = new Vector2d(this.position.x,this.position.y);
            this.position = this.position.add(this.orientation.toUnitVector());
            this.position = new Vector2d(fixCords(this.position.x,mapSize),fixCords(this.position.y,mapSize));
            positionChanged(oldPosition,this.position);
        }
    }

    public int fixCords(int position,int size){
        if(position>=size){
            position=position%size;
        }
        while(position<0){
            position+=100;
            }
        return position;
    }

    public Animal bornAnimal(Animal other, int dayOfBorn){
        int child_energy= (this.energy+ other.energy)/4;
        this.setEnergy((this.energy*3)/4);
        other.setEnergy((other.energy*3)/4);
        int sum = this.energy + other.energy;
        int StrongerGensCounter = (this.genotype.getGens().length*this.energy)/sum;
        Genotype childGenotype = new Genotype().genotypeForChild(other.getGenotype(),StrongerGensCounter);
        return new Animal(child_energy, this.position, childGenotype, dayOfBorn);
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public void addObserver(IPositionChangeObserver observer){
        positionObservers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        positionObservers.remove(observer);
    }

    public void addEnergyObserver(IEnergyChangeObserver observer){
        energyObservers.add(observer);
    }

    public void removeEnergyObserver(IEnergyChangeObserver observer){
        energyObservers.remove(observer);
    }

    private void positionChanged(Vector2d OldPosition, Vector2d NewPosition) {
        for(IPositionChangeObserver observer: positionObservers){
            observer.positionChanged(this,OldPosition,NewPosition);
        }
    }

    private void energyChanged(){
        for(IEnergyChangeObserver observer: energyObservers){
            observer.energyChanged(this);
        }
    }

    public int getDayOfBorn() {
        return dayOfBorn;
    }
}
