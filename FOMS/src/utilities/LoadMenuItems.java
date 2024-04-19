package utilities;

import java.util.ArrayList;

import constants.FilePaths;
import constants.MealCategory;
import entities.MenuItem;
import entities.Branch;

/**
 * The {@link LoadStaffs} class loads MenuItem data from the CSV database
 * @param branches
 * @return a list of MenuItem objects with information loaded in
 * @author @Theawmaster
 */
public class LoadMenuItems extends LoadData<MenuItem>{
    public LoadMenuItems(ArrayList<Branch> branches) {
        super(branches);
    }

    /**
     * The {@link loadDatafromCSV} method in this class loads in MenuItem data from menu_list.csv 
     * @return a list of MenuItem objects with information loaded in
     */
    @Override
    public ArrayList<MenuItem> loadDatafromCSV(ArrayList<Branch> branches){
        ArrayList<MenuItem> menuitems = new ArrayList<>(); // the return value
    
        // load data from the menu list csv
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.menuListPath.getPath());
    
        for(String s:serialisedData){
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("Name,") || s.contains("Price,") || row.length < 4)
                continue;
    
            String categorystr = row[3].trim().split("\\s+")[0].toUpperCase(); // take the first word
            MealCategory category;
            if ("SIDE".equals(categorystr)) {
                category = MealCategory.SIDE;
            } else if ("SET".equals(categorystr)) {
                category = MealCategory.SETMEAL;
            } else if ("BURGER".equals(categorystr)) {
                category = MealCategory.BURGER;
            } else if ("DRINK".equals(categorystr)) {
                category = MealCategory.DRINK;
            } else{
                category = MealCategory.UNDEFINED;
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
        // TODO: implement getbranch 
        return menuitems;
        }

        /**
         * This method adds a MenuItem object to the CSV file
         * @param m
         * @return
         */
        public static boolean addMenuToCSV(MenuItem m) {
            String menuData = String.format("%s,%.2f,%s,%s,%s\n", m.getFood(), m.getPrice(), m.getBranch().getBranchName(), m.getCategory(), m.getDescription(), m.getCustomization());
            // this function is alr written in SerialiseCSV, why not just use the methods if it's alr available
            // try {
            //     Files.write(Paths.get(FilePaths.menuListPath.getPath()), menuData.getBytes(), StandardOpenOption.APPEND);
            //     return true;
            // } catch (IOException e) {
            //     System.out.println("Failed to write to CSV: " + e.getMessage());
            //     return false;
            // }
            return SerialiseCSV.appendToCSV(menuData, FilePaths.menuListPath.getPath());
        }
        public static boolean removeMenuItem(MenuItem m){
            return SerialiseCSV.deleteToCSV(m.getFood(), 0, FilePaths.menuListPath.getPath());
        }

}