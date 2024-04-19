package pages.staffPages.admin;

import services.ManageStaffService;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

/**
 * The AdminManageStaffPage class is a page that allows the admin to manage staff members
 * The admin can view all staff members, fire staff members, hire staff members, and promote staff members
 * The admin can also go back to the AdminAccessPage
 * @author @Theawmaster
 */
public class AdminManageStaffPage implements iPage {
    /**
     * The current active session
     */
    private Session session;

    public AdminManageStaffPage(Session session) {
        this.session = session;
    }

    /**
     * Method to display staff management page
     */
    public void viewOptions() {
        System.out.println("Admin Manage Staff Page");
        System.out.println("[1] View all Staff");
        System.out.println("[2] Fire Staff");
        System.out.println("[3] Hire Staff");
        System.out.println("[4] Promote Staff");
        System.out.println("[5] Transfer Staff");
        System.out.println("[B] Back");
    }

    /**
     * Method to handle user input
     * @param option
     */
    public void handleInput(String option) {
        switch(option) {
            case "1":
                // just go to this page instead since the functions are alr there
                pageViewer.changePage("ManagerViewStaffDetailsPage");
                break;
            case "2":
                ManageStaffService.fireStaff(this.session);
                pageViewer.changePage("current");
                break;
            case "3":
                ManageStaffService.hireStaff(this.session);
                pageViewer.changePage("current");
                break;
            case "4":
                ManageStaffService.promoteStaff(this.session);
                pageViewer.changePage("current");
                break;
            case "5":
                ManageStaffService.transferStaff(this.session);
                pageViewer.changePage("current");
                break;
            case "B":
            case "b":
                pageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
    
}