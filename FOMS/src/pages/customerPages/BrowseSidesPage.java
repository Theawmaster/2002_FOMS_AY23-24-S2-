package pages.customerPages;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import pages.iPage;
import pages.pageViewer;
import utilities.Session;
import entities.Order;
import entities.Branch;
import entities.MenuItem;
import constants.MealCategory;

public class BrowseSidesPage implements iPage{
    Scanner scanner = new Scanner(System.in);
    /**
     * The current active session 
     */
    private Session session;
    private Order order;
    private List <MenuItem> displayedMenuItems;
    public BrowseSidesPage(Session s){
        this.session =s;
        displayedMenuItems = new ArrayList<>();
    }
    public BrowseSidesPage(Session s, Order order){
        this.session = s;
        if (session.getCurrentActiveOrder() == null) {
            int id = 2002; // need to generate a order id
            this.order = new Order(id);
            session.setCurrentActiveOrder(this.order); // Set the new order as active in the session
        } else {
            this.order = session.getCurrentActiveOrder(); // Use the existing active order
        }
    }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        int numOptions = 0;
        System.out.println("Select the Sides you would like: ");
        Branch currentBranch = this.session.getCurrentActiveBranch();
        String currentBranchName = currentBranch.getBranchName();  
        for(MenuItem m : this.session.getAllMenuItems()){
            // Check if the category of the menu item is SETMEAL
            if(m.getCategory() == MealCategory.SIDE && m.getBranch().getBranchName().equals(currentBranchName)){
                numOptions++;
                displayedMenuItems.add(m);
                System.out.println("[" + numOptions + "] " + m.getFood() + " - $" + m.getPrice());
            }
        }
        if(numOptions == 0) {
            System.out.println("No Sides available.");
        }
        System.out.println("[B] Go back to the previous page ");
        System.out.println("Select an item to add to your cart: ");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String input){
        if(input.equalsIgnoreCase("b")){
            pageViewer.changePage("BrowseCategoriesPage");
            return;
        }
        
        try {
            int choiceIndex = Integer.parseInt(input) - 1; // Convert to zero-based index
            if (choiceIndex >= 0 && choiceIndex < displayedMenuItems.size()) {
                MenuItem selectedItem = displayedMenuItems.get(choiceIndex);
                pageViewer.navigateToAddMenuItemPage(selectedItem);
            } else {
                System.out.println("Invalid choice. Please select a number from the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}