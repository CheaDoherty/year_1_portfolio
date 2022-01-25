/**
 *
 * @author Connor McCann & Liam C Doherty
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateFile {
    public static String[] ReadFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Read file line by line
            List<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            return lines.toArray(new String[lines.size()]);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    // Method for writing data to files
    public static void WriteToFile(String fileName, String fileContent, Boolean append) {
        try {
            FileWriter myWriter = new FileWriter(fileName, append);
            // Write the content
            myWriter.write(fileContent);
            // Close the file
            myWriter.close();
            // Inform the user it has worked
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

