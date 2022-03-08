import java.io.File;
import java.util.ArrayList;

public class Main {
    // World Seed
    private static long seed;
    // Search Radius
    private static int radius;
    // Multithreads
    private static MultiThread mT1, mT2, mT3, mT4;
    // Threads
    private static Thread t1, t2, t3, t4;
    // Settings
    private static int updateTimeInMS;

    /**
     * Main method of the project
     * @param args
     */
    public static void main(String[] args) {
        // Show ascii art banner
        fancyAsciiArt();

        // Get seed from Console Input
        System.out.println("-2049756595");
        System.out.print("Seed: ");
        seed = Long.parseLong(System.console().readLine());

        // Get Radius from Console Input
        System.out.print("Radius: ");
        radius = Integer.parseInt(System.console().readLine());

        // starting the threads
        mT1 = new MultiThread((-radius), 0, (-radius), 0);
        mT2 = new MultiThread((-radius), 0, 0, radius);
        mT3 = new MultiThread(0, radius, (-radius), 0);
        mT4 = new MultiThread(0, radius, 0, radius);

        t1 = new Thread(mT1);
        t2 = new Thread(mT2);
        t3 = new Thread(mT3);
        t4 = new Thread(mT4);
        
        updateTimeInMS = 60000;
        settings();
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        statusUpdate();
    }

    private static void statusUpdate() {
        long startTime = System.currentTimeMillis();
        long lastStatusAtTime = startTime;
        long endTime, currentTime;
        int minutesPassed = 0; 
        boolean running = true;

        while (running) {
            currentTime = System.currentTimeMillis();
            if((currentTime - lastStatusAtTime) >= updateTimeInMS) {
                minutesPassed++;
                System.out.println("\n" + Colors.cyan + (currentTime - startTime) + Colors.reset + " ms or " + Colors.cyan + minutesPassed + Colors.reset + " min have passed");

                // Status of Threads
                System.out.println(mT1);
                System.out.println(mT2);
                System.out.println(mT3);
                System.out.println(mT4);

                // Estimated Time
                int perMinute = mT3.getCurrentX() / minutesPassed;
                int estimatedTime = (radius / perMinute) - minutesPassed;
                System.out.println("Estimated Time: " + Colors.cyan + estimatedTime + Colors.reset + " m or " + Colors.cyan + (estimatedTime / 60) + Colors.reset + " h with " + Colors.cyan + perMinute + Colors.reset + " Chunks per Minute");
                lastStatusAtTime = System.currentTimeMillis();
            }

            if(mT1.isDead() && mT2.isDead() && mT3.isDead() && mT4.isDead()) {
                running = false;
            }
            
            System.out.print("");
        }
        
        endTime = System.currentTimeMillis();
        System.out.println("This took " + (endTime - startTime) + " ms or " + ((endTime - startTime) / 1000) + " s to calculate");
        
        finalOutput();
    }

    /**
     * Final Output
     */
    private static void finalOutput() {
        System.out.println("\n-----------------------");

        MultiThread[] maximums = {mT1, mT2, mT3, mT4};

        File f = FileManager.createFile("SlimeChunks");

        for (int i = 0; i < maximums.length; i++) {
            ArrayList<int[]> mT = maximums[i].getAllMax();
    
            for (int j = 0; j < mT.size(); j++) {
                String str = (mT.get(j)[0] + " Slime Chunks at X: " + mT.get(j)[1] + " Z: " + mT.get(j)[2] + " --- " + maximums[i].getThreadName());

                System.out.println(str);

                String oldFileText = FileManager.readFile(f);

                FileManager.writeToFile(f.getName(), (oldFileText + str + "\n"));
            }
        }
    }

    public static void settings() {
        boolean running = true;

        while (running) {
            System.out.print("Arguments: ");
            String argument = System.console().readLine();

            switch (argument) {
                case "start": 
                    return;

                case "updateTime":
                    updateTimeInMS = Integer.parseInt(System.console().readLine()); 
                    break;

                case "color":
                    boolean hasColoredOutput = Boolean.parseBoolean(System.console().readLine());
                    if(hasColoredOutput) {
                      // Nothing  
                    } else {
                        Colors.turnOff();
                    }
                    break;
            
                default:
                    System.out.println("No argument found");
                    break;
            }
        }
    } 

    /**
     * Generates, Saves and Outputs a Fancy Ascii Art text "Slime Chunks"
     */
    private static void fancyAsciiArt() {
        String[] banner = new String[8];
        
        banner[0] = "================================================================================";
        banner[1] = "  _____  _  _                     _____  _                    _         ";
        banner[2] = " / ____|| |(_)                   / ____|| |                  | |        ";
        banner[3] = "| (___  | | _  _ __ ___    ___  | |     | |__   _   _  _ __  | | __ ___ ";
        banner[4] = " \\___ \\ | || || '_ ` _ \\  / _ \\ | |     | '_ \\ | | | || '_ \\ | |/ // __|";
        banner[5] = " ____) || || || | | | | ||  __/ | |____ | | | || |_| || | | ||   < \\__ \\";
        banner[6] = "|_____/ |_||_||_| |_| |_| \\___|  \\_____||_| |_| \\__,_||_| |_||_|\\_\\|___/";
        banner[7] = "================================================================================";
        System.out.println();
        for (int i = 0; i < banner.length; i++) {
            System.out.println(Colors.green + banner[i]);
        }
        System.out.println(Colors.reset);
    }
    
    // Getter for Seed and Radus
    public static long getSeed() { return seed; }
    public static int getRadius() { return radius; }
}