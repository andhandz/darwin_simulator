package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void getPosition() {
        Animal animal = new Animal(10,new Vector2d(10,99));
        assertEquals(animal.getPosition(),new Vector2d(10,99));
    }

    @Test
    void getEnergy() {
        Animal animal = new Animal(10,new Vector2d(10,99));
        assertEquals(10,animal.getEnergy());
    }

    @Test
    void setEnergy() {
        Animal animal = new Animal(10,new Vector2d(10,99));
        animal.setEnergy(15);
        assertEquals(15,animal.getEnergy());
    }

    @Test
    void move() {
    Animal animal = new Animal(10,new Vector2d(0,0));
    animal.move(0,100);
    assertEquals(new Vector2d(0,1),animal.getPosition());
    animal.move(3,100);
    animal.move(0,100);
    assertEquals(MapDirection.ES, animal.getOrientation());
    assertEquals(new Vector2d(1,0),animal.getPosition());
    animal.move(4,100);
    assertEquals(new Vector2d(0,1),animal.getPosition());
    animal.move(7,100);
    animal.move(0,100);
    assertEquals(new Vector2d(99,1),animal.getPosition());
    animal.move(4,100);
    assertEquals(new Vector2d(0,1),animal.getPosition());
    }

    @Test
    void bornAnimal() {
        Animal animal1 = new Animal(100,new Vector2d(0,0));
        Animal animal2 = new Animal(100,new Vector2d(0,0));
        Animal child = animal1.bornAnimal(animal2,0);
        assertEquals(75,animal1.getEnergy());
        assertEquals(75,animal2.getEnergy());
        assertEquals(50,child.getEnergy());
        assertEquals(new Vector2d(0,0),child.getPosition());
    }
}