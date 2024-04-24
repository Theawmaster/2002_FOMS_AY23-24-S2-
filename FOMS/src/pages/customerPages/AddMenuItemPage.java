package pages.customerPages;

import pages.iPage;
import services.ProcessOrderService;
import pages.PageViewer;
import utilities.Session;

/**
 * This page is responsible for displaying the details of the menu item that the customer has selected
 * @author Jed
 * @author Siah Yee Long
 */
public class AddMenuItemPage implements iPage{
    /** The running session */
    private Session session;
    /**
     * Constructor for AddMenuItemPage
     * @param s
     */
    public AddMenuItemPage(Session s){
        this.session = s;
    }
    /**
     * This method displays the details of the menu item that the customer has selected
     */
    public void viewOptions(){
        System.out.println("<< " + this.session.getCurrentActiveMenuItem().getFood()+ " >>");
        System.out.println("Price: $ "+this.session.getCurrentActiveMenuItem().getPrice());
        System.out.println("\n'"+this.session.getCurrentActiveMenuItem().getDescription()+"'");
        System.out.println("Category: " + this.session.getCurrentActiveMenuItem().getCategory());
        System.out.println("Branch: " + this.session.getCurrentActiveMenuItem().getBranch().getBranchName());
        if(!this.session.getCurrentActiveMenuItem().getCustomization().equalsIgnoreCase("NA"))
            System.out.println("Customisation: "+this.session.getCurrentActiveMenuItem().getCustomization());
        System.out.println("\nAdd this item to your order?");
        System.out.println("[Y] Yes");
        System.out.println("[N] No");
        System.out.println("[C] Customise order");
    }
    /**
     * Method to handle user input. Calls the relevant ProcessOrderService methods 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "Y":
            case "y":
                ProcessOrderService.addItemToOrder(this.session);
                System.out.println("Item added to your order");
                PageViewer.changePage("ViewOrderPage");
                break;
            case "N":
            case "n":
                System.out.println("Item not added");
                PageViewer.changePage("back");
                break;
            case "c":
            case "C":
                ProcessOrderService.addCustomisationToOrder(this.session.getCurrentActiveMenuItem());
                PageViewer.changePage("current");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
