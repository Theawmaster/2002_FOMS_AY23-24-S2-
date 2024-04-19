package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.List;
import java.util.Scanner;

import constants.FilePaths;
import constants.OrderStatus;
import constants.Role;
import entities.Order;
import pages.pageViewer;
import utilities.Session;

public class ProcessOrderService {
    /**
     * The current active session
     */
    private Session session;

    /**
     * Variable to store the order ID
     */
    private static int storedOrderID;  

    /**
     * Constructor for ProcessOrderService
     * @param s Session
     */
    public ProcessOrderService(Session s) {
        this.session = s;
    }

    /**
     * Method to display list of pending orders & view process order options
     */
    public static void displayProcessOptions() {
        System.out.println("Pending Orders:");
        try {
            List<String> orderLines = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
            System.out.println("Order ID\tStatus");

            for (int i = 1; i < orderLines.size(); i++) {
                String[] parts = orderLines.get(i).split(",");
                int orderID = Integer.parseInt(parts[0].trim());
                String status = parts[1].trim();
                
                // Check if the status is not "READY_TO_PICKUP" or "CANCELLED" before displaying
                if (!status.equalsIgnoreCase("READY_TO_PICKUP") && !status.equalsIgnoreCase("CANCELLED")) {
                    System.out.println(orderID + "\t\t" + status);
                }
            }
            
        } catch (IOException e) {
            System.err.println("An error occurred while reading the order processing list: " + e.getMessage());
        }  
        System.out.println("");
        System.out.println("[1] Process Order");
        System.out.println("[2] View Order Details");
        System.out.println("[B] Return to Staff Access Page");
    }

    /**
     * Method to display orderID details & view process order options
     */
    public static void displayOrderDetailsProcessOptions() {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the order ID
        System.out.print("Enter order ID to view details: ");
        int orderID;
        try {
            String orderIDInput = scanner.nextLine(); 
            orderID = Integer.parseInt(orderIDInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid order ID.");
            ProcessOrderService.displayOrderDetailsProcessOptions();
            return;
        }

        System.out.println("Pending Order Details:");
        try {
            List<String> orderLines = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
            System.out.println("Order ID\tOrder Details\tIs Takeaway");
            boolean orderFound = false; // Flag to check if the order ID is found

            for (int i = 1; i < orderLines.size(); i++) {
                String[] parts = orderLines.get(i).split(",");
                int orderIdInList = Integer.parseInt(parts[0].trim());
                String status = parts[1].trim(); // Assuming status is at index 1

                // Check if the order ID matches
                if (orderID == orderIdInList) {
                    String orderDetails = parts[2].trim();
                    String isTakeaway = parts[3].trim();
                    System.out.println(orderID + "\t" + orderDetails + "\t" + isTakeaway);
                    orderFound = true;
                    // Set the storedOrderID to the entered orderID
                    storedOrderID = orderID;
                    // Check if the order is already processed or cancelled
                    if (status.equals(OrderStatus.READY_TO_PICKUP.toString())) {
                        System.out.println("This order is already processed and ready for pickup.");
                        pageViewer.changePage("StaffProcessOrderPage");
                    } else if (status.equals(OrderStatus.CANCELLED.toString())) {
                        System.out.println("This order has been cancelled.");
                        pageViewer.changePage("StaffProcessOrderPage");
                    } else {
                        // Display process order option and return to staff process order page option
                        System.out.println("");
                        System.out.println("[1] Process Order");
                        System.out.println("[B] Return to Staff Process Order Page");
                    }
                    break;
                }
            }
            if (!orderFound) {
                System.out.println("Order with ID " + orderID + " not found.");
                pageViewer.changePage("StaffProcessOrderPage");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the order processing list: " + e.getMessage());
        }  
    }

    /**
     * Adds an order to the order processing list after payment.
     * @param order The order to be added.
     */
    public static void addOrderToProcessingList(Order order) {
        try {
            // Read all lines from the order processing list
            List<String> lines = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
            // Add new order line
            lines.add(order.getOrderId() + "," + order.getStatus().toString() + "," + "\"" + order.getMenuItemsAsString() + "\"" + "," + (order.isTakeaway()? "Takeaway" : "Dine-in"));
            // Write the updated lines back to the file
            Files.write(Paths.get(FilePaths.orderprocessListPath.getPath()), lines);
        } catch (IOException e) {
            System.err.println("An error occurred while updating the order processing list: " + e.getMessage());
        }
    }
    
    /**
     * Method to process pending orders given user input of order ID
     */
    public static void processOrderWithUserInputOfOrderID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter order ID to process: ");
        int InputOrderID;
        // Read order ID input from the user
        try {
            InputOrderID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid order ID.");
            ProcessOrderService.processOrderWithUserInputOfOrderID();
            return;
        }
        
        try {
            // Read all lines from the order processing list
            List<String> lines = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
            
            // Flag to check if the order ID is found
            boolean orderFound = false;
            
            // Iterate through each line in the file
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                int orderItemID = Integer.parseInt(parts[0].trim());
                
                // Check if the current order ID matches the user input
                if (orderItemID == InputOrderID) {
                    String status = parts[1].trim(); // Assuming status is at index 1

                    // Check if the order is already processed or cancelled
                    if (status.equals(OrderStatus.READY_TO_PICKUP.toString())) {
                        System.out.println("This order is already processed and ready for pickup.");
                        return;
                    } else if (status.equals(OrderStatus.CANCELLED.toString())) {
                        System.out.println("This order has been cancelled.");
                        return;
                    }
                    
                    // Update the status to "READY_TO_PICKUP"
                    parts[1] = OrderStatus.READY_TO_PICKUP.toString();
                    // Update the line
                    lines.set(i, String.join(",", parts)); 
                    // Write the updated lines back to the file
                    Files.write(Paths.get(FilePaths.orderprocessListPath.getPath()), lines); 
                    System.out.println("Order Status Changed to 'Ready For Pickup'!");
                    orderFound = true;
                    break;
                }
            }
            
            // If the order ID is not found, display a message
            if (!orderFound) {
                System.out.println("Order with ID " + InputOrderID + " not found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred in the order processing list: " + e.getMessage());
        }
    }

    /**
     * Method to process the order immediately with the stored order ID from StaffViewOrderDetailsPage
     */
    public static void processOrderImmediately() {
            // Process the order with the stored order ID
            try {
                // Read all lines from the order processing list
                List<String> lines = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));

                // Iterate through each line in the file
                for (int i = 1; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split(",");
                    int orderItemID = Integer.parseInt(parts[0].trim());

                    // Check if the current order ID matches the stored order ID
                    if (orderItemID == storedOrderID) {
                        // Update the status to "READY_TO_PICKUP"
                        parts[1] = OrderStatus.READY_TO_PICKUP.toString();
                        // Update the line
                        lines.set(i, String.join(",", parts));
                        // Write the updated lines back to the file
                        Files.write(Paths.get(FilePaths.orderprocessListPath.getPath()), lines);
                        System.out.println("Order Status Changed to 'Ready For Pickup'!");
                        // Reset the stored order ID to zero after processing
                        storedOrderID = 0;
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred in the order processing list: " + e.getMessage());
            }
    }

    /**
     * Method to reset the stored order ID to zero
     */
    public static void resetStoredOrderID() {
        // Reset storedOrderID to zero
        storedOrderID = 0; 
    }
}