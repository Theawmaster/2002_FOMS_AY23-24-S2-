package pages.customerPages;

import pages.iPage;
import pages.pageViewer;

public class BrowseCategoriesPage implements iPage {
    /**
     * The current active session 
     */
    public BrowseCategoriesPage(){}
    /**
     * Method to view customer options
     */
    public void viewOptions(){
        System.out.println("Categories: ");
        System.out.println("[1] Set Meals");
        System.out.println("[2] Drinks");
        System.out.println("[3] Sides");
        System.out.println("[B] Back ");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                pageViewer.changePage("BrowseSetMealPage");
                break;
            case "2":
                pageViewer.changePage("BrowseDrinksPage");
                break;
            case "3":
                pageViewer.changePage("BrowseSidesPage");
                break;
            case "b":
            case "B":
                pageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid input, please put in a valid input");
                break;
        }
    }
}