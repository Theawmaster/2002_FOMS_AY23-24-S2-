package services;

import utilities.LoadBranches; 
import entities.Branch;
import entities.Staff;
import utilities.Session;
import utilities.UserInputHelper; 

/**
 * Service class for managing branch operations. This class is called by the AdminManageBranchPage
 * @author Alvin Aw Yong
 * @author Siah Yee Long
 */
public class ManageBranchService {
    /**
     * Private constructor to prevent instantiation of this class
     */
    private ManageBranchService() {}
    /**
     * Method to change the status of a branch and update the CSV files accordingly
     * @param session the current active session
     * @param open true if the branch is to be opened, false if the branch is to be closed
     */
    public static void changeBranchStatus(Session session, boolean open) {
        String status = open ? "Open" : "Close";
        Branch whichBranch = UserInputHelper.chooseBranch(session.getAllBranches());
        if(whichBranch == null) return;
        if(LoadBranches.updateBranchStatus(whichBranch, status)){
            session.getAllBranches().add(whichBranch);
            whichBranch.setStatus(status);
            System.out.println(whichBranch.getBranchName()+" has been "+status);
            return;
        }
        else{
            System.out.println("Error in opening/closing branch");
            return;
        }
    }
    /**
     * Method to add a new branch and update the CSV files accordingly
     * @param session the current active session
     */
    public static void addBranch(Session session){ //String branchName, String location, int quota, String status) {
        String branchName = UserInputHelper.getInput("Enter the new branch name:");
        String location = UserInputHelper.getInput("Enter the new branch's location:");
        int quota = UserInputHelper.getUserChoice("Enter the branch's staff quota:", 999);
        if(quota == -1) return; // user decided to cancel adding (back to main menu)
        Branch newBranch = new Branch(branchName, location, quota, "Open");
        if(LoadBranches.addBranch(newBranch)){
            session.getAllBranches().add(newBranch);
            System.out.println("New branch "+newBranch.getBranchName()+" successfully added!");
            return;
        }
        else{
            System.out.println("Failed to add a new branch");
            return;
        }
    }
    /**
     * Method to remove a branch and update the CSV files accordingly
     * @param session the current active session
     */
    public static void removeBranch(Session session) {
        Branch badBranch = UserInputHelper.chooseBranch(session.getAllBranches());
        if(badBranch == null) return;
        if(LoadBranches.removeBranch(badBranch)){
            for(Staff unluckyStaff : session.getAllStaffs()){
                if(unluckyStaff.getBranch() == badBranch){
                    // when branch gets deleted, all existing staff just gets displaced into oblivion
                    Branch undefinedBranch = new Branch("UNDEFINED", "beside the dustbin", 999, "Open");
                    unluckyStaff.setBranch(undefinedBranch);
                }
            }
            session.getAllBranches().remove(badBranch);
            System.out.println("Removal of branch successful. All affected staff are gonna starve because of YOU");
            return;
        }
        else{
            System.out.println("Removal of branch failed. phew...");
        }
    }
    /**
     * Method to display all branches
     * @param session the current active session
     */
    public static void displayBranches(Session session) {
        System.out.println("Branch name\tLocation\t\tQuota\tStatus");
        for (Branch branch : session.getAllBranches()) {
            // Adjust the integers in %-15s, %-20s, %-5d according to your data length needs
            String output = String.format("%-15s\t%-20s\t%-5d\t%-5s",
                                        branch.getBranchName(),
                                        branch.getLocation(),
                                        branch.getbranchQuota(),
                                        branch.getStatus());
            System.out.println(output);
        }
        return;
    }
    
}
