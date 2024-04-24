package entities;

import constants.OrderStatus;
import utilities.TimeHandler;

import java.util.ArrayList;

/**
 * The Order class encapsulates the attributes and contains the methods for operations related to an Order
 * An Order is a unique order tied to each new customer, and contains the list of items they are buying, and the summary of the order details
 */
public class Order {
    /** A unique order identifier*/
    private int orderID;
    /** List of items included in the order. */
    private ArrayList<MenuItem> items;
    /** Indicates whether the order is for takeaway or dine-in. */
    private boolean isTakeaway;
    /** Current status of the order. */
    private OrderStatus status;
    /** Total price of all items in the order. */
    private double totalPrice;
    /** The branch which the customer is ordering from */
    private Branch branch;
    /** The last modified time of this order. This is used to see if the order should be cancelled too if the user took too long to collect */
    private long lastModified;
    /**
     * The constructor of a new order during runtime
     * @param orderId 
     * @param branch
     */
    public Order(int orderId, Branch branch) {
        this.orderID = orderId;
        this.items = new ArrayList<>();
        this.isTakeaway = false;
        this.status = OrderStatus.NEW;
        this.totalPrice = 0.0;
        this.branch = branch;
        this.lastModified = TimeHandler.getCurrentTime();
    }
    /**
     * This is the constructor for when the order is loaded in from the CSV file
     * (For persistence)
     * @param orderID
     * @param items
     * @param isTakeaway
     * @param status
     * @param totalPrice
     * @param branch
     * @param lastModified
     */
    public Order(int orderID, ArrayList<MenuItem> items, boolean isTakeaway, OrderStatus status, double totalPrice, Branch branch, long lastModified){
        this.orderID = orderID;
        this.items = items;
        this.isTakeaway = isTakeaway;
        this.status = status;
        this.totalPrice = totalPrice;
        this.branch = branch;
        this.lastModified = lastModified;
    }
    /**
     * The method to add a new item into the list of items. It also updates the total price of this order
     * @param item the new item to add
     */
    public void addItem(MenuItem item) {
        this.items.add(item);
        this.totalPrice+=item.getPrice();
    }
    /**
     * The method to remove an item from the list of items. It also updates the total price of this order
     * @param item item to remove
     * @return true if operation was successful
     */
    public boolean removeItem(MenuItem item) {
        boolean removed = this.items.remove(item);
        if (removed)
            this.totalPrice-=item.getPrice();
        return removed;
    }
    /**
     * Gets the unique identifier of the order.
     * @return Retrieve the order ID.
     */
    public int getOrderId() {
        return orderID;
    }

    /**
     * Set the unique identifier of the order.
     * @param orderId The order ID to set.
     */
    public void setOrderId(int orderId) {
        this.orderID = orderId;
    }

    /**
     * Gets the list of items in the order.
     * @return The list of items.
     */
    public ArrayList<MenuItem> getItems() {
        return this.items; 
    }
    /**
     * Checks if the order is for takeaway.
     * @return True if the order is for takeaway, false otherwise.
     */
    public boolean isTakeaway() {
        return isTakeaway;
    }
    /**
     * Sets whether the order is for takeaway or dine-in.
     * @param isTakeaway True for takeaway, false for dine-in.
     */
    public void setTakeaway(boolean isTakeaway) {
        this.isTakeaway = isTakeaway;
    }
    /**
     * Checks the status of the order
     * @return the status of the order. (See OrderStatus enums)
     */
    public OrderStatus getStatus() {
        // Yet to implement orderId to capture status of the order ...
        return status;
    }
    /**
     * Sets the status of the order. Updates the last modified time accordingly
     * @param status The status of the order.
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
        this.lastModified = TimeHandler.getCurrentTime();
    }
    /**
     * Gets the total price of the order.
     * @return The total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    /**
     * Counts the total number of items in the order.
     * @return The total number of items.
     */
    public int countTotalItems(){
        return items.size();
    }
    /**
     * Method to quickly get the name of the branch this order belongs to
     * @return the branch name
     */
    public String getBranchName(){
        return this.branch.getBranchName();
    }
    /**
     * Method to get the last modified time
     * @return the last modified time
     */
    public long getLastModified(){
        return this.lastModified;
    }
    /**
     * Prints the details of the order including customization.
     */
    public void printOrderDetails() {

        System.out.println("Order ID: " + this.orderID);
        System.out.println("Order Type: " + (this.isTakeaway ? "Takeaway" : "Dine-in"));
        System.out.println("Order Status: " + this.status);
        System.out.println("Total Items in Order: " + countTotalItems());
        System.out.println("Items in Order:");

        for(MenuItem m : this.items){
            System.out.println("- " + m.getFood() 
                                + " ("+ m.getCategory() + ") $" 
                                + m.getPrice() + " -- " 
                                + (m.getCustomization().equalsIgnoreCase("NA")?"Standard":m.getCustomization()));
        }

        this.totalPrice = 0.0;
        for (MenuItem item : this.items) {
            this.totalPrice += item.getPrice();
        }

        System.out.println(String.format("Total price: $%.2f", this.totalPrice));
    }
}