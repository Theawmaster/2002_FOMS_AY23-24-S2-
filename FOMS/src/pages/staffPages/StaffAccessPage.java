package pages.staffPages;

import pages.iPage;
import pages.PageViewer;
import utilities.Session;

/**
 * This page allows the staff to access the staff options
 * @author Siah Yee Long
 */
public class StaffAccessPage implements iPage {
    /**
     * The current active session 
     */
    private Session session;
    /**
     * Constructor for the StaffAccessPage class
     * @param s the current session
     */
    public StaffAccessPage(Session s){
        this.session = s;
    }
    /**
     * Method to view staff access options
     */
    public void viewOptions(){
        System.out.println("[1] View Pending Orders");
        System.out.println("[2] Manager Access");
        System.out.println("[3] Admin Access");
        System.out.println("[B] Log Out");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                PageViewer.changePage("StaffProcessOrderPage");
                break;
            case "2":
                PageViewer.changePage("ManagerAccessPage");
                break;
            case "3":
                PageViewer.changePage("AdminAccessPage");
                break;
            case "b":
            case "B":
                this.session.setCurrentActiveStaff(null);
                System.out.println("Logging out...");
                PageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}