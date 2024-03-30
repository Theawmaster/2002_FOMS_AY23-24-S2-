package utilities;

import java.util.ArrayList;

import constants.Role;
import entities.Staff;
import entities.Branch;

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

    // public ArrayList<MenuItem> loadMenuItems(){

    // }
}
