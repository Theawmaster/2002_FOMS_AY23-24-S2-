package pages.staffPages.admin;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;
import services.ManageBranchService;

/**
 * This page allows the admin to manage branches
 * The admin can open/enable a branch and close/disable a branch
 * @author @Theawmaster
 */
public class AdminManageBranchPage implements iPage {
    /**
     * The current active session
     */
    private Session session;
    private ManageBranchService branchService;

    public AdminManageBranchPage(Session session) {
        this.session = session;
        this.branchService = new ManageBranchService();
    }

    /**
     * Method to view Admin Manage Payment Methods Page
     */
    public void viewOptions() {
        System.out.println("== Admin Manage Branches ==");
        System.out.println("[1]. Open/Enable a branch");
        System.out.println("[2]. Close/Disable a branch");
        System.out.println("[3]. Add a branch");
        System.out.println("[4]. Remove a branch");
        System.out.println("[B]. Go back");
    }

    /**
     * Method to handle user input
     * @param option: user input
     */
    public void handleInput(String option) {
        switch (option) {
            case "1":
                System.out.println("Enter branch name to open:");
                String branchNameOpen = System.console().readLine().toUpperCase();
                if (branchService == null) {
                    System.out.println("Branch service is not initialized.");
                } else {
                    if (branchService.changeBranchStatus(branchNameOpen, true)) {
                        System.out.println(branchNameOpen + " is now open.");
                    } else {
                        System.out.println("Failed to open " + branchNameOpen);
                    }
                }
            break;
            case "2":
                System.out.println("Enter branch name to close:");
                String branchNameClose = System.console().readLine().toUpperCase();
                if (branchService.changeBranchStatus(branchNameClose, false)) {
                    System.out.println(branchNameClose + " is now closed.");
                } else {
                    System.out.println("Failed to close " + branchNameClose);
                }
                break;
                case "3":  // Adding a new branch
                branchService.displayBranches();
                System.out.println("Enter branch details (Name,Location,Quota,Status):");
                String[] details = System.console().readLine().split(",");
                if (branchService.addBranch(details[0], details[1], Integer.parseInt(details[2]), details[3])) {
                    System.out.println("Branch added successfully.");
                } else {
                    System.out.println("Failed to add branch.");
                }
                break;
            case "4":  // Removing a branch
                branchService.displayBranches();
                System.out.println("Enter branch name to remove:");
                String branchName = System.console().readLine();
                if (branchService.removeBranch(branchName)) {
                    System.out.println("Branch removed successfully.");
                } else {
                    System.out.println("Failed to remove branch.");
                }
                break;
            case "B":
                pageViewer.changePage("AdminAccessPage");
                break;
            case "b":
                pageViewer.changePage("AdminAccessPage");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}