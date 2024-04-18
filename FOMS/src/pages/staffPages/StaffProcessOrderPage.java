package pages.staffPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;
import services.ProcessOrderService;

public class StaffProcessOrderPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public StaffProcessOrderPage(Session s){
        this.session = s;
    }
    /**
     * Method to display list of pending orders & view process order options
     */
    public void viewOptions(){
        ProcessOrderService.displayProcessOptions();
    }
    /**
    * Method to handle user input 
    * @param choice branches the pages
    */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                ProcessOrderService.processOrderWithUserInputOfOrderID();
                pageViewer.changePage("StaffProcessOrderPage");
                break;
            case "2":
                pageViewer.changePage("StaffViewOrderDetailsPage");
                break;
            case "b":
            case "B":
                pageViewer.changePage("StaffAccessPage");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}