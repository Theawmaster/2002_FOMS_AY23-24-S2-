package pages.staffPages.manager;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

public class ManagerViewStaffDetailsPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public ManagerViewStaffDetailsPage(Session s){
        this.session = s;
    }
    /**
     * Method to view staff details options
     */
    public void viewOptions(){
        System.out.println("[1] Sort staff");
        //System.out.println("[2] Manager Access");
        //System.out.println("[3] Admin Access");
        System.out.println("[B] Return to Manager Access Page");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            // case "1":
            //     pageViewer.changePage("StaffProcessOrderPage");
            //     break;
            // case "2":
            //     pageViewer.changePage("ManagerAccessPage");
            //     break;
            // case "3":
            //     pageViewer.changePage("AdminAccessPage");
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

