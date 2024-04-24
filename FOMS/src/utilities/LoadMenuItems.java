package utilities;


import java.util.ArrayList;

import constants.FilePaths;
import constants.MealCategory;
import entities.MenuItem;
import entities.Branch;

/**
 * The {@link LoadStaffs} class loads MenuItem data from the CSV database. It inherits from the {@link LoadData} class
 * @author Alvin Aw Yong
 * @author Siah Yee Long
 */
public class LoadMenuItems extends LoadData<MenuItem>{
    /**
     * This constructor creates a LoadMenuItems object with a list of Branch objects and a list of MenuItem objects
     * @param branches all information about branches
     * @param menu null 
     */
    protected LoadMenuItems(ArrayList<Branch> branches, ArrayList<MenuItem> menu) {
        super(branches, menu);
    }
    /**
     * The {@link loadDatafromCSV} method in this class loads in MenuItem data from menu_list.csv
     * @param branches all information about branches
     * @param x null
     * @return a list of MenuItem objects with information loaded in
     */
    @Override
    protected ArrayList<MenuItem> loadDatafromCSV(ArrayList<Branch> branches, ArrayList<MenuItem> x){
        ArrayList<MenuItem> menuitems = new ArrayList<>(); // the return value
    
        // load data from the menu list csv
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.menuListPath.getPath());
    
        for(String s:serialisedData){
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("Name,") || s.contains("Price,") || row.length < 4)
                continue;
    
            String categorystr = row[3].trim().split("\\s+")[0].toUpperCase(); // take the first word
            MealCategory category=MealCategory.UNDEFINED;

            for(MealCategory mc : MealCategory.values()){
                if(categorystr.equalsIgnoreCase(mc.toString())) category = mc;
            }
            
            String food = row[0].trim();
            Double price = Double.parseDouble(row[1].trim());
            String branchname = row[2].trim();
            String desc = row[4].trim();

            // find the matching branch and add it in
            MenuItem tempMenuItems;
            for (Branch b : branches) {
                if (b.getBranchName().equalsIgnoreCase(branchname)) {
                    tempMenuItems  = new MenuItem(food, price, b, category, desc, "NA");
                    menuitems.add(tempMenuItems); // add to the return value of list of staff
                    break;
                }
            }
        }
        return menuitems;
        }
    /**
     * This method adds a MenuItem object to the CSV file
     * @param m the MenuItem object to be added
     * @return true if the MenuItem is successfully added to the CSV file
     */
    public static boolean addMenuToCSV(MenuItem m) {
        String menuData = String.format("%s,%.2f,%s,%s,%s\n", m.getFood(), m.getPrice(), m.getBranch().getBranchName(), m.getCategory(), m.getDescription());
        return SerialiseCSV.appendToCSV(menuData, FilePaths.menuListPath.getPath());
    }
    /**
     * This method removes a MenuItem object from the CSV file
     * @param m the MenuItem object to be removed
     * @return true if the MenuItem is successfully removed from the CSV file
     */
    public static boolean removeMenuItem(MenuItem m){
        return SerialiseCSV.deleteToCSV(m.getFood(), 0, FilePaths.menuListPath.getPath());
    }
    /**
     * This method replaces an old MenuItem object with a new MenuItem object in the CSV file
     * @param oldItem the old MenuItem object to be replaced
     * @param newItem the new MenuItem object to replace the old MenuItem object
     * @return true if the MenuItem is successfully replaced in the CSV file
     */
    public static boolean replaceMenuItem(MenuItem oldItem, MenuItem newItem){
        return removeMenuItem(oldItem) && addMenuToCSV(newItem);
    }
        
}