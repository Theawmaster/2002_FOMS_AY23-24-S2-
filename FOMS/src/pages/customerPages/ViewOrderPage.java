package pages.customerPages;

import pages.iPage;
import pages.pageViewer;
import services.ManagePaymentsService;
import utilities.Session;
import entities.Order;
import constants.OrderStatus;
import services.ProcessOrderService;

/**
 * This is the View Order Page that the user will see after choosing View Cart on Customer Page. 
 * This page offers options to either make payment, edit order or to return back to CustomerPage
 * @author Winnie
 */

public class ViewOrderPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public ViewOrderPage(Session s){
        this.session = s;
    }
    /**
     * Method to view Current Order options
     */
    public void viewOptions(){
        if(session.getCurrentActiveOrder() == null){
            System.out.println("Your cart is empty");
            pageViewer.changePage("CustomerPage");
        }
        else{
            System.out.println("[1] Make Payment");
            System.out.println("[2] Edit Order");
            System.out.println("[3] Continue Browsing");
        }
        //...
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                ManagePaymentsService.makePayment(session, session.getCurrentActiveOrder().getOrderId(), session.getCurrentActiveOrder().getTotalPrice());
                session.getCurrentActiveOrder().setStatus(OrderStatus.PREPARING);
                ProcessOrderService.addOrderToProcessingList(session.getCurrentActiveOrder());
                session.getCurrentActiveOrder().printOrderDetails();
                // session.setCurrentActiveOrder(null);
                pageViewer.changePage("CustomerPage");
                break;
            case "2":
                pageViewer.changePage("EditOrderPage");
                break;
            case "3":
                pageViewer.changePage("CustomerPage");
                break;
                //...
            default:
                System.out.println("Invalid choice, please try again.");
                break;
        }
    }
}