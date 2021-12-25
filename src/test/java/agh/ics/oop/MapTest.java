package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void placeInitialAnimals() {
        IWorldMap map = new Map(10,5,13,10,3,10);
        assertTrue(map.getAnimals().size()>0);
    }

    @Test
    void placeInitialPlants() {
        IWorldMap map = new Map(10,10,13,10,99,10);
        assertEquals(1, map.openSpots(new Vector2d(0,0), new Vector2d(10,10)).size());
    }

    @Test
    void placeAnimal() {
        IWorldMap map = new Map(10,10,13,10,99,10);
        Animal animal = new Animal(10,new Vector2d(5,5));
        int x=0;
        if(map.getAnimals().get(new Vector2d(5,5))!=null){
            x = map.getAnimals().get(new Vector2d(5, 5)).size();
        }
        map.placeAnimal(animal,animal.getPosition());
        assertEquals(x+1,map.getAnimals().get(animal.getPosition()).size());
    }

    @Test
    void placePlant() {
        IWorldMap map = new Map(10,10,13,10,0,10);
        Plant plant = new Plant(new Vector2d(2,2),10);
        map.placePlant(plant);
        assertEquals(plant,map.PlantAt(new Vector2d(2,2)));
    }

    @Test
    void getJungleLowerLeft() {
        IWorldMap map = new Map(10,5,13,10,0,10);
        assertEquals(new Vector2d(2,2),map.getJungleLowerLeft());
    }

    @Test
    void getJungleUpperRight() {
        IWorldMap map = new Map(10,5,13,10,0,10);
        assertEquals(new Vector2d(7,7), map.getJungleUpperRight());
    }

    @Test
    void removePlant() {
        IWorldMap map = new Map(10,10,13,10,0,10);
        Plant plant = new Plant(new Vector2d(2,2),10);
        map.placePlant(plant);
        assertEquals(plant,map.PlantAt(new Vector2d(2,2)));
        map.removePlant(plant);
        assertNull(map.PlantAt(plant.getPosition()));
    }

    @Test
    void removeAnimal() {
        IWorldMap map = new Map(10,10,13,10,99,10);
        Animal animal = new Animal(10,new Vector2d(5,5));
        Animal animal1 = new Animal(10,new Vector2d(5,5));
        int x=0;
        if(map.getAnimals().get(new Vector2d(5,5))!=null){
            x = map.getAnimals().get(new Vector2d(5, 5)).size();
        }
        map.placeAnimal(animal,animal.getPosition());
        map.placeAnimal(animal1, new Vector2d(5,5));
        assertEquals(x+2,map.getAnimals().get(animal.getPosition()).size());
        map.removeAnimal(animal,new Vector2d(5,5));
        assertEquals(x+1,map.getAnimals().get(animal.getPosition()).size());
    }

    @Test
    void animalAt() {
        IWorldMap map = new Map(1,1,13,10,0,10);
        assertEquals(13,map.AnimalAt(new Vector2d(0,0)).size());
    }

    @Test
    void isOccupiedByPlant() {
        IWorldMap map = new Map(10,10,13,10,0,10);
        Plant plant = new Plant(new Vector2d(3,3),10);
        map.placePlant(plant);
        assertTrue(map.isOccupiedByPlant(new Vector2d(3,3)));
        assertFalse(map.isOccupiedByPlant(new Vector2d(2,3)));
    }

    @Test
    void isOccupiedByAnimal() {
        IWorldMap map = new Map(10,10,13,10,0,10);
        Animal animal = new Animal(10,new Vector2d(3,3));
        map.placeAnimal(animal,new Vector2d(3,3));
        assertTrue(map.isOccupiedByAnimal(new Vector2d(3,3)));
    }

}