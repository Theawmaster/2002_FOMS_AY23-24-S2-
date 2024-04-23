package pages.customerPages;

import pages.iPage;
import services.ProcessOrderService;
import pages.PageViewer;
import utilities.Session;

public class EditOrderPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public EditOrderPage(Session s){
        this.session = s;
    }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        if(this.session.getCurrentActiveOrder()!=null){
            session.getCurrentActiveOrder().printOrderDetails();
            System.out.println("[1] Remove Item");
            System.out.println("[2] Customise Item");
            System.out.println("[B] Back to View Order Page");
        }else{
            System.out.println("Order hasn't been made!");
            PageViewer.changePage("ViewOrderPage");
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
                System.out.println("-- Removing an order --");
                ProcessOrderService.removeOrder(this.session);
                PageViewer.changePage("back");
                break;
            case "2":
                System.out.println("-- Customising an order --");
                ProcessOrderService.customiseAnOrder(this.session);
                PageViewer.changePage("back");
                break;
            case "B":
            case "b":
                PageViewer.changePage("back");
                break;
                //...
            default:
                System.out.println("Enter a valid input!");
                break;
        }
    }
}
