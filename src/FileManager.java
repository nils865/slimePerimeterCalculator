import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class FileManager {
    public static File createFile(String name) {
        try {
            String dt = getDateTime();

            File f = new File(name + "_" + dt + ".scf");

            if (f.createNewFile()) {
                return f;
            }
        } catch (Exception e) {
            // no errors cuz my code is perfect (:
        }

        return null;
    }

    public static void writeToFile(String fileName, String txt) {
        try {
            // Create a new Writer Object
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            // Write the text
            writer.append(txt);
            writer.close();

        // For errors
        } catch (Exception e) {
            // no errors cuz my code is perfect (:
        }
    }

    public static String readFile(File file) {
        ArrayList<String> read = new ArrayList<>();
        String str = "";

        try {
            Scanner s = new Scanner(file);

            while(s.hasNextLine()) {
                String data = s.nextLine();
                read.add(data);
            }

            s.close();
        } catch (Exception e) {
            // No Error cuz im god
        }

        for (int i = 0; i < read.size(); i++) {
            str = str + read.get(i) + "\n";
        }
        
        return str;
    }

    public static String getDateTime() {
        // Set the format
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");  

        // Get the time
        LocalDateTime now = LocalDateTime.now();  

        // Return the time
        return dtf.format(now);
    }
}
