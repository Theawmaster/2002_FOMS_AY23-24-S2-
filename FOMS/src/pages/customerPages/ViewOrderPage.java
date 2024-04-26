package pages.customerPages;

import pages.iPage;
import pages.PageViewer;
import services.ManagePaymentsService;
import services.ProcessOrderService;
import utilities.Session;
import utilities.UserInputHelper;

/**
 * This is the View Order Page that the user will see after choosing View Cart on Customer Page. 
 * This page offers options to either make payment, edit order or to return back to CustomerPage
 * @author Koh Huei Shan, Winnie
 * @author Siah Yee Long
 */
public class ViewOrderPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    /**
     * Constructor for ViewOrderPage
     * @param s the current session
     */
    public ViewOrderPage(Session s){
        this.session = s;
    }
    /**
     * Method to view Current Order options
     */
    public void viewOptions(){
        if(session.getCurrentActiveOrder() == null || session.getCurrentActiveOrder().countTotalItems() == 0){
            System.out.println("== Your cart is currently empty ==");
            PageViewer.changePage("back");
        }
        else{
            session.getCurrentActiveOrder().printOrderDetails();
            System.out.println("[1] Make Payment");
            System.out.println("[2] Edit Order");
            System.out.println("[B] Continue Browsing");
        }
    }
    /**
     * Method to handle user input. Calls the respective ProcessOrderService and ManagePaymentsService methods based on user input
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                // ask for having here / take away
                System.out.println("Would you like to dine in or takeaway?");
                System.out.println("[1] Dine in");
                System.out.println("[2] Take away");
                System.out.println("[C] Cancel payment");
                if(!UserInputHelper.chooseTakeaway(this.session.getCurrentActiveOrder())){
                    // if user cancelled payment, just go back to this page
                    PageViewer.changePage("current");
                }
                // use chose dine in / takeaway. now ask for money
                else if(ManagePaymentsService.makePayment(this.session, this.session.getCurrentActiveOrder().getOrderId(), session.getCurrentActiveOrder().getTotalPrice())){
                    // money goes through, process the paid order
                    ProcessOrderService.customerPaid(this.session);
                    PageViewer.changePage("MainPage");
                }
                else
                    // transaction cancelled, remain in this page
                    PageViewer.changePage("current");
                break;
            case "2":
                PageViewer.changePage("EditOrderPage");
                break;
            case "b":
            case "B":
                // brings user back to browse the categories  
                PageViewer.changePage("SelectCategoriesPage");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
        }
    }
}