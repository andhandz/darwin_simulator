package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void dailyPlants() {

        SimulationEngine simulator = new SimulationEngine(new Parameters(new int[]{10, 10, 10, 10, 0, 10, 10}));
        simulator.dailyPlants();
        assertEquals(98,simulator.getMap().openSpots(new Vector2d(0,0), new Vector2d(10,10)).size());

    }

    @Test
    void dailyFunreal() {
        SimulationEngine simulator = new SimulationEngine(new Parameters(new int[]{1000, 10, 10, 10, 0, 10, 10}));
        Animal animal = new Animal(0,new Vector2d(3,3));
        Animal animal1 = new Animal(1,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal1,new Vector2d(3,3));
        int x = simulator.getMap().getAnimals().get(new Vector2d(3,3)).size();
        simulator.dailyFunreal();
//        assertEquals(x-1, simulator.getMap().getAnimals().get(new Vector2d(3,3)).size());
//        simulator.getMap().removeAnimal(animal1,new Vector2d(3,3));
//        assertNull(simulator.getMap().AnimalAt(new Vector2d(3,3)));
    }

    @Test
    void fightForPlant() {
        SimulationEngine simulator = new SimulationEngine(new Parameters(new int[]{10, 10, 10, 10, 0, 10, 10}));
        Animal animal = new Animal(20,new Vector2d(3,3));
        Animal animal1 = new Animal(30,new Vector2d(3,3));
        Animal animal2 = new Animal(30,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal1,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal2,new Vector2d(3,3));
        Plant plant = new Plant( new Vector2d(3,3),10);
        simulator.getMap().placePlant(plant);
        simulator.fightForPlant();
        assertEquals(20,animal.getEnergy());
        assertEquals(35,animal1.getEnergy());
        assertEquals(35,animal2.getEnergy());
        assertNull(simulator.getMap().PlantAt(new Vector2d(3,3)));
    }

    @Test
    void hormoneAttack() {
        SimulationEngine simulator = new SimulationEngine(new Parameters(new int[]{1000, 10, 10, 10, 0, 10, 15}));
        Animal animal = new Animal(20,new Vector2d(3,3));
        Animal animal1 = new Animal(30,new Vector2d(3,3));
        Animal animal4 = new Animal(16,new Vector2d(3,3));
        Animal animal5 = new Animal(16,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal1,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal4,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal5,new Vector2d(3,3));
        simulator.hormoneAttack();
        assertEquals(5,simulator.getMap().AnimalAt(new Vector2d(3,3)).size());
        assertEquals(15,animal.getEnergy());
        Animal animal2 = new Animal(20,new Vector2d(2,2));
        Animal animal3 = new Animal(14,new Vector2d(2,2));
        simulator.getMap().placeAnimal(animal2,new Vector2d(2,2));
        simulator.getMap().placeAnimal(animal3,new Vector2d(2,2));
        assertEquals(2,simulator.getMap().AnimalAt(new Vector2d(2,2)).size());
        assertEquals(20,animal2.getEnergy());
    }

    @Test
    void dailyMove() {
        SimulationEngine simulator = new SimulationEngine(new Parameters(new int[]{10, 10, 10, 10, 0, 10, 10}));
        Animal animal = new Animal(20,new Vector2d(3,3));
        simulator.getMap().placeAnimal(animal,new Vector2d(3,3));
        simulator.dailyMove();
        assertEquals(19,animal.getEnergy());
    }
}