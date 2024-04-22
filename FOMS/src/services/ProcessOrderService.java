package services;

import java.util.ArrayList;

import constants.OrderStatus;
import entities.Order;
import utilities.LoadOrders;
import utilities.Session;
import utilities.UserInputHelper;
import utilities.Logger;

public class ProcessOrderService {
    public static void addItemToOrder(Session session){
        if(session.getCurrentActiveOrder()==null) session.makeNewOrder();
        session.getCurrentActiveOrder().addItem(session.getCurrentActiveMenuItem());
        LoadOrders.addOrderToCSV(session.getCurrentActiveOrder());
    }
    public static void addCustomisationToOrder(Session session){
        String custom = UserInputHelper.getInput("Enter your customisation: ");
        session.getCurrentActiveMenuItem().setCustomization(custom);
    }
    public static void processOrder(Session session){
        if(session.getAllOrders().size() == 0){
            System.out.println("No orders to process");
            return;
        }
        Order selectedOrder = UserInputHelper.chooseOrder(session.getAllOrders(), session.getCurrentActiveBranch());
        if(selectedOrder.getStatus().equals(OrderStatus.PREPARING)){
            if(LoadOrders.updateOrderStatus(selectedOrder, OrderStatus.READY_TO_PICKUP)){
                selectedOrder.setStatus(OrderStatus.READY_TO_PICKUP);
                System.out.println("Order number "+selectedOrder.getOrderId()+" is now ready for pickup");
                return;
            }
            else{
                System.out.println("Failed to update order status");
                return;
            }
        }
        else{
            System.out.println("This order cannot be prepared!");
            return;
        }
    }
    public static void custViewOrderStatus(ArrayList<Order> allOrders){
        int orderID = UserInputHelper.getUserChoice("Enter your order ID to view (c to cancel): ", 999, "c");
        if(orderID == -1) return;

        for(Order o : allOrders){
            if(o.getOrderId() == orderID){
                String status = "";
                switch (o.getStatus()) {
                    case OrderStatus.READY_TO_PICKUP:
                        status = "ready for pickup!";
                        break;
                    case OrderStatus.PREPARING:
                        status = "still preparing! Please have a seat first";
                        break;
                    case OrderStatus.CANCELLED:
                        status = "cancelled! Please approach our staff for more help as you missed your order";
                        break;
                    case OrderStatus.COMPLETED:
                        status = "completed! If you would like to order more, please do!";
                        break;
                    default:
                        status = "unknown... hm mysterious...";
                        break;
                }
                System.out.println("Your current order '"+orderID+"'"+" is "+status);
                return;
            }
        }
        System.out.println("Your order "+orderID+" was not found!");
        return;
    }
    public static void collectOrder(Session session){
        Order order = UserInputHelper.chooseOrder(session.getAllOrders(), session.getCurrentActiveBranch());
        if(order == null) return;
        if(order.getStatus()==OrderStatus.READY_TO_PICKUP){
            order.setStatus(OrderStatus.COMPLETED);
            System.out.println("Enjoy your meal!");
            LoadOrders.addOrderToCSV(order);
            return;
        }
        else if(order.getStatus()==OrderStatus.CANCELLED){
            System.out.println("Your order has been cancelled. Please approach our staff for help");
            return;
        }
        else if(order.getStatus()==OrderStatus.PREPARING){
            System.out.println("Your order is not ready yet");
            return;
        }
        else{
            System.out.println("Unfortunately, we have not received this order. Please approach our staff for help");
            return;
        }
    }
    public static void viewOrderDetails(Session session){
        Order selectedOrder = UserInputHelper.chooseOrder(session.getAllOrders(), session.getCurrentActiveBranch());
        selectedOrder.printOrderDetails();
        return;
    }
    public static void customerPaid(Session session){
        // set order status to PROCESSING
        session.getCurrentActiveOrder().setStatus(OrderStatus.PREPARING);
        // print receipt
        System.out.println(Logger.ANSI_CYAN+"\n-- Here's your receipt --"+Logger.ANSI_RESET);
        session.getCurrentActiveOrder().printOrderDetails();
        // save data into CSV
        LoadOrders.addOrderToCSV(session.getCurrentActiveOrder());
        // clear active order
        session.setCurrentActiveOrder(null);
    }
}
