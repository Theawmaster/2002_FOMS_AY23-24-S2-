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
}
