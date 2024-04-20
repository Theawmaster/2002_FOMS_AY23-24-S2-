package pages.customerPages;

import java.util.Scanner;
import pages.iPage;
import pages.pageViewer;
import entities.MenuItem;
import utilities.Session;
import entities.Order;

public class AddMenuItemPage implements iPage{
    private Scanner scanner = new Scanner(System.in);
    private Session session;
    private MenuItem selectedItem;
    private static int Orderid;

    public AddMenuItemPage(Session s){
        this.session = s;
    }

    public AddMenuItemPage(Session session, MenuItem selectedItem) {
        this.session = session;
        this.selectedItem = selectedItem;
    }

    public void viewOptions(){
        System.out.println("You have selected: " + selectedItem.getFood());
        System.out.println("Price: $ "+selectedItem.getPrice());
        System.out.println("Branch: " + selectedItem.getBranch().getBranchName());
        System.out.println("Category: " + selectedItem.getCategory());

        System.out.println("Would you like any customization options: ");
        System.out.println("If no customizations, just press 'Enter' ");
        String customization = scanner.nextLine();
        handleInput(customization);
    }

    public void handleInput(String input){
        // Add customization details to the item, if any
        selectedItem.setCustomization(input.isEmpty() ? "NA" : input);
        // Prompt user to add to cart or not 
        System.out.println("Would you like to add "+ selectedItem.getFood() +" to your order? ");
        System.out.println("[Y] Yes");
        System.out.println("[N] No");

        String userdecision = scanner.nextLine().trim().toLowerCase();
        if(userdecision.equals("y")){
            // We add the users order to the current session
            addToOrder(selectedItem,"", input.isEmpty() ? "Standard" : input);
            System.out.println("Item added to your order.");

            // Ask the user if they are done with ordering, redirect them accordingly
            // Ask if the user is done with ordering
            System.out.println("Are you ready to proceed to payment? ");
            System.out.println("[Y] Yes, proceed to payment");
            System.out.println("[N] No, add more items");

            String userDecision = scanner.nextLine().trim().toLowerCase();
            if (userDecision.equals("y")) {
                // Direct user to the ViewOrderPage
                pageViewer.changePage("ViewOrderPage");
            } else {
                // Direct user back to BrowseCategoriesPage to add more items
                pageViewer.changePage("BrowseCategoriesPage");
            }
        }
        else{
            // Inform User his order was not added
            System.out.println("Item not added");
            pageViewer.changePage("BrowseCategoriesPage");
        }
    }
    


    private void addToOrder(MenuItem Item, String Description, String Customization){
        // Adds the item to the current active order
        Order currentOrder = session.getCurrentActiveOrder();
        if (currentOrder == null) {
            currentOrder = new Order(generateOrderId());
            session.setCurrentActiveOrder(currentOrder);
        }
        currentOrder.addItem(Item, Description, Customization);
        System.out.println("Current order details: ");
        currentOrder.printOrderDetails();
    }

    private static int generateOrderId(){
        int orderid = 1;
        return orderid++;
    }
}