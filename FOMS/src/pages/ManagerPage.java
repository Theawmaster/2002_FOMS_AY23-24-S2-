package pages;

import entities.MenuItem;
import entities.Order;
import constants.OrderStatus;
import constants.MealCategory;
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
    private void showManagerMenu() {

        System.out.println("    ______  _______         _____  _____   ______         _____         _____         ______        _____              _____         _____         _____         ______   ");
        System.out.println("   |      \\/       \\    ___|\\    \\|\\    \\ |\\     \\    ___|\\    \\    ___|\\    \\    ___|\\     \\   ___|\\    \\         ___|\\    \\    ___|\\    \\    ___|\\    \\    ___|\\     \\  ");
        System.out.println("  /          /\\     \\  /    /\\    \\| \\    \\  \\     \\  /    /\\    \\  /    /\\    \\  |     \\     \\ |    |\\    \\       |    |\\    \\  /    /\\    \\  /    /\\    \\  |     \\     \\ ");
        System.out.println(" /     /\\   / /\\     ||    |  |    |\\|    \\  \\     ||    |  |    ||    |  |____| |     ,_____/||    | |    |      |    | |    ||    |  |    ||    |  |____| |     ,_____/|");
        System.out.println("/     /\\ \\_/ / /    /||    |__|    | |     \\  |    ||    |__|    ||    |    ____ |     \\--'\\_|/|    |/____/       |    |/____/||    |__|    ||    |    ____ |     \\--'\\_|/");
        System.out.println("|     |  \\|_|/ /    / ||    .--.    | |      \\ |    ||    .--.    ||    |   |    ||     /___/|  |    |\\    \\       |    ||    |||    .--.    ||    |   |    ||     /___/|  ");
        System.out.println("|     |       |    |  ||    |  |    | |    |\\ \\|    ||    |  |    ||    |   |_,  ||     \\____|\\ |    | |    |      |    ||____|/|    |  |    ||    |   |_,  ||     \\____|\\ ");
        System.out.println("|\\____\\       |____|  /|____|  |____| |____||\\_____/||____|  |____||\\ ___\\___/  /||____ '     /||____| |____|      |____|       |____|  |____||\\ ___\\___/  /||____ '     /|");
        System.out.println("| |    |      |    | / |    |  |    | |    |/ \\|   |||    |  |    || |   /____ / ||    /_____/ ||    | |    |      |    |       |    |  |    || |   /____ / ||    /_____/ |");
        System.out.println("| |____|      |____|/  |____|  |____| |____|   |___|/|____|  |____| \\|___|    | / |____|     | /|____| |____|      |____|       |____|  |____| \\|___|    | / |____|     | / ");
        System.out.println("|    \\(          )/       \\(      )/     \\(       )/    \\(      )/     \\( |____|/    \\( |_____|/   \\(     )/          \\(           \\(      )/     \\( |____|/    \\( |_____|/ ");
        System.out.println("|     '          '         '      '       '       '      '      '       '   )/        '    )/       '     '            '            '      '       '   )/        '    )/    ");
        System.out.println("                                                                           '              '                                                           '              '      ");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Manager Commands:");
        System.out.println("  1 - Simulate Orders");
        System.out.println("  q - Return to Login Page");

        String command;
        do {
            System.out.print("\nEnter manager command: ");
            command = scanner.nextLine().trim();

            switch (command) {
                case "1":
                    simulateOrders(); 
                    break;
                case "q":
                    System.out.println("Returning to Login Page.");
                    new LoginPage().showLoginPage(); // Return to the login page
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equals("q"));
    }

    /**
     * Simulates the process of creating and tracking orders.
     */
    private void simulateOrders() {
        // Create a new order for takeaway
        Order takeawayOrder = new Order(true);
        
        // Create menu items
        MenuItem burger = new MenuItem("Burger", 5.99, MealCategory.BURGER, "na");
        MenuItem fries = new MenuItem("Fries", 2.49, MealCategory.SIDE, "na");

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
