package services;

import java.util.ArrayList;

import constants.OrderStatus;
import constants.Settings;
import entities.Branch;
import entities.MenuItem;
import entities.Order;
import utilities.LoadOrders;
import utilities.Session;
import utilities.TimeHandler;
import utilities.UserInputHelper;
import utilities.Logger;

/**
 * This class contains mainly static methods pertaining to order processing
 * @author Koh Huei Shan, Winnie
 * @author Lee Jedidiah
 * @author Chan Zi Hao
 * @author Siah Yee Long
 */
public class ProcessOrderService {
    /**
     * Private constructor to prevent instantiation of this class
     */
    private ProcessOrderService() {}
    /**
     * Method to add an item to the current active order and update the CSV files accordingly
     * @param session The current session
     */
    public static void addItemToOrder(Session session){
        if(session.getCurrentActiveOrder()==null) session.makeNewOrder();
        session.getCurrentActiveOrder().addItem(session.getCurrentActiveMenuItem());
        LoadOrders.addOrderToCSV(session.getCurrentActiveOrder());
    }
    /**
     * Method to add customisation to an item in the current active order and update the CSV files accordingly
     * @param item The item to add customisation to
     */
    public static void addCustomisationToOrder(MenuItem item){
        String custom = UserInputHelper.getInput("Enter your customisation: ");
        // lazy way to ensure that user input does not contain delimmiters that will affect saving data into csv
        if(custom.indexOf(",")!=-1) custom = custom.substring(0, custom.indexOf(","));
        if(custom.indexOf("|")!=-1) custom = custom.substring(0, custom.indexOf("|"));
        item.setCustomization(custom);
    }
    /**
     * Method to customise an order in the current active order and update the CSV files accordingly. This method will call the above method to add customisation to an item in the current active order
     * @param session The current session
     */
    public static void customiseAnOrder(Session session){
        MenuItem custItem = UserInputHelper.chooseMenuItem(session.getCurrentActiveOrder().getItems());
        if(custItem == null) return;
        addCustomisationToOrder(custItem);
    }
    /**
     * Method to remove an order from the current active order and update the CSV files accordingly
     * @param session The current session
     */
    public static void removeOrder(Session session){
        if(session.getAllOrders().size() == 0){
            System.out.println("No orders to remove");
            return;
        }

        MenuItem badItem = UserInputHelper.chooseMenuItem(session.getCurrentActiveOrder().getItems());
        if(badItem == null) return;

        session.getCurrentActiveOrder().removeItem(badItem);
    }
    /**
     * Method to process an order and update the CSV files accordingly
     * @param session The current session
     */
    public static void processOrder(Session session){
        if(session.getAllOrders().size() == 0){
            System.out.println("No orders to process");
            return;
        }
        Order selectedOrder = UserInputHelper.chooseOrder(session.getAllOrders(), session.getCurrentActiveBranch());
        if(selectedOrder==null) return;
        else if(selectedOrder.getStatus().equals(OrderStatus.PREPARING)){

            selectedOrder.setStatus(OrderStatus.READY_TO_PICKUP);
            if(LoadOrders.updateOrderStatus(selectedOrder, OrderStatus.READY_TO_PICKUP)){
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
    /**
     * Method to cancel an order and update the CSV files accordingly
     * @param session The current session
     */
    public static void cancelOrder(Session session){
        if(session.getAllOrders().size() == 0){
            System.out.println("No orders to cancel");
            return;
        }
        Order selectedOrder = UserInputHelper.chooseOrder(session.getAllOrders(), session.getCurrentActiveBranch());
        if(selectedOrder==null) return;

        selectedOrder.setStatus(OrderStatus.CANCELLED);
        if(LoadOrders.updateOrderStatus(selectedOrder, OrderStatus.CANCELLED)){
            System.out.println("Order number "+selectedOrder.getOrderId()+" is now cancelled");
            return;
        }
        else{
            System.out.println("Failed to update order status");
            return;
        }
    }
    /**
     * Method to view the status of an order, and ask to pick up the order if it is ready
     * @param allOrders The list of all orders
     * @param branch The branch that the user is currently at
     */
    public static void custViewOrderStatus(ArrayList<Order> allOrders, Branch branch){
        if(allOrders.size()==0){
            System.out.println("No orders placed at the moment");
            return;
        }
        int orderID = UserInputHelper.getUserChoice("Enter your order ID to view", 999);
        if(orderID == -1) return;

        for(Order o : allOrders){
            if(o.getOrderId() == orderID && o.getBranchName().equalsIgnoreCase(branch.getBranchName())){
                checkToCancelOrder(o);
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
                if(o.getStatus() != OrderStatus.READY_TO_PICKUP) return;
                else{
                    collectOrder(o);
                    return;
                }
            }
        }
        System.out.println("Your order "+orderID+" was not found!");
        return;
    }
    /**
     * Method to view the details of an order
     * @param session The current session
     */
    public static void viewOrderDetails(Session session){
        Order selectedOrder = UserInputHelper.chooseOrder(session.getAllOrders(), session.getCurrentActiveBranch());
        if(selectedOrder==null) return;
        
        selectedOrder.printOrderDetails();
        return;
    }
    /**
     * Method to process an order once the customer has paid and update the CSV files accordingly
     * @param session The current session
     */
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
    /**
     * Method to collect an order
     * @param order The order to collect
     */
    private static void collectOrder(Order order){
        String choice = UserInputHelper.getInput("Wanna pick your order up now? [Y/N]:");
        switch (choice) {
            case "y":
            case "Y":
                order.setStatus(OrderStatus.COMPLETED);
                System.out.println(Logger.ANSI_CYAN+"(You have just collected your order: Order "+Integer.toString(order.getOrderId())+")"+Logger.ANSI_RESET);
                System.out.println("Enjoy your meal!");
                LoadOrders.addOrderToCSV(order);
                return;
            default:
                return;
        }
    }
    /**
     * Method to check if an order should be cancelled if it has exceeded the time limit
     * @param order The order to check
     */
    private static void checkToCancelOrder(Order order){
        if(order.getStatus()==OrderStatus.READY_TO_PICKUP && TimeHandler.hasElapsed((int)Settings.MAX_COLLECTION_TIME_SECONDS.getValue(), order.getLastModified())){
            order.setStatus(OrderStatus.CANCELLED);
            // save data into CSV
            LoadOrders.updateOrderStatus(order, OrderStatus.CANCELLED);
            return;
        }
        Logger.debug("Didn't cancel order, not ready to pick up yet / still within time limit");
    }
}
