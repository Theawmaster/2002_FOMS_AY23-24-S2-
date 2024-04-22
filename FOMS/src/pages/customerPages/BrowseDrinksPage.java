package pages.customerPages;

import java.util.ArrayList;
import pages.iPage;
import pages.PageViewer;
import utilities.Session;
import entities.Branch;
import entities.MenuItem;
import constants.MealCategory;

public class BrowseDrinksPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    /**
     * The list of drinks
     */
    private ArrayList<MenuItem> displayedMenuItems;

    public BrowseDrinksPage(Session s){
        this.session = s;
    }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        System.out.println("Select the drinks you would like: ");
        Branch currentBranch = this.session.getCurrentActiveBranch();
        String currentBranchName = currentBranch.getBranchName();  
        displayedMenuItems = new ArrayList<>();

        int numOptions = 0;
        for(MenuItem m : this.session.getAllMenuItems()){
            // Check if the category of the menu item is SETMEAL
            if(m.getCategory() == MealCategory.DRINK && m.getBranch().getBranchName().equals(currentBranchName)){
                numOptions++;
                displayedMenuItems.add(m);
                System.out.println("[" + numOptions + "] " + m.getFood() + " - $" + m.getPrice());
            }
        }
        if(numOptions == 0) {
            System.out.println("No drinks available in this branch. Sorry!");
        }
        System.out.println("[B] Go back to the previous page ");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        if(choice.equalsIgnoreCase("b")){
            PageViewer.changePage("back");
            return;
        }
        try {
            int choiceIndex = Integer.parseInt(choice) - 1; // Convert to zero-based index
            if (choiceIndex >= 0 && choiceIndex < displayedMenuItems.size()) {
                MenuItem selectedItem = displayedMenuItems.get(choiceIndex);
                this.session.setCurrentActiveMenuItem(selectedItem);
                PageViewer.changePage("AddMenuItemPage");
            } else {
                System.out.println("Invalid choice. Please select a number from the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}