package agh.ics.oop;

public class Statistics {
    private int avgEnergy;
    private int animalCounter;
    private int plantCounter;
    private int day;
    private int avgLivingTime;
    private int animalsDeaths;
    private int childrenCounter;
    private int deathAnimalsDays;

    public Statistics(){
         avgEnergy=0;
         animalCounter=0;
         plantCounter=0;
         day=0;
         avgLivingTime=0;
         animalsDeaths=0;
         childrenCounter=0;
         deathAnimalsDays=0;
    }

    public int getAvgEnergy() {
        return avgEnergy;
    }

    public void setAvgEnergy(int avgEnergy) {
        this.avgEnergy = avgEnergy;
    }

    public int getAnimalCounter() {
        return animalCounter;
    }

    public void setAnimalCounter(int animalCounter) {
        this.animalCounter = animalCounter;
    }

    public int getPlantCounter() {
        return plantCounter;
    }

    public void setPlantCounter(int plantCounter) {
        this.plantCounter = plantCounter;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getAvgLivingTime() {
        return avgLivingTime;
    }

    public void setAvgLivingTime(int avgLivingTime) {
        this.avgLivingTime = avgLivingTime;
    }

    public int getAnimalsDeaths() {
        return animalsDeaths;
    }

    public void setAnimalsDeaths(int animalsDeaths) {
        this.animalsDeaths = animalsDeaths;
    }

    public int getChildrenCounter() {
        return childrenCounter;
    }

    public void setChildrenCounter(int childrenCounter) {
        this.childrenCounter = childrenCounter;
    }

    public int getDeathAnimalsDays() {
        return deathAnimalsDays;
    }

    public void setDeathAnimalsDays(int deathAnimalsDays) {
        this.deathAnimalsDays = deathAnimalsDays;
    }
}
