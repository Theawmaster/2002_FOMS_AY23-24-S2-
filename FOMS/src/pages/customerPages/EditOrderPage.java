package pages.customerPages;

import pages.iPage;
import pages.pageViewer;
import services.ManagePaymentsService;
import utilities.Session;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.MenuItem;
import entities.Order;

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
        try{
            session.getCurrentActiveOrder().printOrderDetails();
            System.out.println("[1] Remove Item");
            System.out.println("[2] Customise Item");
            System.out.println("[B] Back to View Order Page");
        }catch(Exception e){
            System.out.println("Order hasn't been made!");
            pageViewer.changePage("ViewOrderPage");
        }
        //...
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case "1":
                System.out.println("Enter the number of the item you wish to remove:");
                try{
                    int itemNumber = scanner.nextInt();
                    scanner.nextLine();
                    if(itemNumber > 0 && itemNumber <= session.getCurrentActiveOrder().countTotalItems()) {
                        List<MenuItem> items = session.getCurrentActiveOrder().getItems();
                        MenuItem itemToRemove = items.get(itemNumber - 1); 
                        if (session.getCurrentActiveOrder().removeItem(itemToRemove)) {
                            System.out.println("Item removed successfully.");
                        } else {
                            System.out.println("Failed to remove the item.");
                        }
                    } else {
                        System.out.println("Invalid item number.");
                    }
                }catch (InputMismatchException ime) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
                pageViewer.changePage("ViewOrderPage");
                break;
            case "2":
                System.out.println("Enter the number of the item you wish to customise:");
                try{
                    int itemNumber = scanner.nextInt();
                    if(itemNumber > 0 && itemNumber <= session.getCurrentActiveOrder().countTotalItems()){
                        String customization = scanner.nextLine();
                        List<MenuItem> items = session.getCurrentActiveOrder().getItems();
                        MenuItem itemToCustomise = items.get(itemNumber - 1); 
                        itemToCustomise.setCustomization(customization);
                    }
                }catch (InputMismatchException ime) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
                pageViewer.changePage("ViewOrderPage");
                break;
            case "B":
            case "b":
                pageViewer.changePage("ViewOrderPage");
                break;
                //...
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Session session = new Session();
        EditOrderPage editorder = new EditOrderPage(session);
        ManagePaymentsService.viewAllPaymentMethods(session);
        editorder.viewOptions();
    }
}
