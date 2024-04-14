package services;

import java.util.ArrayList;
import java.io.FileWriter; 
import java.io.IOException; 

import utilities.LoadBranches; 
import entities.Branch; 
import utilities.Logger; 

/**
 * Service class for managing branches
 * @author @Theawmaster
 */
public class ManageBranchService {
    private LoadBranches loadBranches = new LoadBranches();

    public boolean changeBranchStatus(String branchName, boolean open) {
        String status = open ? "Open" : "Close";
        return loadBranches.updateBranchStatus(branchName, status);
    }

    public boolean addBranch(String branchName, String location, int quota, String status) {
        return loadBranches.addBranch(branchName, location, quota, status);
    }

    public boolean removeBranch(String branchName) {
        return loadBranches.removeBranch(branchName);
    }

    public void displayBranches() {
        ArrayList<Branch> branches = loadBranches.loadDatafromCSV();
        for (Branch branch : branches) {
            System.out.println(branch.getBranchName() + ", " + branch.getLocation() + ", " + branch.getQuota() + ", " + branch.getStatus());
        }
    }
    
}
