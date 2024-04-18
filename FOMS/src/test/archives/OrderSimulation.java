package test.archives;

import entities.MenuItem;
import entities.Order;
import constants.OrderStatus;
import constants.MealCategory;

public class OrderSimulation {
    public static void main(String[] args) {
        // Create a new order for takeaway
        Order takeawayOrder = new Order(1);

        // Create menu items
        MenuItem burger = new MenuItem("Cheese Burger", 5.99, MealCategory.SETMEAL, "delicious cheese burger", "na");
        MenuItem fries = new MenuItem("Fries", 2.49, MealCategory.SIDE, "delicious cheese fries", "na");
        MenuItem burger1 = new MenuItem("MEAT Burger", 6, MealCategory.BURGER, "delicious burger", "na");
        MenuItem fries1 = new MenuItem("Fries", 2.49, MealCategory.SIDE, "delicious fries", "na");

        // Add items to the order with customization
        takeawayOrder.addItem(burger, "delicious cheese burger" ,"No onions");
        takeawayOrder.addItem(fries, " delicious fries" ,"Extra crispy");
        // Print order details
        System.out.println("Takeaway Order Details:");
        takeawayOrder.printOrderDetails();
        System.out.println();

        // Create a new order for dine-in
        Order dineInOrder = new Order(0);
        
        // Add items to the order
        dineInOrder.addItem(burger1, "delicious burger", "");
        dineInOrder.addItem(fries1, "delicious fries", "no crispy");

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
        if (dineInOrder.hasExceededTimeframeForPickup(30)) {
            dineInOrder.setStatus(OrderStatus.CANCELLED);
            System.out.println("Order automatically cancelled due to exceeding pickup timeframe.");
            System.out.println("Order Status After Cancellation: " + dineInOrder.getStatus());
        }
    }
}