package pages.staffPages.admin;

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
        System.out.println("Admin Access Page");
        System.out.println("[1]. Manage Staff");
        System.out.println("[2]. Manage Branch");
        System.out.println("[3]. Manage Payment Methods");
        System.out.println("[B]. Back to Staff Access Page");
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
                pageViewer.changePage("AdminManagePaymentMethodsPage");
                break;
            case "B":
                pageViewer.changePage("StaffAccessPage");
                break;
            case "b":
                pageViewer.changePage("StaffAccessPage");
                break;
            default:
                pageViewer.changePage("Invalid choice");
                break;
        }
    }

}