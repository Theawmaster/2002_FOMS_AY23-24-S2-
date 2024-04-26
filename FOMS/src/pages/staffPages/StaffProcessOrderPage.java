package pages.staffPages;

import entities.Order;
import pages.iPage;
import pages.PageViewer;
import utilities.Session;
import services.ProcessOrderService;

/**
 * This page is responsible for displaying and handling inputs for staff to process orders
 * @author Siah Yee Long
 * @author Chan Zi Hao
 */
public class StaffProcessOrderPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    /**
     * Constructor for the StaffProcessOrderPage class
     * @param s the current session
     */
    public StaffProcessOrderPage(Session s){
        this.session = s;
    }
    /**
     * Method to display list of pending orders and view process order options
     */
    public void viewOptions(){
        boolean hasPendingOrders = false;
        for(Order o : this.session.getAllOrders()){
            if(o.getBranchName().equalsIgnoreCase(this.session.getCurrentActiveBranch().getBranchName())){
                System.out.println("Order number: " + o.getOrderId() + " [" + o.getStatus() + "]");
                hasPendingOrders = true;
            }
        }
        if (!hasPendingOrders) {
            System.out.println("== There are no pending orders at the moment ==");
        }
        System.out.println();
        System.out.println("[1] Prepare an Order");
        System.out.println("[2] Cancel an Order");
        System.out.println("[3] View Order Details");
        System.out.println("[B] Return to Staff Access Page");
    }
    /**
    * Method to handle user input. Calls the respective ProcessOrderService methods based on user input
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