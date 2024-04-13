package pages.staffPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;
import entities.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import constants.FilePaths;
import constants.OrderStatus;

public class StaffProcessOrderPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public StaffProcessOrderPage(Session s){
        this.session = s;
        showProcessingOrders();
    }
    /**
     * Method to view process order options
     */
    public void viewOptions(){
        System.out.println("[1] Process Order");
        System.out.println("[2] View Order Details");
        System.out.println("[B] Return to Staff Access Page");
    }
    /**
     * Method to view list of processing orders
     */
    public void showProcessingOrders() {
        try {
            List<String> processingorder = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
            if (processingorder.isEmpty()) {
                System.out.println("The order processing list is currently empty.");
                return;
            } else {
                System.out.println("orderID,Status,isittakeaway");
                for (int i = 1; i < processingorder.size(); i++) {
                    System.out.println((i) + ". " + processingorder.get(i));
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading order processing list: " + e.getMessage());
        }
    }
    /**
    * Method to handle user input 
    * @param choice branches the pages
    */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                Scanner scanner1 = new Scanner(System.in);
                System.out.print("Enter orderID to process: ");
                int orderitemIndex1;
                try {
                    orderitemIndex1 = Integer.parseInt(scanner1.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid order ID.");
                    return; 
                }
                
                Order order1 = new Order(orderitemIndex1); 
                order1.setStatus(OrderStatus.READY_TO_PICKUP);
                System.out.println("Order Status Changed to '" + order1.getStatus().toString() + "'!");
                break;
            case "2":
                pageViewer.changePage("StaffViewOrderDetailsPage");
                break;
            case "b":
                pageViewer.changePage("StaffAccessPage");
                break;
            case "B":
                pageViewer.changePage("StaffAccessPage");
                break;
            default:
                break;
        }
    }
}
