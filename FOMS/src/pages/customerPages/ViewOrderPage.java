package pages.customerPages;

import pages.iPage;
import pages.pageViewer;
import services.ManagePaymentsService;
import utilities.Session;

import java.util.Scanner;

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
        if(session.getCurrentActiveOrder() == null || session.getCurrentActiveOrder().countTotalItems() == 0){
            System.out.println("Your cart is empty");
            pageViewer.changePage("back");
        }
        else{
            session.getCurrentActiveOrder().printOrderDetails();
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
                session.getCurrentActiveOrder().setTakeaway(askForOrderType());
                ManagePaymentsService.makePayment(session, session.getCurrentActiveOrder().getOrderId(), session.getCurrentActiveOrder().getTotalPrice());
                session.getCurrentActiveOrder().setStatus(OrderStatus.PREPARING);
                ProcessOrderService.addOrderToProcessingListinCSV(session.getCurrentActiveOrder());
                session.getCurrentActiveOrder().printOrderDetails();
                session.addOrder(session.getCurrentActiveOrder());
                session.setCurrentActiveOrder(null);
                pageViewer.changePage("CustomerPage");
                break;
            case "2":
                pageViewer.changePage("EditOrderPage");
                break;
            case "3":
                pageViewer.changePage("back");
                break;
                //...
            default:
                System.out.println("Invalid choice, please try again.");
                viewOptions();
                break;
        }
    }

    // method to get order type
    private boolean askForOrderType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you dining in or taking away?"); 
        System.out.println("[1] Takeaway");
        System.out.println("[2] Dine-in");
        
        while (true) {
            String response = scanner.nextLine().trim();
            if (response.equals("1")) {
                return true; // Takeaway
            } else if (response.equals("2")) {
                return false; // Dine-in
            } else {
                System.out.println("Invalid input, please select '1' for Takeaway or '2' for Dine-in.");
            }
        }
    }
}