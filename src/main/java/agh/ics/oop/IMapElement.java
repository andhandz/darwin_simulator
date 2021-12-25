package agh.ics.oop;

public interface IMapElement {
    /**
     *
     * @return current position of the object
     */
    Vector2d getPosition();
    /**
     *
     * @return energy of the object
     */
    int getEnergy();
    /**
     *
     * @return symbol of Animal/Plant on the map
     */
    String toString();
}
