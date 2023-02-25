package nils865.SlimePerimeterCalculator;

import java.util.Scanner;

import nils865.SlimePerimeterCalculator.Finder.FindPerimeters;
import nils865.nilsColor.Color;

public class Main {
    private static long seed;
    private static boolean debug = false;
    private static final int perimeterSize = 16;

    public static long getSeed() {
        return Main.seed;
    }

    public static boolean getDebug() {
        return Main.debug;
    }

    public static void main(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "debug":
                    Main.debug = true;
                    seed = 12345L;
                    break;
            
                default:
                    System.out.println( Color.fg.red("Argument \"") +
                                        Color.fg.cyan(arg) +
                                        Color.fg.red("\" not found"));
                    return;
            }
        }

        System.out.println("\nWelcome to the " + Color.fg.green("Slime Perimeter Calculator"));
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        if (!debug) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter the World seed: ");

                String input = scanner.nextLine();

                if (input.equals("exit")) {
                    scanner.close();
                    return;
                }

                try {
                    if (Long.parseLong(input) == 0) throw new NumberFormatException();

                    seed = Long.parseLong(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\n\tInvalid input. Please enter a valid long value.\n");
                }
            }

            System.out.println();

            scanner.close();
        }

        FindPerimeters search = new FindPerimeters(10000);

        search.searchForSlimeChunks();
    }
}
