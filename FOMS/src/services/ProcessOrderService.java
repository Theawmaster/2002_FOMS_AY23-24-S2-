package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import constants.FilePaths;
import constants.OrderStatus;

public class ProcessOrderService {

    public static void displayProcessOptions() {
        System.out.println("Order Options:");
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
            System.err.println("An error occurred while reading order processing list: " + e.getMessage());
        }
        System.out.println("[1] Process Order");
        System.out.println("[2] View Order Details");
        System.out.println("[B] Return to Staff Access Page");
    }

    public static void processOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter orderID to process: ");
        int orderID;
            try {
                orderID = Integer.parseInt(scanner.nextLine());
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
                    if (orderItemID == orderID) {
                        // Update the status to "READY_TO_PICKUP"
                        parts[1] = OrderStatus.READY_TO_PICKUP.toString();
                        lines.set(i, String.join(",", parts)); // Update the line
                        Files.write(Paths.get(FilePaths.orderprocessListPath.getPath()), lines); // Write the updated lines back to the file
                        System.out.println("Order Status Changed to 'Ready For Pickup'!");
                        orderFound = true;
                        break;
                    }
                }
                // If the order ID is not found, display a message
                if (!orderFound) {
                    System.out.println("Order with ID " + orderID + " not found.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading or writing the order processing list: " + e.getMessage());
            }
    }     
}