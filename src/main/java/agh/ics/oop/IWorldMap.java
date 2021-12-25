package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public interface IWorldMap {
    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     */
   void placeAnimal(Animal animal, Vector2d position);
    /**
     * Return animals at a given position.
     *
     * @param position The position of the animals.
     * @return Animals or null if the position is not occupied.
     */
    List<Animal> AnimalAt(Vector2d position);
    /**
     * @param position Position to check.
     * @return True if the position is occupied by plant.
     */
    boolean isOccupiedByPlant(Vector2d position);
    /**
     * Place initial animals on the map before simulation starts.
     *
     * @param amount The number of animals to place on the map.
     * @param energy the value of energy these animals.
     */
    void placeInitialAnimals(int amount, int energy);
    /**
     * @return all not occupied by plant spots in lowerleft to upperright range
     */
    ArrayList<Vector2d> openSpots(Vector2d lowerLeft, Vector2d upperRight);
    /**
     * Place initial plants on the map before simulation starts.
     *
     * @param amount The number of plants to place on the map.
     * @param energy the value of energy these plants.
     */
   void placeInitialPlants(int amount, int energy);
    /**
     * Draw spot for new plant from open spots.
     *
     * @param spots the list of open spots.
     * @param draw the method to draw.
     * @param energy the energy of new plants.
     */
    void choosingPlantSpotProcess(ArrayList<Vector2d> spots, Random draw, int energy);
    /**
     * Place a plant on the map.
     *
     * @param plant The plant to place on the map.
     */
    void placePlant(Plant plant);
    /**
     * @return lower left corner of the jungle.
     */
    Vector2d getJungleLowerLeft();
    /**
     * @return upper right corner of the jungle.
     */
    Vector2d getJungleUpperRight();
    /**
     * Remove plant from the map.
     *
     * @param plant The plant to remove from the map.
     */
    void removePlant(Plant plant);
    /**
     * Remove animal from the map.
     *
     * @param animal The animal to remove from the map.
     */
    void removeAnimal(Animal animal, Vector2d position);
    /**
     * @return plant at this position or null if position is empty
     */
    Plant PlantAt(Vector2d position);
    /**
     * @return true if this position is occupied by animal.
     */
    boolean isOccupiedByAnimal(Vector2d position);
    /**
     * @return list of animals at theirs spots.
     */
    LinkedHashMap<Vector2d, List<Animal>> getAnimals();
    /**
     * @return plants on the map.
     */
    LinkedHashMap<Vector2d, Plant> getPlants();
    /**
     * Change position animal, which moved on.
     */
    void updateAnimalPosition(Animal animal);
    /**
     * @return map size.
     */
    int getMapSize();
}
