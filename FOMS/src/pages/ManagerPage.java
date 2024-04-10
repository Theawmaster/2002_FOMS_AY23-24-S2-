package pages;

import entities.MenuItem;
import entities.Order;
import entities.MenuManager;
import constants.OrderStatus;
import constants.MealCategory;
import utilities.LoadStaffs;
import java.util.Scanner;

public class ManagerPage {
     /**
     * Constructor for ManagerPage class.
     * Creates a new instance of the ManagerPage and displays the manager menu.
     */
    public ManagerPage() {
        showManagerMenu(); // Show the manager menu upon creation
    }

    /**
     * Displays the manager menu and handles manager commands.
     * Allows managers to simulate orders or return to the login page.
     */
    public void showManagerMenu() {

        Scanner scanner = new Scanner(System.in);


        String command;

        do {
            System.out.println("                    _______                                          ______                                       ");
            System.out.println(" ______ ______     |   |   |.---.-.-----.---.-.-----.-----.----.    |   __ \\.---.-.-----.-----.     ______ ______ ");
            System.out.println("|______|______|    |       ||  _  |     |  _  |  _  |  -__|   _|    |    __/|  _  |  _  |  -__|    |______|______|");
            System.out.println("                   |__|_|__||___._|__|__|___._|___  |_____|__|      |___|   |___._|___  |_____|                   ");
            System.out.println("                                              |_____|                             |_____|                          ");
            System.out.println("Manager Commands:");
            System.out.println("  1 - Display Orders");
            System.out.println("  2 - View OrderId order details");
            System.out.println("  3 - Process order of OrderId");
            System.out.println("  4 - Menu Management");
            System.out.println("  5 - View all staffs");
            System.out.println("  q - Return to Login Page");
            System.out.print("\nEnter manager command: ");
            command = scanner.nextLine().trim();

            switch (command) {
                case "1":
                    simulateOrders(); 
                    break;
                case "2":
                    // to add viewMyStaff method
                    break;
                case "3":
                
                    break;
                case "4":
                    MenuManager menuManager = new entities.MenuManager();
                    menuManager.runMenuManagement();
                    break;
                case "5":
// The code snippet `LoadStaffs loadStaffs = new LoadStaffs(); loadStaffs.viewAllStaff();` is creating
// an instance of the `LoadStaffs` class and then calling the `viewAllStaff()` method on that instance.
                    LoadStaffs loadStaffs = new LoadStaffs();
                    loadStaffs.viewAllStaff();
                    break;
                case "q":
                    System.out.println("Returning to Login Page.");
                    new LoginPage().showLoginPage(); // Return to the login page
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equals("q"));
                // Close the scanner to prevent resource leak
                scanner.close();
    }

    /**
     * Simulates the process of creating and tracking orders.
     */
    private void simulateOrders() {
        // Create a new order for takeaway
        Order takeawayOrder = new Order(true);
        
        // Create menu items
        MenuItem burger = new MenuItem("Burger", 5.99, MealCategory.BURGER, "na", "Delicious burger");
        MenuItem fries = new MenuItem("Fries", 2.49, MealCategory.SIDE, "na", "Crispy fries");
    
        // Add items to the order with customization
        takeawayOrder.addItem(burger, "No onions");
        takeawayOrder.addItem(fries, "Extra crispy");
    
        // Print order details
        System.out.println("Takeaway Order Details:");
        takeawayOrder.printOrderDetails();
        System.out.println();
    
        // Create a new order for dine-in
        Order dineInOrder = new Order(false);
    
        // Add items to the order with customization
        dineInOrder.addItem(burger, "with onions");
        dineInOrder.addItem(fries, "no crispy");
    
        // Print order details
        System.out.println("Dine-in Order Details:");
        dineInOrder.printOrderDetails();
        System.out.println();
    
        // Simulate order tracking
        dineInOrder.setStatus(OrderStatus.PREPARING);
        System.out.println("Order Status: " + dineInOrder.getStatus());
        System.out.println();
    
        // Simulate order cancellation
        dineInOrder.setStatus(OrderStatus.READY_TO_PICKUP);
        System.out.println("Order Status Before Cancellation: " + dineInOrder.getStatus());
        if (dineInOrder.hasExceededTimeframeForPickup(30)) { // Assuming hasExceededTimeframeForPickup is implemented
            dineInOrder.setStatus(OrderStatus.CANCELLED);
            System.out.println("Order automatically cancelled due to exceeding pickup timeframe.");
            System.out.println("Order Status After Cancellation: " + dineInOrder.getStatus());
        }
    }    
}
