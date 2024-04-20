package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    public LoadStaffs(ArrayList<Branch> branches) {
        super(branches);
    }

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
    public ArrayList<Staff> loadDatafromCSV(ArrayList<Branch> branches){
        ArrayList<Staff> staffs = new ArrayList<Staff>(); // the return value

        // load data from the staff list csv
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.staffListPath.getPath());

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

            // Find the branch object matching the staff's branch name and update the number of managers and staff in the branch
            for (Branch branch : branches) {
                if (branch.getBranchName().equalsIgnoreCase(branchName)) {
                    if(tempStaff.getRole() == Role.MANAGER){
                        branch.incrementManagerCount();
                    }
                    else if(tempStaff.getRole() == Role.STAFF)
                        branch.incrementStaffCount();

                    tempStaff.setBranch(branch);
                    break;
                }
            }

            staffs.add(tempStaff);
        }
        return staffs;
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
     */
    private void appendNewPasswordRecord(String staffID, String staffPassword){
        String passwordRecord = staffID + "," + staffPassword;
        SerialiseCSV.appendToCSV(passwordRecord, FilePaths.staffPasswordsPath.getPath());
    }



    /**
     * Adds a new staff member to the staff list CSV file.
     * @param name
     * @param id
     * @param role
     * @param gender
     * @param age
     * @param branch
     * @return
     * @author @Theawmaster
     */
    public static boolean addStaffToCSV(Staff s) {
        String staffData = String.format("%s,%s,%s,%s,%d,%s\n", s.getFirstName()+" "+s.getLastName(), s.getLoginID(), s.getRole(), s.getGender(), s.getAge(), s.getBranch().getBranchName());
        try {
            Files.write(Paths.get(FilePaths.staffListPath.getPath()), staffData.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to write to CSV: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reads all staff lines from the staff list CSV file.
     * @return
     * @throws IOException
     * @Author @Theawmaster
     */
    public List<String> readAllStaffLines() throws IOException {
        return Files.readAllLines(Paths.get(FilePaths.staffListPath.getPath()));
    }
    
    /**
     * Removes a staff line from the staff list CSV file.
     * @param index
     * @throws IOException
     * @author @Theawmaster
     */
    public void removeStaffLine(int index) throws IOException {
        List<String> lines = readAllStaffLines();
        if (index >= 0 && index < lines.size()) {
            lines.remove(index);
            Files.write(Paths.get(FilePaths.staffListPath.getPath()), lines);
        }
    }
    public static boolean updatePromotedStaff(Staff staff){
        return SerialiseCSV.replaceColumnValue(staff.getFirstName()+" "+staff.getLastName(), 3, "MANAGER", FilePaths.staffListPath.getPath());
    }
    public static boolean updateTransferredStaff(Staff staff, Branch branch){
        return SerialiseCSV.replaceColumnValue(staff.getFirstName()+" "+staff.getLastName(), 6, branch.getBranchName(), FilePaths.staffListPath.getPath());
    }
    public static boolean updateFiredStaff(Staff staff){
        return SerialiseCSV.deleteToCSV(staff.getLoginID(), 1, FilePaths.staffListPath.getPath()) 
            && SerialiseCSV.deleteToCSV(staff.getLoginID(), 0, FilePaths.staffPasswordsPath.getPath());
    }
        /**
     * Promotes a staff member to a manager.
     * @param staffId
     * @return
     * @throws IOException
     * @author @Theawmaster
     */
    // public boolean promoteStaffToManager(String staffId) throws IOException {
    //     List<String> lines = readAllStaffLines();
    //     boolean found = false;
    //     for (int i = 0; i < lines.size(); i++) {
    //         String[] parts = lines.get(i).split(",");
    //         if (parts[1].trim().equals(staffId) && parts[2].trim().equalsIgnoreCase("STAFF")) {
    //             String branch = parts[5].trim();
    //             if (canAddManager(branch, lines)) {
    //                 parts[2] = "MANAGER";
    //                 lines.set(i, String.join(",", parts));
    //                 found = true;
    //                 break;
    //             } else {
    //                 System.out.println("ORH OH... Manager quota exceeded for this branch.. Promotion not possible..");
    //                 return false;
    //             }
    //         }
    //     }
    
    //     if (!found) {
    //         System.out.println("BOOOO Staff ID not found or the employee is already a manager...");
    //         return false;
    //     }
    
    //     Files.write(Paths.get(FilePaths.staffListPath.getPath()), lines);
    //     return true;
    // }
    /**
     * This method calculates and returns the count of non-managerial staff per branch.
     * @return A map of branch names to their respective staff counts.
     */
    // public Map<String, Long> getStaffCounts() {
    //     List<String> staffLines = SerialiseCSV.readCSV(FilePaths.staffListPath.getPath());

    //     return staffLines.stream()
    //         .map(line -> line.split(","))
    //         .filter(parts -> parts.length >= 6 && !parts[2].trim().equalsIgnoreCase("MANAGER")) // Excluding managers
    //         .collect(Collectors.groupingBy(parts -> parts[5].trim(), Collectors.counting()));
    // }

    // /**
    //  * This method calculates and returns the count of managers per branch.
    //  * @return A map of branch names to their respective manager counts.
    //  */
    // public Map<String, Long> getManagerCounts() {
    //     List<String> staffLines = SerialiseCSV.readCSV(FilePaths.staffListPath.getPath());

    //     return staffLines.stream()
    //         .map(line -> line.split(","))
    //         .filter(parts -> parts.length >= 6 && parts[2].trim().equalsIgnoreCase("MANAGER")) // Only managers
    //         .collect(Collectors.groupingBy(parts -> parts[5].trim(), Collectors.counting()));
    // }
    /**
     * Checks if a manager can be added to the branch based on the current manager count.
     * @param branch
     * @param lines
     * @return
     * @author @Theawmaster
     */
    // private boolean canAddManager(String branch, List<String> lines) {
    //     // TODO Auto-generated method stub
    //     long currentManagers = lines.stream()
    //                                 .filter(line -> line.contains(branch) && line.split(",")[2].trim().equalsIgnoreCase("MANAGER"))
    //                                 .count();
    
    //     // Get staff count for quota calculation
    //     long staffCount = lines.stream()
    //                            .filter(line -> line.contains(branch) && line.split(",")[2].trim().equalsIgnoreCase("STAFF"))
    //                            .count();
    
    //     // Calculate allowed managers based on staff count
    //     int allowedManagers = (staffCount < 5) ? 1 : (staffCount < 9) ? 2 : 3;
    
    //     return currentManagers < allowedManagers;
    // }
    
    // public boolean updateStaffBranch(String staffId, String newBranch) throws IOException {
    //     List<String> lines = readAllStaffLines();
    //     boolean updated = false;
    //     for (int i = 0; i < lines.size(); i++) {
    //         String[] parts = lines.get(i).split(",");
    //         if (parts[1].trim().equals(staffId)) {
    //             parts[5] = newBranch;  // Update the branch
    //             lines.set(i, String.join(",", parts));
    //             updated = true;
    //             break;
    //         }
    //     }
    //     if (updated) {
    //         Files.write(Paths.get(FilePaths.staffListPath.getPath()), lines);
    //     }
    //     return updated;
    // }

}
