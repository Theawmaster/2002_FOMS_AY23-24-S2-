package pages.customerPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

import java.util.Scanner;

import entities.Order;

public class CustomerPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public CustomerPage(Session s){
        this.session = s;
    }
    
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        System.out.println("[1] Browse menu");
        System.out.println("[2] View cart");
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
                    pageViewer.changePage("BrowseCategoriesPage");
                    break;
                case "2":
                    pageViewer.changePage("ViewOrderPage");
                    break;
                case "3": 
                    pageViewer.changePage("ViewOrderStatus");
                    break;
                case "B":
                case "b":
                    pageViewer.changePage("MainPage");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    viewOptions();  // Re-display the options for the user
                    break;
            }
        }catch(Exception e){
            System.out.println("An unexpected error occured, please try again!");
            viewOptions();
        }
        
    }
}

