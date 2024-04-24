package utilities;

import java.util.ArrayList;

import entities.Branch;
import entities.MenuItem;

/**
 * The {@link LoadData} class is an abstract class that loads data from CSV files
 * It is inherited by classes that load data for different entities such as Staff, Branch, Order and MenuItem
 * @param <T> The type of data to be loaded
 * @author Siah Yee Long
 */
public abstract class LoadData<T> {
    /**
     * The {@link loadedData} contains a list of loaded data from CSV
     */
    protected ArrayList<T> loadedData;
    /**
     * This constructor creates a LoadData object with a list of Branch objects and a list of MenuItem objects
     * @param branches if information about branches is not needed, it will be passed in as null
     * @param menu if information about menu items is not needed, it will be passed in as null
     */
    protected LoadData(ArrayList<Branch> branches, ArrayList<MenuItem> menu){
        this.loadedData = loadDatafromCSV(branches, menu);
    }
    /**
     * Abstract method where reading of CSV is called and data is sorted accordingly to corresponding classes
     * @return a list of data loaded from CSV, and saves it in this.loadedData
     */
    protected abstract ArrayList<T> loadDatafromCSV(ArrayList<Branch> branches, ArrayList<MenuItem> menu);
    /**
     * Concrete getter method to return loadedData
     * @return list of class T in loadedData
     */
    protected ArrayList<T> getLoadedData(){
        return this.loadedData;
    };
}
