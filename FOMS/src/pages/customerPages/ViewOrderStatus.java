package pages.customerPages;

import java.util.Scanner;

import constants.OrderStatus;
import pages.iPage;
import pages.pageViewer;
import services.ProcessOrderService;
import utilities.Session;
import entities.Order;
public class ViewOrderStatus implements iPage{
    private Session session;
    private Scanner scanner;
    private Order currentOrder;
    
    public ViewOrderStatus(Session s){
        this.session = s;
        this.scanner = new Scanner(System.in);
    }

    public void viewOptions() {
        System.out.println("Please enter your order ID");
        int orderID;
        try {
            orderID = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid order ID format. Please enter a valid number.");
            return;
        }

        // Attempt to retrieve the order using the orderId
        currentOrder = session.getOrderById(orderID);

        if (currentOrder == null) {
            System.out.println("No active order found.");
            pageViewer.changePage("back");
        } else {
            // Display order details
            currentOrder.printOrderDetails();

            // Provide options based on order status
            switch (currentOrder.getStatus()) {
                case NEW:
                case PREPARING:
                    System.out.println("Your order is not ready yet. It's currently in the kitchen being prepared.");
                    pageViewer.changePage("back");
                    break;
                case READY_TO_PICKUP:
                    System.out.println("Your order is ready for pickup! Would you like to pick it up now?");
                    System.out.println("[Y] Yes, pick up the order.");
                    System.out.println("[N] No, not yet.");
                    break;
                case CANCELLED:
                    System.out.println("Your order has been cancelled. Please contact support for more information.");
                    pageViewer.changePage("back");
                    break;
                case COMPLETED:
                    System.out.println("Your order is completed! Please contact support for more information if you haven't received it.");
                    pageViewer.changePage("back");
                    break;
                default:
                    System.out.println("Your order status is unknown. Please contact support.");
                    pageViewer.changePage("back");
                    break;
            }
        }
    }
    public void handleInput(String input) {
        // Handle user input
        switch (input.trim().toLowerCase()) {
            case "y":
                if(currentOrder != null && currentOrder.getStatus() == OrderStatus.READY_TO_PICKUP){
                    // Change the order status to COMPLETED
                    currentOrder.setStatus(OrderStatus.COMPLETED);
                    ProcessOrderService.updateOrderCompletedStatus(currentOrder.getOrderId());
                    System.out.println("Thank you for picking up your order. Have a great day!");
                    pageViewer.changePage("back");
                } else {
                    System.out.println("Your order is not ready to pick up yet.");
                }
                break;
            case "n":
                // If the user selects 'No', simply go back to the main customer page
                System.out.println("You can come back to pick up your order later.");
                pageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid input. Please choose a valid option.");
                break;
            
        }
    }
}
