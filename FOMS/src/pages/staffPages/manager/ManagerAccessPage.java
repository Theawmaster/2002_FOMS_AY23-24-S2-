package pages.staffPages.manager;

import constants.Role;
import pages.iPage;
import pages.PageViewer;
import utilities.Session;

/**
 * This page is responsible for displaying and handling inputs for the manager access page
 * @author Siah Yee Long
 * @author Chan Zi Hao
 */
public class ManagerAccessPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    /**
     * Constructor for ManagerAccessPage
     * @param s the current session
     */
    public ManagerAccessPage(Session s){
        this.session = s;
    }
    /**
     * Method to view manager access options
     */
    public void viewOptions(){
        if (this.session.getCurrentActiveStaff().getRole() == Role.STAFF){
            // allow MANAGER or ADMIN only
            System.out.println("You are not a manager! You do not have access to this page!");
            // go back to staff access page
            PageViewer.changePage("back");
        }
        else {
            System.out.println("[1] Display My Staffs");
            System.out.println("[2] Edit Menu Items");
            System.out.println("[B] Return to Staff Access Page");
        }
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                PageViewer.changePage("ViewStaffDetailsPage");
                break;
            case "2":
                PageViewer.changePage("EditMenuItemsPage");
                break;
            case "b":
            case "B":
                PageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
