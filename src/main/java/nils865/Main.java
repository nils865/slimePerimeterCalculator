package nils865;

public class Main {
    public static void main(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "debug":
                    break;
            
                default:
                    System.out.println("Argument \"" + arg + "\" not found");
                    return;
            }
        }

        System.out.println("Hello World!");
    }
}
