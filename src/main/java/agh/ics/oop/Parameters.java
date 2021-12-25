package agh.ics.oop;

public class Parameters {
    public final int mapSize;
    public final int jungleSize;
    public final int animalAmount;
    public final int startEnergy;
    public final int plantsAmount;
    public final int plantEnergy;
    public final int minimalEnergyForChild;

    public Parameters(int[] params){
        this.mapSize=params[0];
        this.jungleSize=params[1];
        this.animalAmount=params[2];
        this.startEnergy=params[3];
        this.plantsAmount=params[4];
        this.plantEnergy=params[5];
        this.minimalEnergyForChild=params[6];
    }
}
