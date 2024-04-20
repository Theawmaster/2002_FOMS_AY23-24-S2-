package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
        //Logger.info("Reading branch data from path:" + csvFilePath);
        ArrayList<String> dataReadFromCSV = new ArrayList<>();
        
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
    public static boolean appendToCSV(String s, String csvFilePath) {
        try (FileWriter fw = new FileWriter(csvFilePath, true)) { // true to append
            fw.append(s + "\n");
            fw.flush();
            return true; // Return true to indicate success
        } catch (IOException e) {
            Logger.error("Error appending to " + csvFilePath + ": " + e.getMessage());
            return false; // Return false to indicate failure
        }
    }

    /**
     * Replaces a specified value in a specified column of a CSV file.
     *
     * @param searchString The value to search for in column 0.
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
            if (columns.length > columnIndex && columns[0].equals(searchString)) {
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

    /**
     * Deletes a row in the CSV file where the first column matches the deleteKey.
     *
     * @param deleteKey    The value to search for deletion.
     * @param csvFilePath  The path to the CSV file.
     * @return true if a row was deleted successfully, false otherwise.
     * @author @Theawmaster
     */
    public static boolean deleteToCSV(String deleteKey, int deleteAt, String csvFilePath) {
        ArrayList<String> lines = readCSV(csvFilePath);
        ArrayList<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] columns = line.split(",");
            if (columns.length > 0 && columns[deleteAt].trim().equals(deleteKey)) {
                found = true; // Found the row to delete
                continue; // Skip adding this line to updatedLines
            }
            updatedLines.add(line);
        }

        if (found) {
            try {
                // Use Paths.get to handle path correctly and FileWriter for writing back
                Files.write(Paths.get(csvFilePath), updatedLines);
                Logger.info("Row successfully deleted from " + csvFilePath);
                return true;
            } catch (IOException e) {
                Logger.error("Error writing to " + csvFilePath + ": " + e.getMessage());
                return false;
            }
        } else {
            Logger.info("No matching row found to delete in " + csvFilePath);
            return false;
        }
    }

}
