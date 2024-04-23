package pages.staffPages;

import entities.Order;
import pages.iPage;
import pages.PageViewer;
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
        for(Order o : this.session.getAllOrders()){
            System.out.println("Order number: " + o.getOrderId() + " [" + o.getStatus() + "]");
        }
        System.out.println("[1] Process an Order");
        System.out.println("[2] Cancel an Order");
        System.out.println("[3] View Order Details");
        System.out.println("[B] Return to Staff Access Page");
    }
    /**
    * Method to handle user input 
    * @param choice branches the pages
    */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                ProcessOrderService.processOrder(this.session);
                PageViewer.changePage("current");
                break;
            case "2":
                ProcessOrderService.cancelOrder(this.session);
                PageViewer.changePage("current");
                break;
            case "3":
                ProcessOrderService.viewOrderDetails(this.session);
                PageViewer.changePage("current");
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