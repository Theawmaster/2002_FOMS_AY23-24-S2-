package pages.staffPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

public class StaffAccessPage implements iPage {
    /**
     * The current active session 
     */
    private Session session;
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
        System.out.println("[B] Return to Staff Login Page");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                pageViewer.changePage("StaffProcessOrderPage");
                break;
            case "2":
                pageViewer.changePage("ManagerAccessPage");
                break;
            case "3":
                pageViewer.changePage("AdminAccessPage");
                break;
            case "b":
            case "B":
                pageViewer.changePage("StaffLoginPage");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}