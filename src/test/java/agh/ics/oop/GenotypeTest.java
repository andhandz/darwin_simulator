package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenotypeTest {
    private final Genotype genotype = new Genotype();

    @Test
    void generateGenotype() {
        int[] g = genotype.getGens();
        for(int gen : g) assertTrue(gen>=0 && gen<=7);
        assertEquals(32,g.length);
    }

    @Test
    void genotypeForChild() {
        Genotype genotype1 = new Genotype();
        int strongerCounter = 20;
        Genotype child = genotype.genotypeForChild(genotype1,strongerCounter);
        int[] g1 = genotype.getGens();
        int[] g2 = genotype1.getGens();
        int[] c = child.getGens();
        for(int i=0;i<32;i++){
            if(i<20) {
                assertEquals(g1[i],c[i]);
            }
            else{
                assertEquals(g2[i],c[i]);
            }
        }
    }

    @Test
    void generateGensForChild() {
        int[] g1 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] g2 = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
        int counter1 = 16;
        int counter2 = 25;
        int[] g = new int[32];
        int[] gg = new int[32];
        int[] expected1 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
        int[] expected2 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3};
        genotype.generateGensForChild(g,g1,g2,counter1);
        genotype.generateGensForChild(gg,g1,g2,counter2);
        for(int i=0;i<32;i++) {
            assertEquals(expected1[i], g[i]);
            assertEquals(expected2[i], gg[i]);
        }
    }
}