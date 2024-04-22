package test.archives;

import pages.iPage;
import pages.PageViewer;
import services.ProcessOrderService;
import utilities.Session;

public class archiveStaffViewOrderDetailsPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public archiveStaffViewOrderDetailsPage(Session s){
        this.session = s;
    }
    /**
     * Method to view order details process options
     */
    public void viewOptions(){
        
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                ProcessOrderService.processOrder(this.session);
                PageViewer.changePage("StaffProcessOrderPage");
                break;
            case "b":
            case "B":
                PageViewer.changePage("StaffProcessOrderPage");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
