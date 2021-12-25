package agh.ics.oop;

public class Plant implements IMapElement{
    private final Vector2d position;
    private final int energy;

    public Plant(Vector2d position, int energy){
        this.position=position;
        this.energy=energy;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public String toString(){
        return "*";
    }
}
