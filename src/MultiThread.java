import java.util.ArrayList;
import java.util.Random;

public class MultiThread implements Runnable {
    // saves Thread Name for memory management
    private String threadName;
    // seed from main method
    private long seed;
    // Start and End values for x and z coords, + radius + current x and z
    private int xStart, zStart, xEnd, zEnd, radius, currentX, currentZ;
    // Maximum Slime Chunks
    private int[] maxSlimeChunks;
    // list of all slime chunks
    private ArrayList<ArrayList<Boolean>> slimeChunkList;
    // Status of the thread
    private boolean dead;
    // List of perimeters with same amount
    private ArrayList<int[]> allMaxSlimeChunks;

    /**
     * Constructor
     * @param xStart
     * @param xEnd
     * @param zStart
     * @param zEnd
     */
    public MultiThread(int xStart, int xEnd, int zStart, int zEnd) {
        // Values from parameter
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.zStart = zStart;
        this.zEnd = zEnd;

        // Values from Main method
        this.seed = Main.getSeed();
        this.radius = Main.getRadius();

        // Standart Values
        this.slimeChunkList = new ArrayList<>();
        this.dead = false;
        this.currentX = xStart;
        this.currentZ = zStart;
        this.maxSlimeChunks = new int[3];
        this.maxSlimeChunks[0] = 0;
        this.maxSlimeChunks[1] = 0;
        this.maxSlimeChunks[2] = 0;
    }

    @Override
    public void run() {
        this.threadName = Thread.currentThread().getName();
        this.findSlimeChunks();
    }

    @Override
    public String toString() {
        return (Colors.cyan + this.threadName + Colors.reset + " found a perimeter with " + Colors.cyan + maxSlimeChunks[0] + Colors.reset + " Slime Chunks at X: " + Colors.cyan + this.maxSlimeChunks[1] + Colors.reset + " Z: " + Colors.cyan + this.maxSlimeChunks[2] + Colors.reset + ", currently at X: " + Colors.cyan + this.currentX + Colors.reset + " Z: " + Colors.cyan + this.currentZ + Colors.reset);
    }

    /**
     * Calulates if the chunk is a slime chunk -
     * from minecraft source code
     * @param xPosition
     * @param zPosition
     * @return true, if slime chunk
     */
    public boolean getSlimeChunk(int xPosition, int zPosition) {
        return new Random(seed + (int) (xPosition * xPosition * 0x4c1906) + (int) (xPosition * 0x5ac0db) + (int) (zPosition * zPosition) * 0x4307a7L + (int) (zPosition * 0x5f24f) ^ 0x3ad8025fL).nextInt(10) == 0;
    }
    
    /**
     * Starts a loop that runs until the x Position reaches the max
     * x coord, counted up each time
     */
    private void findSlimeChunks() {
        for (int x = this.xStart; x < this.xEnd; x++) {
            this.currentX = x;

            this.slimeChunkList.add(new ArrayList<>());

            this.zLoop(x);

            if (this.slimeChunkList.size() >= 18) {
                this.slimeChunkList.remove(0);
            }

            x++;
        }

        // Text when thread is finished
        System.out.println("----- " + this.threadName + " is finished -----");
        // Changes alive state
        this.dead = true;
    }

    /**
     * Starts a loop going over all z coords
     * calculates and saves all chunks
     * z coord, counted up every time
     */
    private void zLoop(int x) {
        for (int z = this.zStart; z < this.zEnd; z++) {
            this.currentZ = z;


            int currentZArraySize = this.slimeChunkList.get((this.slimeChunkList.size() - 1)).size();

            this.slimeChunkList.get((this.slimeChunkList.size() - 1)).add(this.getSlimeChunk(x, z));
            
            if (this.slimeChunkList.size() >= 18 && currentZArraySize >= 18 && currentZArraySize < (radius - 18)) {
                this.calculatePerimeter(x, z);
            }
        }
    }

    /**
     * Calculates the slime chunks in the perimeter
     */
    private void calculatePerimeter(int xPos, int zPos) {
        int zIndex = this.slimeChunkList.get((this.slimeChunkList.size() - 1)).size() - 1;
        int currentSlimeChunkCount = 0;

        for (int x = -7; x < 9; x++) {
            for (int z = -7; z < 9; z++) {
                boolean currentState = this.slimeChunkList.get((8 + x)).get((zIndex + z));

                if(currentState) currentSlimeChunkCount++;
            }

            if(x > 6 && currentSlimeChunkCount < 18) {
                return;
            }
        }
        
        if(currentSlimeChunkCount > this.maxSlimeChunks[0] && currentSlimeChunkCount > 50) {
            this.maxSlimeChunks[0] = currentSlimeChunkCount;
            this.maxSlimeChunks[1] = (xPos - 16);
            this.maxSlimeChunks[2] = zPos;
            System.out.println(Colors.reset + "New max with " + Colors.cyan + currentSlimeChunkCount + Colors.reset + " at X: " + Colors.cyan + this.maxSlimeChunks[1] + Colors.reset + " Z: " + Colors.cyan + this.maxSlimeChunks[2] + Colors.reset + " --- " + Colors.cyan + this.threadName + Colors.reset);

            ArrayList<int[]> empty = new ArrayList<>();
            allMaxSlimeChunks = empty;
        }

        if(currentSlimeChunkCount == this.maxSlimeChunks[0] && currentSlimeChunkCount > 50) {
            int[] currentChunk = {currentSlimeChunkCount, (xPos - 16), zPos};

            this.allMaxSlimeChunks.add(currentChunk);

            System.out.println(Colors.reset + "New same as max with " + Colors.cyan + currentSlimeChunkCount + Colors.reset + " at X: " + Colors.cyan + this.maxSlimeChunks[1] + Colors.reset + " Z: " + Colors.cyan + this.maxSlimeChunks[2] + Colors.reset + " --- " + Colors.cyan + this.threadName + Colors.reset);

            this.updateDiscordRP(this.maxSlimeChunks[0], this.maxSlimeChunks[1], this.maxSlimeChunks[2]);
        }
    }

    /**
     * Updates the DiscordRP
     * @param xPos
     * @param zPos
     */
    private void updateDiscordRP(int maxCount, int xPos, int zPos) {
        if(this.threadName.equals("Thread-3")) {
            String str = "Best Perimeter with " + maxCount + " Slime Chunks at X: " + xPos + " Z: " + zPos;
            Main.getDiscordRP().updateDetails(str);
        }
    }

    /**
     * Getter for alive
     * @return alive
     */
    public boolean isDead() {
        return this.dead;
    }

    public int getCurrentX() {
        return this.currentX;
    }

    public int getCurrentZ() {
        return this.currentZ;
    }

    public int[] getMaxSlimeChunkCount() {
        return this.maxSlimeChunks;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public ArrayList<int[]> getAllMax() {
        return this.allMaxSlimeChunks;
    }
}
