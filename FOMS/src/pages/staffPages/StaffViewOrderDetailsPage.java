package pages.staffPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;
import entities.Order;
import constants.OrderStatus;

public class StaffViewOrderDetailsPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public StaffViewOrderDetailsPage(Session s){
        this.session = s;
        showOrderDetails();
    }
    /**
     * Method to view order details options
     */
    public void viewOptions(){
        System.out.println("[1] Process Order");
        System.out.println("[B] Return to Staff Process Order Page");
    }
    /**
     * Method to show the order details of the orderID
     */
    private void showOrderDetails(){
        // Fetch the order details from Order class
        Order order = new Order(0); // put zero as for now
        order.printOrderDetails();
        }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                Order order = new Order(0); // put zero as for now
                order.setStatus(OrderStatus.READY_TO_PICKUP);
                System.out.println("Order Status Changed to '" + order.getStatus().toString() + "'!");
                break;
            case "b":
                pageViewer.changePage("StaffProcessOrderPage");
                break;
            case "B":
                pageViewer.changePage("StaffProcessOrderPage");
                break;
            default:
                break;
        }
    }
}
