package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line != null && !line.isEmpty()) {
                    dataReadFromCSV.add(line);
                }
            }
        } catch (IOException e) {
            Logger.error("Error parsing file from path:" + csvFilePath);
        }
        return dataReadFromCSV;
    }
    
    /**
     * To write to CSV file specified by the CSV file path
     * @param s
     * @param csvFilePath
     */
    public static void writeToCSV(String s, String csvFilePath) {
        Logger.info("Writing data to " + csvFilePath);
        try (FileWriter fw = new FileWriter(csvFilePath)) {
            fw.write(s + "\n");
        } catch (IOException e) {
            Logger.error("Error writing to " + csvFilePath);
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
     * Replaces a specified column value in a CSV file after finding the specified "where" value.
     * @param where The value to search for in the first column.
     * @param columnIndex The index of the column to be replaced (index 0 is first column and should not be the thing you're replacing).
     * @param replacementValue The new value to replace the existing value.
     * @param csvFilePath The file path of the CSV file.
     * @return true if successful
     */
    public static boolean replaceColumnValue(String where, int columnIndex, String replacementValue, String csvFilePath) {
        Logger.info("Replacing column in " + csvFilePath);
        ArrayList<String> updatedLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > columnIndex && parts[0].equals(where)) {
                    parts[columnIndex] = replacementValue;
                    line = String.join(",", parts);
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            Logger.error("Error replacing column in " + csvFilePath);
            return false;
        }

        try (FileWriter fw = new FileWriter(csvFilePath)) {
            for (String updatedLine : updatedLines) {
                fw.write(updatedLine + "\n");
            }
            return true;
        } catch (IOException e) {
            Logger.error("Error writing to " + csvFilePath + " while replacing column.");
            return false;
        }
    }
}
