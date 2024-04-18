package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import constants.FilePaths;
import constants.OrderStatus;
import constants.Role;
import pages.pageViewer;
import utilities.Session;

public class ProcessOrderService {
    /**
     * The current active session
     */
    private Session session;
    public ProcessOrderService(Session s) {
        this.session = s;
    }
    /**
    * Method to display list of pending orders & view process order options
    */
    public static void displayProcessOptions() {
            System.out.println("Pending Orders");
            try {
                List<String> orderLines = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
                if (orderLines.isEmpty()) {
                    System.out.println("No orders available.");
                } else {
                    System.out.println("Order ID\tStatus");
                    for (int i = 1; i < orderLines.size(); i++) {
                        String[] parts = orderLines.get(i).split(",");
                        int orderID = Integer.parseInt(parts[0].trim());
                        String status = parts[1].trim();
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
    * Method to process pending orders given user input of order ID
    */
    public static void processOrderWithUserInputOfOrderID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter orderID to process: ");
        int InputOrderID;
        // Read order ID input from the user
            try {
                InputOrderID = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid order ID.");
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
    * Method to process pending orders with insert of order ID parameter 
    */
    public static void processOrderWithGivenID(int orderID) {
        try {
            // Read all lines from the order processing list
            List<String> lines = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
    
            // Iterate through each line in the file
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                int orderItemID = Integer.parseInt(parts[0].trim());
                
                // Check if the current order ID matches the user input
                if (orderItemID == orderID) {
                    // Update the status to "READY_TO_PICKUP"
                    parts[1] = OrderStatus.READY_TO_PICKUP.toString();
                    // Update the line
                    lines.set(i, String.join(",", parts)); 
                    // Write the updated lines back to the file
                    Files.write(Paths.get(FilePaths.orderprocessListPath.getPath()), lines); 
                    System.out.println("Order Status Changed to 'Ready For Pickup'!");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred in the order processing list: " + e.getMessage());
        }
    }
    /**
    * Method to enter orderId to display order details
    */
    public static void UserInputCurrentOrderDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter order ID: ");
        // Read order ID input from the user
        int orderID;
        try {
            String orderIDInput = scanner.nextLine(); 
            orderID = Integer.parseInt(orderIDInput);
            // Call processOrder with the order ID obtained
            processOrderWithGivenID(orderID);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid order ID.");
            return;
        }
    }
}