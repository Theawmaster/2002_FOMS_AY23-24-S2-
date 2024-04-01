package utilities;

import java.util.ArrayList;

/**
 * Abstract class for loading of data from CSV
 */
public abstract class LoadData<T> {
    /**
     * The {@link loadedData} contains a list of loaded data from CSV
     */
    protected ArrayList<T> loadedData;
    /**
     * Concrete constructor that loads data into {@link loadedData}
     */
    public LoadData(){
        this.loadedData = loadDatafromCSV();
    }
    /**
     * Abstract method where reading of CSV is called and data is sorted accordingly to corresponding classes
     * @return a list of data loaded from CSV, and saves it in this.loadedData
     */
    public abstract ArrayList<T> loadDatafromCSV();
    /**
     * Concrete getter method to return loadedData
     * @return list of class T in loadedData
     */
    public ArrayList<T> getLoadedData(){
        return this.loadedData;
    };
}
