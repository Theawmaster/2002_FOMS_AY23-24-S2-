package pages.customerPages;

import pages.iPage;
import pages.pageViewer;

public class CustomerPage implements iPage{
    /**
     * The current active session 
     */
    public CustomerPage(){}
    
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        System.out.println("[1] Browse menu");
        System.out.println("[2] View Cart, Make Payment or Edit Order");
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
                    pageViewer.changePage("back");
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

