package pages.staffPages.admin;

import constants.Role;
import pages.iPage;
import pages.pageViewer;
import utilities.Session;

/**
 * This class is the page that the admin will see after logging in
 * This page facilitates the admin's access to the system
 * The admin can manage staff, branches, and payment methods
 * @author @Theawmaster
 */
public class AdminAccessPage implements iPage {
    /**
     * The current active session
     */
    private Session session;
    public AdminAccessPage(Session s) {
        this.session = s;
    }

    /**
     * Method to view admin access options
     */
    public void viewOptions() {
        // allow entry only if current user is an admin
        if(this.session.getCurrentActiveStaff().getRole() == Role.ADMIN){
            System.out.println("Admin Access Page");
            System.out.println("[1] Manage Staff");
            System.out.println("[2] Manage Branch");
            System.out.println("[3] Manage Payment Methods");
            System.out.println("[B] Back to Staff Access Page");
        }
        else{
            System.out.println("You are not an admin! You do not have access to this page!");
            // go back to staff access page
            pageViewer.changePage("back");
        }
    }

    /**
     * Method to handle admin access options
     * @param choice branch choice
     */
    public void handleInput(String choice) {
        switch (choice) {
            case "1":
                pageViewer.changePage("AdminManageStaffPage");
                break;
            case "2":
                pageViewer.changePage("AdminManageBranchPage");
                break;
            case "3":
                pageViewer.changePage("AdminManagePaymentPage");
                break;
            case "B":
            case "b":
                pageViewer.changePage("back");
                break;
            default:
                pageViewer.changePage("Invalid choice");
                break;
        }
    }

}