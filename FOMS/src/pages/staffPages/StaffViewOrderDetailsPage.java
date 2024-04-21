package pages.staffPages;

import pages.iPage;
import pages.pageViewer;
import services.ProcessOrderService;
import utilities.Session;

public class StaffViewOrderDetailsPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public StaffViewOrderDetailsPage(Session s){
        this.session = s;
    }
    /**
     * Method to view order details process options
     */
    public void viewOptions(){
        ProcessOrderService.displayOrderDetailsProcessOptions();;
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                ProcessOrderService orderService = new ProcessOrderService(session);
                orderService.processOrderImmediately();
                pageViewer.changePage("StaffProcessOrderPage");
                break;
            case "b":
            case "B":
                ProcessOrderService.resetStoredOrderID();
                pageViewer.changePage("StaffProcessOrderPage");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
