package pages.staffPages.admin;

import constants.FilePaths;
import pages.iPage;
import pages.pageViewer;
import utilities.PersistenceHandler;
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

    public AdminManageBranchPage(Session session) {
        this.session = session;
    }

    /**
     * Method to view Admin Manage Payment Methods Page
     */
    public void viewOptions() {
        System.out.println("== Admin Manage Branches ==");
        System.out.println("[1] Open/Enable a branch");
        System.out.println("[2] Close/Disable a branch");
        System.out.println("[3] Add a branch");
        System.out.println("[4] Remove a branch");
        System.out.println("[5] View all branches");
        System.out.println("[B] Go back");
    }

    /**
     * Method to handle user input
     * @param option: user input
     */
    public void handleInput(String option) {
        // if data has been modified by another instance of the FOMS app, update it into the session
        if(PersistenceHandler.hasBeenUpdated(FilePaths.dataFolderPath.getPath())){ session.updateSession(); }
        switch (option) {
            case "1":
                ManageBranchService.changeBranchStatus(this.session, true);
                pageViewer.changePage("AdminManageBranchPage");
                break;
            case "2":
                ManageBranchService.changeBranchStatus(this.session, false);
                pageViewer.changePage("AdminManageBranchPage");
                break;
            case "3":
                ManageBranchService.addBranch(this.session);
                pageViewer.changePage("AdminManageBranchPage");
                break;
            case "4": 
                ManageBranchService.removeBranch(this.session);
                pageViewer.changePage("AdminManageBranchPage");
                break;
            case "5":
                ManageBranchService.displayBranches(this.session);
                pageViewer.changePage("AdminManageBranchPage");
                break;
            case "B":
            case "b":
                pageViewer.changePage("AdminAccessPage");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}