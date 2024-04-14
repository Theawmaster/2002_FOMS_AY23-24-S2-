package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import constants.FilePaths;
import constants.Role; 
import entities.Branch;
import entities.Staff;

/**
 * The {@link LoadStaffs} class loads Staff data and corresponding passwords from the CSV database
 */
public class LoadStaffs extends LoadData<Staff>{
    /**
     * The {@link loadedPasswords} private variable stores a hashmap of staffID:password to be loaded into the Staff class. 
     * This variable will not be accessible outside of this class
     */
    private HashMap<String, String> loadedPasswords;
    /**
     * The {@link loadDatafromCSV} method in this class loads in staff data from staff_list.csv 
     * @return a list of Staff objects with information loaded in
     */
    @Override
    public ArrayList<Staff> loadDatafromCSV(){
        ArrayList<Staff> staffs = new ArrayList<Staff>(); // the return value

        // load data from the staff list csv
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.staffListPath.getPath());

        // Load branch data from the CSV file
        //TODO: no you call LoadBranches to do this job instead, dont do the job for them
        List<Branch> branches = loadBranchesFromCSV(FilePaths.branchListPath.getPath());

        // load password data into this.passwords
        getPasswords();

        for(String s:serialisedData){
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("Name,") || s.contains("Staff Login ID,") || row.length < 5)
                continue;

            Role staffRole = Role.UNDEFINED;
            if(row[2].trim().toUpperCase().contains("S")) staffRole = Role.STAFF;
            else if(row[2].trim().toUpperCase().contains("M")) staffRole = Role.MANAGER;
            else if(row[2].trim().toUpperCase().contains("A")) staffRole = Role.ADMIN;
            //TODO: else throw an exception

            boolean isFemale = row[3].trim().equalsIgnoreCase("F");
            String[] name = row[0].trim().split(" ", 2);
            String firstName = name[0];
            String lastName = (name.length > 1) ? name[1] : name[0];
            String loginID = row[1].trim();
            int age = Integer.parseInt(row[4].trim());
            String branchName = row[5].trim();

            String staffPassword; 
            if(this.loadedPasswords!=null && this.loadedPasswords.containsKey(loginID)) 
                staffPassword = this.loadedPasswords.get(loginID);
            else{
                staffPassword = "password"; // default if there is no record in staff_passwords.csv
                appendNewPasswordRecord(loginID, staffPassword);
            }

            Staff tempStaff = new Staff(firstName, lastName, loginID, staffRole, isFemale, age, staffPassword);

            // Find the branch object matching the staff's branch name
            for (Branch branch : branches) {
                if (branch.getBranchName().equalsIgnoreCase(branchName)) {
                    tempStaff.setBranch(branch);
                    break;
                }
            }

            staffs.add(tempStaff);
        }
        return staffs;
    }

    /**
     * Loads branch data from the CSV file.
     * TODO: delete this. dont do LoadBranch's job
     * @param filePath The path to the CSV file containing branch data.
     * @return A list of Branch objects.
     */
    private List<Branch> loadBranchesFromCSV(String filePath) {
        List<Branch> branches = new ArrayList<>();

        // Load data from the branch CSV
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(filePath);

        for (String s : serialisedData) {
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("Name,") || row.length < 3)
                continue;

            String branchName = row[0].trim();
            String location = row[1].trim();
            int quota = Integer.parseInt(row[2].trim());
            String status = row[3].trim();

            branches.add(new Branch(branchName, location, quota, status));
        }
        return branches;
    }

    /**
    * The {@link updatePassword} method calls on the {@link SerialiseCSV.replaceColumnValue} method to update the 2nd column with the new password
    * @param loginID search String
    * @param newPassword updated new password
    * @return true if successful
    */
   public static boolean updatePassword(String loginID, String newPassword){
        return SerialiseCSV.replaceColumnValue(loginID, 1, newPassword, FilePaths.staffPasswordsPath.getPath());
    }


    /**
     * The {@link getPasswords} private method reads in saved passwords from staff_passwords.csv and stores them in the private variable {@link loadedPasswords}
     * TODO: Add a point for decryption of password data. Passwords should not be stored in raw form
     */
    private void getPasswords(){
        this.loadedPasswords = new HashMap<String, String>();
        // get each line in csv
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.staffPasswordsPath.getPath());

        // iterate through the lines of password records
        for(String s : serialisedData){
            String[] loginPair = s.split(",");
            if(s.isEmpty()) continue;
            String staffID = loginPair[0];
            String staffPassword = loginPair[1];

            this.loadedPasswords.put(staffID, staffPassword);
        }
    }
    /**
     * The {@link appendNewPasswordRecord} private function appends a new password record 
     * Used in the {@link loadData} method above if password not already existing for the current Staff
     * @param staffID String argument of the corresponding staffID
     * @param staffPassword String argument of the corresponding staffPassword
     * TODO: should this be a private method? what if i wanna change password
     * TODO: Add a point for encryption of password data. Passwords should not be stored in raw form
     */
    private void appendNewPasswordRecord(String staffID, String staffPassword){
        String passwordRecord = staffID + "," + staffPassword;
        SerialiseCSV.appendToCSV(passwordRecord, FilePaths.staffPasswordsPath.getPath());
    }

    
}
