package pages.customerPages;

import pages.iPage;
import pages.PageViewer;
import services.ManagePaymentsService;
import services.ProcessOrderService;
import test.exampleShowStaff;
import utilities.Session;

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
            System.out.println("Your cart is currently empty");
            PageViewer.changePage("back");
        }
        else{
            session.getCurrentActiveOrder().printOrderDetails();
            System.out.println("[1] Make Payment");
            System.out.println("[2] Edit Order");
            System.out.println("[B] Continue Browsing");
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
                if(ManagePaymentsService.makePayment(this.session, this.session.getCurrentActiveOrder().getOrderId(), session.getCurrentActiveOrder().getTotalPrice())){
                    ProcessOrderService.customerPaid(this.session);
                    PageViewer.changePage("MainPage");
                }
                else
                    PageViewer.changePage("current");
                break;
            case "2":
                PageViewer.changePage("EditOrderPage");
                break;
            case "b":
            case "B":
                // brings user back to browse the categories  
                PageViewer.changePage("BrowseCategoriesPage");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
        }
    }
}