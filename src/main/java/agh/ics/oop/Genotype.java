package agh.ics.oop;

import java.util.Random;

public class Genotype {
    private final int[] gens;

    public void generateGenotype(){
        Random draw = new Random();
        for(int i=0;i<this.gens.length;i++){
            int gen = draw.nextInt(8);
            this.gens[i]=gen;
        }
    }

    public Genotype(){
        this.gens = new int[32];
        generateGenotype();
    }

    public Genotype(int[] gens){
        this.gens=gens;
    }

    public int[] getGens(){
        return this.gens;
    }

    public Genotype genotypeForChild(Genotype other, int strongerAnimalGensCounter){
        int weakerAnimalGensCounter = this.gens.length - strongerAnimalGensCounter;
        Random draw = new Random();
        int side = draw.nextInt(2);
        int[] gens = new int[32];
        if(side==0){
            generateGensForChild(gens,this.gens,other.gens,strongerAnimalGensCounter);
        }
        else{
            generateGensForChild(gens, other.gens, this.gens,weakerAnimalGensCounter);
        }
        return new Genotype(gens);
    }

    public void generateGensForChild(int[] gens, int[] first, int[] second, int counter){
        int j=0;
        while(j<counter){
            gens[j]=first[j];
            j++;
        }
        while(j<first.length){
            gens[j]=second[j];
            j++;
        }
    }
}
