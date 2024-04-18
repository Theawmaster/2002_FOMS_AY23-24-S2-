package pages.staffPages.manager;

import constants.Role;
import pages.iPage;
import pages.pageViewer;
import utilities.Session;

public class ManagerAccessPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public ManagerAccessPage(Session s){
        this.session = s;
    }
    /**
     * Method to view manager access options
     */
    public void viewOptions(){
        if (this.session.getCurrentActiveStaff().getRole() == Role.MANAGER){
            System.out.println("[1] Display My Staffs");
            System.out.println("[2] Edit Menu Items");
            System.out.println("[B] Return to Staff Access Page");
        }
        else {
            System.out.println("You are not a manager! You do not have access to this page!");
            // go back to staff access page
            pageViewer.changePage("StaffAccessPage");
        }
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                pageViewer.changePage("ManagerViewStaffDetailsPage");
                break;
            case "2":
                pageViewer.changePage("ManagerEditMenuItemsPage");
                break;
            case "b":
                pageViewer.changePage("StaffAccessPage");
                break;
            case "B":
                pageViewer.changePage("StaffAccessPage");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
