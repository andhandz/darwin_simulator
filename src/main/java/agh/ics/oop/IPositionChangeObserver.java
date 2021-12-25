package agh.ics.oop;

public interface IPositionChangeObserver {
    /**
     * remove old position of object and add new to hashmap
     * @param oldPosition is the old position of animal
     * @param newPosition is the new position of animal
     */
    void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition);
}
