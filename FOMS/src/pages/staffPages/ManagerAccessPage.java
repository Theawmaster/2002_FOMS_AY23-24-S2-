package pages.staffPages;

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
        System.out.println("[1] Display My Staffs");
        System.out.println("[2] Edit Menu Items");
        System.out.println("[B] Return to Staff Access Page");
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
                break;
        }
    }
}
