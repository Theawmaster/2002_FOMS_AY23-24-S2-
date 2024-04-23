package test.archives;

import pages.iPage;
import pages.PageViewer;
import utilities.Session;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.MenuItem;

public class archiveEditOrderPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public archiveEditOrderPage(Session s){
        this.session = s;
    }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        if(this.session.getCurrentActiveOrder()!=null){
            session.getCurrentActiveOrder().printOrderDetails();
            System.out.println("[1] Remove Item");
            System.out.println("[2] Customise Item");
            System.out.println("[B] Back to View Order Page");
        }else{
            System.out.println("Order hasn't been made!");
            PageViewer.changePage("ViewOrderPage");
        }
        //...
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int attempts = 0;
        switch (choice) {
            case "1":
                while(attempts<3 && !validInput){
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
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please try again!");
                        }
                    }catch (InputMismatchException ime) {
                        System.out.println("Invalid input.");
                        scanner.nextLine();
                    }
                    attempts++;
                }

                if (!validInput) {
                    System.out.println("Failed to remove item after 3 attempts. Returning to View Order page.");
                }

                PageViewer.changePage("back");
                break;
            case "2":
                while(attempts<3 && !validInput){
                    System.out.println("Enter the number of the item you wish to customise:");
                    try{
                        int itemNumber = scanner.nextInt();
                        scanner.nextLine();
                        if(itemNumber > 0 && itemNumber <= session.getCurrentActiveOrder().countTotalItems()){
                            System.out.println("Enter your customization:");
                            String customization = scanner.nextLine();
                            List<MenuItem> items = session.getCurrentActiveOrder().getItems();
                            MenuItem itemToCustomise = items.get(itemNumber - 1); 
                            itemToCustomise.setCustomization(customization);
                        } else{
                            System.out.println("Invalid input. Please try again!");
                        }
                    }catch (InputMismatchException ime) {
                        System.out.println("Invalid input. Please try again!");
                        scanner.nextLine();
                    }
                    attempts++;
                }
                if (!validInput) {
                    System.out.println("Failed to customise item after 3 attempts. Returning to order page.");
                }
                PageViewer.changePage("back");
                break;
            case "B":
            case "b":
                PageViewer.changePage("back");
                break;
                //...
            default:
                System.out.println("Enter a valid input!");
                break;
        }
    }
}
