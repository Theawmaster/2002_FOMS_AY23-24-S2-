package pages.customerPages;

import java.util.Scanner;

import constants.OrderStatus;
import pages.iPage;
import pages.pageViewer;
import entities.MenuItem;
import utilities.Session;
import entities.Order;
public class ViewOrderStatus implements iPage{
    private Session session;
    private Scanner scanner;
    
    public ViewOrderStatus(Session s){
        this.session = s;
        this.scanner = new Scanner(System.in);
    }

    public void viewOptions() {
        Order currentOrder = session.getCurrentActiveOrder();
        if (currentOrder == null) {
            System.out.println("No active order found.");
            pageViewer.changePage("CustomerPage");
        } else {
            // Display order details
            currentOrder.printOrderDetails();

            // Provide options based on order status
            switch (currentOrder.getStatus()) {
                case NEW:
                case PREPARING:
                    System.out.println("Your order is not ready yet. It's currently in the kitchen being prepared.");
                    System.out.println("Please wait or press [B] to go back to the previous page.");
                    break;
                case READY_TO_PICKUP:
                    System.out.println("Your order is ready for pickup! Would you like to pick it up now?");
                    System.out.println("[Y] Yes, pick up the order.");
                    System.out.println("[N] No, not yet.");
                    break;
                case COMPLETED:
                    System.out.println("Your order has already been picked up. Thank you!");
                    System.out.println("Press [B] to go back to the main menu.");
                    break;
                default:
                    System.out.println("Your order status is unknown. Please contact support.");
                    System.out.println("Press [B] to go back to the main menu.");
                    break;
            }
            pageViewer.changePage("CustomerPage");
        }
    }
    public void handleInput(String input) {
        // Get the current active order
        Order currentOrder = session.getCurrentActiveOrder();
        if (currentOrder == null) {
            System.out.println("No active order found.");
            pageViewer.changePage("CustomerPage");
            return;
        }

        // Handle user input
        switch (input.trim().toLowerCase()) {
            case "y":
                if (currentOrder.getStatus() == OrderStatus.READY_TO_PICKUP) {
                    // Change the order status to COMPLETED
                    currentOrder.setStatus(OrderStatus.COMPLETED);
                    System.out.println("Thank you for picking up your order. Have a great day!");

                    // Go back to the main customer page
                    pageViewer.changePage("CustomerPage");
                } else {
                    System.out.println("Your order is not ready to pick up yet.");
                }
                break;
            case "n":
                // If the user selects 'No', simply go back to the main customer page
                System.out.println("You can come back to pick up your order later.");
                pageViewer.changePage("CustomerPage");
                break;
            case "1":
                // If the order is ready to pick up and the user presses '1'
                if (currentOrder.getStatus() == OrderStatus.READY_TO_PICKUP) {
                    System.out.println("Please confirm you want to pick up the order. Press 'Y' to confirm or 'N' to cancel.");
                } else {
                    System.out.println("Your order is not ready to pick up yet.");
                }
                break;
            default:
                System.out.println("Invalid input. Please choose a valid option.");
                break;
        }
    }
}
