package nils865.SlimePerimeterCalculator.Finder;

import java.util.Random;

import nils865.SlimePerimeterCalculator.Main;

public class Chunk {
    private final int x;
    private final int z;
    private final boolean slimeChunk;

    public Chunk(int x, int z) {
        this.x = x;
        this.z = z;

        this.slimeChunk = calcSlimeChunk();
    }

    private boolean calcSlimeChunk() {
        Random rnd = new Random(Main.getSeed() +
                (long) (this.x * this.x * 0x4c1906) +
                (long) (this.x * 0x5ac0db) +
                (long) (this.z * this.z) * 0x4307a7L +
                (long) (this.z * 0x5f24f) ^ 0x3ad8025f);
    
        return rnd.nextInt(10) == 0;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public boolean isSlimeChunk() {
        return this.slimeChunk;
    }
}
