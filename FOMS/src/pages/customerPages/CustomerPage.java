package pages.customerPages;

import pages.iPage;
import pages.PageViewer;
import services.ProcessOrderService;
import utilities.Session;

/**
 * This page is for the customer. It allows the customer to browse the menu, view cart, make payment, edit order, view order status and go back to the main page.
 * @author Siah Yee Long
 * @author Jed
 */
public class CustomerPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    /**
     * Constructor for CustomerPage
     * @param session the current session
     */
    public CustomerPage(Session session){ this.session = session; }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        System.out.println("[1] Browse menu");
        System.out.println("[2] View Cart, Make Payment or Edit Order");
        System.out.println("[3] View Order status");
        System.out.println("[B] Back to main page ");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */

    public void handleInput(String choice){
        try{
            switch (choice) {
                case "1":
                    PageViewer.changePage("SelectCategoriesPage");
                    break;
                case "2":
                    PageViewer.changePage("ViewOrderPage");
                    break;
                case "3": 
                    ProcessOrderService.custViewOrderStatus(this.session.getAllOrders(), this.session.getCurrentActiveBranch());
                    PageViewer.changePage("current");
                    break;
                case "B":
                case "b":
                    PageViewer.changePage("back");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }catch(Exception e){
            System.out.println("An unexpected error occured, please try again!");
            viewOptions();
        }
        
    }
}

