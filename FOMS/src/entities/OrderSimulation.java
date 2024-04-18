package entities;

import entities.MenuItem;
import entities.Order;
import constants.OrderStatus;

import java.util.List;

import constants.MealCategory;


public class OrderSimulation {
    public static void main(String[] args) {
        // Create a new order for takeaway
        Order takeawayOrder = new Order(1);
        
        // Create menu items
        MenuItem burger = new MenuItem("Burger", 5.99, MealCategory.BURGER, "na", "na");
        MenuItem fries = new MenuItem("Fries", 2.49, MealCategory.SIDE, "na", "na");

        // Add items to the order with customization
        takeawayOrder.addItem(burger, "No onions", "na");
        takeawayOrder.addItem(fries, "Extra crispy", "na");

        // Print order details
        System.out.println("Takeaway Order Details:");
        takeawayOrder.printOrderDetails();
        System.out.println();

        // Create a new order for dine-in
        Order dineInOrder = new Order(0);

        // Create menu items
        MenuItem burger1 = new MenuItem("Burger", 5.99, MealCategory.BURGER, "na", "na");
        MenuItem fries1 = new MenuItem("Fries", 2.49, MealCategory.SIDE, "na", "na");

        // Add items to the order with customization
        dineInOrder.addItem(burger, "with onions", "na");
        dineInOrder.addItem(fries, "no crispy", "na");

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

