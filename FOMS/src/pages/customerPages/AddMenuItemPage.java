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
    private static int Orderid = 1;

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

        boolean validInputReceived = false;
        boolean validInput = false;

        while(!validInputReceived){
            try{
                // Prompt user to add to cart or not 
                System.out.println("Would you like to add "+ selectedItem.getFood() +" to your order? ");
                System.out.println("[Y] Yes");
                System.out.println("[N] No");
                
                String userdecision = scanner.nextLine().trim().toLowerCase();
                switch(userdecision){
                    case "y":
                    case "Y":
                        addToOrder(selectedItem,"", input.isEmpty() ? "Standard" : input);
                        System.out.println("Item added to your order.");

                        while(!validInput){
                            try{
                                System.out.println("Would you like to proceed to payment or edit your order?");
                                System.out.println("[Y] Yes, proceed to payment / edit order");
                                System.out.println("[N] No, add more items");
                                String choice = scanner.nextLine().trim().toLowerCase();
                                switch(choice){
                                    case "y":
                                    case "Y":
                                        pageViewer.changePage("ViewOrderPage");
                                        validInput = true;
                                        break;
                                    case "n":
                                    case "N":
                                        pageViewer.changePage("CustomerPage");
                                        validInput = true;
                                        break;
                                    default:
                                        System.out.println("Invalid input please try again!");
                                        break;
                                }
                            }catch(Exception e){
                                System.out.println("Invalid input please try again!");
                            }
                        }
                        validInputReceived = true;
                        break;
            
                    case "n":
                    case "N":
                        System.out.println("Item not added");
                        pageViewer.changePage("CustomerPage");
                        validInputReceived = true;
                        break;
                    default:
                        System.out.println("Invalid input please try again!");
                        break;
                }
            }catch(Exception e){
                System.out.println("Invalid input please try again!");
            }
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
        return Orderid++;
    }
}