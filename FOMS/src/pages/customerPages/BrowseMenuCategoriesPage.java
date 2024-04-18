package pages.customerPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

public class BrowseMenuCategoriesPage implements iPage {
    /**
     * The current active session 
     */
    private Session session;
    public BrowseMenuCategoriesPage(Session s){
        this.session = s;
    }
    /**
     * Method to view staff access options
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
            case "2":
            case "3":
                pageViewer.changePage("BrowseMenuItemsPage");
                break;
            case "b":
            case "B":
                pageViewer.changePage("CustomerPage");
                break;
            default:
                System.out.println("Invalid input, please put in a valid input");
                break;
        }
    }
}