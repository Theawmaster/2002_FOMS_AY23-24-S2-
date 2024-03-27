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
    public ArrayList<String> readCSV(String csvFilePath) {
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
    public void writeToCSV(String s, String csvFilePath) {
        Logger.info("Writing data to " + csvFilePath);
        try (FileWriter fw = new FileWriter(csvFilePath)) {
            fw.write(s + "\n");
        } catch (IOException e) {
            Logger.error("Error writing to " + csvFilePath);
        }
    }
}
