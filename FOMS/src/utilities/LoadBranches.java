package utilities;

import java.util.ArrayList;

import constants.FilePaths;
import entities.Branch;
import entities.MenuItem;

/**
 * The {@link LoadStaffs} class loads Branch data from the CSV database. It inherits from the {@link LoadData} class
 * @author Jed
 */
public class LoadBranches extends LoadData<Branch>{
    /**
     * This constructor creates a LoadBranches object with a list of Branch objects and a list of MenuItem objects
     * @param branches null
     * @param menu null
     */
    public LoadBranches(ArrayList<Branch> branches, ArrayList<MenuItem> menu) {
        super(branches, menu);
    }
    /**
     * The {@link loadDatafromCSV} method in this class loads in Branch data from branch_list.csv 
     * @param x null
     * @param y null
     * @return a list of Branch objects with information loaded in
     */
    @Override
    public ArrayList<Branch> loadDatafromCSV(ArrayList<Branch> x, ArrayList<MenuItem> y){
        ArrayList<Branch> branches = new ArrayList<>();
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.branchListPath.getPath());

        boolean isFirstLine = true; // Flag to skip the header
        for (String s : serialisedData) {
            if (isFirstLine) {
                isFirstLine = false; // Skip the first line (header)
                continue;
            }
            if (s.isEmpty()) continue; // Skip empty lines

            String[] row = s.split(",");
            if (row.length < 4) continue; // Ensure there are enough columns

            try {
                String name = row[0].trim();
                String location = row[1].trim();
                int quota = Integer.parseInt(row[2].trim());
                String status = row[3].trim();

                branches.add(new Branch(name, location, quota, status));
            } catch (NumberFormatException e) {
                System.err.println("Error parsing quota for branch: " + e.getMessage());
                // Optionally handle this error or log it
            }
        }
        return branches;
    }
    /**
     * Add a new branch to the CSV file
     * @param branch
     * @return true if the branch was added successfully, false otherwise
     */
    public static boolean addBranch(Branch branch) {
        String newBranchData = branch.getBranchName() + "," + branch.getLocation() + "," + branch.getbranchQuota() + "," + branch.getStatus();
        return SerialiseCSV.appendToCSV(newBranchData, FilePaths.branchListPath.getPath());
    }
    /**
     * Update the status of a branch in the CSV file
     * @param branch
     * @param status
     * @return true if the branch status was updated successfully, false otherwise
     */
    public static boolean updateBranchStatus(Branch branch, String status) {
        return SerialiseCSV.replaceColumnValue(branch.getBranchName(), 3, status, FilePaths.branchListPath.getPath());
    }
    /**
     * Remove a branch from the CSV file
     * @param branch
     * @return true if the branch was removed successfully, false otherwise
     */
    public static boolean removeBranch(Branch branch) {
        return SerialiseCSV.deleteToCSV(branch.getBranchName(), 0, FilePaths.branchListPath.getPath());
    }

}

