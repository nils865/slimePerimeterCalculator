package nils865.Finder;

import java.util.Random;

public class Chunk {
    private final int x;
    private final int z;
    private final boolean slimeChunk;

    public Chunk(int x, int z) {
        this.x = x;
        this.z = z;

        this.slimeChunk = isSlimeChunk(12345L);
    }

    public boolean isSlimeChunk(long seed) {
        Random rnd = new Random(seed +
                (long) (this.x * this.x * 0x4c1906) +
                (long) (this.x * 0x5ac0db) +
                (long) (this.z * this.z) * 0x4307a7L +
                (long) (this.z * 0x5f24f) ^ 0x3ad8025f);
    
        return rnd.nextInt(10) == 0;
    }
}
