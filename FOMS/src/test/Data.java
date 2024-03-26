package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Logger;

/**
 * [WIP] This class facilitates reading and writing to csv files
 */
public class Data {
    private String path = "FOMS/src/test/data.csv";
    private ArrayList<String> rowData;
    
    public Data(){}

    
    /** 
     * @return ArrayList<String>
     */
    public ArrayList<String> readFromFile() {
        Logger.info("Reading data from path:" + path);
        rowData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line != null && !line.isEmpty()) {
                    rowData.add(line);
                }
            }
        } catch (IOException e) {
            Logger.error("Error parsing file from path:" + path);
        }
        return rowData;
    }
    
    /** 
     * @param FileWriter(path)
     */
    public void writeToFile(String s) { //TODO: input should not be just a string, pass in obj that spits out csv format text to write
        Logger.info("Writing data to " + path);
        // write to csv file
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(s + "\n");
        } catch (IOException e) {
            Logger.error("Error adding rows to " + path);
        }
    }
}
