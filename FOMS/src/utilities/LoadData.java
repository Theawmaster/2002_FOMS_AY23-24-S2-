package utilities;

import java.util.ArrayList;
import java.util.Locale.Category;

import constants.Role;
import entities.Staff;
import entities.Branch;

import constants.MealCategory;
import entities.MenuItems;

/**
 * Class that accounts for loading of data: Staff, Branch, Menu item
 */
public class LoadData {
    public LoadData(){}

    /**
     * Function that reads CSV data and loads corresponding data into Staff object
     * @return a list of Staff
     */
    public ArrayList<Staff> loadStaffs(){
        ArrayList<Staff> staffs = new ArrayList<>(); // the return value

        SerialiseCSV c = new SerialiseCSV();
        // load data from the staff list csv
        ArrayList<String> serialisedData = c.readCSV(constants.FilePaths.staffListPath.getPath());

        for(String s:serialisedData){
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("Name,") || s.contains("Staff Login ID,") || row.length < 5)
                continue;


            Role staffRole = Role.UNDEFINED;
            if(row[2].trim().toUpperCase().contains("S")) staffRole = Role.STAFF;
            else if(row[2].trim().toUpperCase().contains("M")) staffRole = Role.MANAGER;
            else if(row[2].trim().toUpperCase().contains("A")) staffRole = Role.ADMIN;
            //TODO: else throw an exception
            boolean isFemale = true;
            if(row[3].trim().toUpperCase().contains("F")) isFemale = true;
            else if(row[3].trim().toUpperCase().contains("M")) isFemale = false;
            //TODO: else throw an exception
            String[] name = row[0].trim().split(" ", 2);
            String firstName = name[0];
            String lastname = (name.length>1) ? name[1] : name[0];

            Staff tempStaff = new Staff(firstName, lastname, row[1].trim(), staffRole, isFemale, Integer.parseInt(row[4].trim()));
            staffs.add(tempStaff); // add to the return value of list of staff
        }
        return staffs;
    }
    
    /**
     * Function that reads CSV data and loads corresponding data into Branch object
     * @return a list of branches
     */
    public ArrayList<Branch> loadBranches(){
        ArrayList<Branch> branches = new ArrayList<>();

        SerialiseCSV c = new SerialiseCSV();
        // load data from the branch list csv
        ArrayList<String> serialisedData = c.readCSV(constants.FilePaths.branchListPath.getPath());

        for(String s:serialisedData){
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("Name,") || s.contains("Location") || row.length < 2)
                continue;
            
            String name = row[0];
            String location = row[1];
            int quota = Integer.parseInt(row[2].trim());

            Branch tempBranch = new Branch(name, location, quota);
            branches.add(tempBranch);
        }
        return branches;
    }

    public ArrayList<MenuItems> loadMenuItems(){
        ArrayList<MenuItems> menuitems = new ArrayList<>(); // the return value
    
        SerialiseCSV c = new SerialiseCSV();
            // load data from the menu list csv
        ArrayList<String> serialisedData = c.readCSV(constants.FilePaths.menuListPath.getPath());
    
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
                // TODO: Handle exception if category is undefined
    
            
            String food = row[0].trim();
            Double price = Double.parseDouble(row[1].trim());
            // Branch branch = row[2];

            MenuItems tempMenuItems = new MenuItems(food, price, category);
            menuitems.add(tempMenuItems); // add to the return value of list of staff
            }
            // to do: implement getbranch 
            return menuitems;
        }
}
