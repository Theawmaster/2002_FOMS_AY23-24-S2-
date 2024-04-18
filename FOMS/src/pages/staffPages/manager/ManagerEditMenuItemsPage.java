package pages.staffPages.manager;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

public class ManagerEditMenuItemsPage implements iPage {
        /**
     * The current active session 
     */
    private Session session;
    public ManagerEditMenuItemsPage(Session s){
        this.session = s;
    }
    /**
     * Method to view manager menu editing mode options
     */
    public void viewOptions(){
        // System.out.println("[1] Add Menu Item");
        // System.out.println("[2] Edit Menu Items");
        // System.out.println("[3] Remove Menu Items");
        System.out.println("[B] Return to Manager Access Page");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            // case "1":
            //     pageViewer.changePage("ManagerViewStaffDetailsPage");
            //     break;
            // case "2":
            //     pageViewer.changePage("ManagerEditMenuItemsPage");
            //     break;
            // case "3":
            //     pageViewer.changePage("ManagerEditMenuItemsPage");
            //     break;
            case "b":
                pageViewer.changePage("ManagerAccessPage");
                break;
            case "B":
                pageViewer.changePage("ManagerAccessPage");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}

