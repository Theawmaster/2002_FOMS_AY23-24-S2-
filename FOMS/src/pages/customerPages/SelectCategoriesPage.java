package pages.customerPages;

import pages.iPage;
import pages.PageViewer;

/**
 * This page is for the customer to browse the different categories of menu items
 * @author Siah Yee Long
 * @author Lee Jedidiah
 */
public class SelectCategoriesPage implements iPage {
    /**
     * The default constructor for SelectCategoriesPage 
     */
    public SelectCategoriesPage(){}
    /**
     * Method to view customer options
     */
    public void viewOptions(){
        System.out.println("Categories: ");
        System.out.println("[1] Set Meals");
        System.out.println("[2] Burgers");
        System.out.println("[3] Drinks");
        System.out.println("[4] Sides");
        System.out.println("[B] Back ");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                PageViewer.changePage("BrowseSetMealPage");
                break;
            case "2":
                PageViewer.changePage("BrowseBurgerPage");
                break;
            case "3":
                PageViewer.changePage("BrowseDrinksPage");
                break;
            case "4":
                PageViewer.changePage("BrowseSidesPage");
                break;
            case "b":
            case "B":
                PageViewer.changePage("CustomerPage");
                break;
            default:
                System.out.println("Invalid input, please put in a valid input");
                break;
        }
    }
}