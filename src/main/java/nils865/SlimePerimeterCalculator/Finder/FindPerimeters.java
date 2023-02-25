package nils865.SlimePerimeterCalculator.Finder;

import nils865.SlimePerimeterCalculator.Main;
import nils865.SlimePerimeterCalculator.Utils.NumberFormatting;

public class FindPerimeters {
    private int radius;

    public FindPerimeters(int radius) {
        this.radius = radius;
    }

    public int searchForSlimeChunks() {
        int slimeChunkCount = 0;
        
        Chunk bestChunk = null;
        int highestCount = 0;

        for (int x = -this.radius; x < this.radius; x++) {
            for (int z = -this.radius; z < this.radius; z++) {
                Chunk currentChunk = new Chunk(x, z);

                int perimeterSize = calcPerimeter(currentChunk);

                if (perimeterSize > highestCount) {
                    bestChunk = currentChunk;
                    highestCount = perimeterSize;
                }

                if (currentChunk.isSlimeChunk()) slimeChunkCount++;
            }
        }

        System.out.println("Slime Chunk Count: " + NumberFormatting.beautifyNumber(slimeChunkCount));
        System.out.println("The best Chunk is at X: " + bestChunk.getX() + " Z: " + bestChunk.getZ() + " and has " + highestCount + " Slime Chunks in the Perimeter Radius!");

        return slimeChunkCount;
    }

    public int calcPerimeter(Chunk chunk) {
        int slimeChunkCount = 0;

        if (chunk.isSlimeChunk()) slimeChunkCount++;

        for (int x = -Main.perimeterRadius; x < Main.perimeterRadius; x++) {
            for (int z = -Main.perimeterRadius; z < Main.perimeterRadius; z++) {
                if (x == 0 && z == 0) continue;

                Chunk currentChunk = new Chunk(x, z);

                if (currentChunk.isSlimeChunk()) slimeChunkCount++;
            }
        }

        return slimeChunkCount;
    }
}
