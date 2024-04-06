package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class that reads and writes data with CSV files
 */
public class SerialiseCSV {   
    /**
     * Default constructor
     */
    public SerialiseCSV(){}

    /**
     * To read CSV file specified by the file path
     * @param csvFilePath
     * @return rows of data as a list of string
     */
    public static ArrayList<String> readCSV(String csvFilePath) {
        Logger.info("Reading branch data from path:" + csvFilePath);
        ArrayList<String> dataReadFromCSV = new ArrayList<>();
        Path path = Paths.get(csvFilePath);   
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line != null && !line.isEmpty()) {
                    dataReadFromCSV.add(line);
                }
            }
        } catch (IOException e) {
            Logger.error("Error parsing file from path:" + csvFilePath + "; Error: " + e.getMessage());
        }
        return dataReadFromCSV;
    }
    
    /**
     * To write to CSV file specified by the CSV file path
     * @param s
     * @param csvFilePath
     */
    public static void writeToCSV(String s, String csvFilePath) {
        try {
            Files.createDirectories(Paths.get(csvFilePath).getParent());

            try (FileWriter fw = new FileWriter(csvFilePath, true)) { // true to append
                fw.write(s + "\n");
                fw.flush();
            }
            Logger.info("Data written to file successfully.");
        } catch (IOException e) {
            Logger.error("Error writing to " + csvFilePath + ": " + e.getMessage());
        }
    }
    /**
     * To append data to a CSV file specified by the CSV file path
     * @param s The data to be appended
     * @param csvFilePath The file path of the CSV file
     */
    public static void appendToCSV(String s, String csvFilePath) {
        Logger.info("Appending data to " + csvFilePath);
        try (FileWriter fw = new FileWriter(csvFilePath, true)) {
            fw.append(s + "\n");
        } catch (IOException e) {
            Logger.error("Error appending to " + csvFilePath);
        }
    }

    /**
     * Replaces a specified value in a specified column of a CSV file.
     *
     * @param searchString The value to search for in the specified column.
     * @param columnIndex  The index of the column in which the value will be replaced.
     * @param newValue      The new value to replace the old value with.
     * @param csvFilePath   The file path of the CSV file.
     * @return true if the value is found and replaced successfully, false otherwise.
     */
    public static boolean replaceColumnValue(String searchString, int columnIndex, String newValue, String csvFilePath) {
        ArrayList<String> lines = readCSV(csvFilePath);
        boolean found = false;

        // Iterate through each line in the CSV file
        for (int i = 0; i < lines.size(); i++) {
            String[] columns = lines.get(i).split(",");
            // Check if the line has enough columns and the specified value is found in the specified column
            if (columns.length > columnIndex && columns[columnIndex].equals(searchString)) {
                // Replace the old value with the new value
                columns[columnIndex] = newValue;
                // Update the line with the modified columns
                lines.set(i, String.join(",", columns));
                found = true;
            }
        }

        // If the value is found and replaced successfully, write the modified data back to the CSV file
        if (found) {
            try (FileWriter fw = new FileWriter(csvFilePath)) {
                // Write each line of the modified data back to the CSV file
                for (String line : lines) {
                    fw.write(line + "\n");
                }
                return true;
            } catch (IOException e) {
                Logger.error("Error replacing value in " + csvFilePath + ": " + e.getMessage());
                return false;
            }
        } else {
            Logger.error("Value not found in " + csvFilePath);
            return false;
        }
    }

}
