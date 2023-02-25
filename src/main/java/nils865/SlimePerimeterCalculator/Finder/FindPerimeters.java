package nils865.SlimePerimeterCalculator.Finder;

import nils865.SlimePerimeterCalculator.Utils.NumberFormatting;

public class FindPerimeters {
    private int radius;

    public FindPerimeters(int radius) {
        this.radius = radius;
    }

    public int searchForSlimeChunks() {
        int slimeChunkCount = 0;

        for (int x = -this.radius; x < this.radius; x++) {
            for (int z = -this.radius; z < this.radius; z++) {
                Chunk currentChunk = new Chunk(x, z);

                if (currentChunk.isSlimeChunk()) {
                    slimeChunkCount++;
                }
            }
        }

        System.out.println("Slime Chunk Count: " + NumberFormatting.beautifyNumber(slimeChunkCount));

        return slimeChunkCount;
    }
}
