package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import constants.MealCategory;
import constants.OrderStatus;
import constants.Role;

/**
 * The {@code Manager} extends the {@code Staff} class and provide various management functionalities 
 * such as managing orders, menu items and staffs within the system.
 */
public class Manager extends Staff {
 
    /**
     * The list of the menu items in an array list.
     */
    private List<MenuItem> menuItems = new ArrayList<>();
    /**
     * The list of the supervised staff names in the branch using array list.
     */
	private List<Staff> supervisedStaff = new ArrayList<>();

    /**
     * Constructor for Manager, extending Staff with additional properties.
     * 
     * @param firstName The first name of the manager.
     * @param lastName The last name of the manager.
     * @param loginID The login ID for the manager.
     * @param gender The gender of the manager (true for female, false for male).
     * @param age The age of the manager.
     * @param staffPassword The password for the manager's account.
     */
    public Manager(String firstName, String lastName, String loginID, boolean gender, int age, String staffPassword) {
        super(firstName, lastName, loginID, Role.MANAGER, gender, age, staffPassword);
        // Initialize Manager-specific fields here
    }

    /**
     * Adds menu item to the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
	public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
		throw new UnsupportedOperationException();
	}

    /**
     * Edit the menu item consisting of food name, price and category from the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
	public void editMenuItem(String oldFood) {
        Scanner sc = new Scanner(System.in);

        // Find the existing menu item and replace it with the new description
        for (MenuItem item : menuItems) {
            if (item.getFood().equalsIgnoreCase(oldFood)) {
                System.out.print("Enter new food name for " + oldFood + ": ");
                String newFood = sc.next();
                item.setFood(newFood);

                System.out.print("Enter new price for " + oldFood + ": ");
                double newPrice = sc.nextDouble();
                item.setPrice(newPrice);

                System.out.print("Enter new category for " + oldFood + ": ");
                String newCategoryStr = sc.next();
                MealCategory newCategory = MealCategory.valueOf(newCategoryStr.toUpperCase());
                item.setCategory(newCategory);

                break; 
            }
        }
		throw new UnsupportedOperationException();
	}

    /**
     * Remove menu item from the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
	public void removeMenuItem(String food) {
        menuItems.removeIf(item -> item.getFood().equalsIgnoreCase(food));
		throw new UnsupportedOperationException();
	}

    /**
     * Facilitates processing of orders by updating their statuses.
     * @param orders The list of orders to process.
     */
    public void processOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("There are no orders to process.");
            return;
        }

        // Display the orders to process
        System.out.println("Process Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Type: " + (order.isTakeaway() ? "Takeaway" : "Dine-in"));
            System.out.println("Order Status: " + order.getStatus());
            System.out.println("Total Price: " + order.getTotalPrice());
            System.out.println("---------------------------");
        }

        // Assuming the manager calls to process at a fast rate which can be done afterwards
        // Setting order status to ready for pickup
        order.setStatus(OrderStatus.READY_TO_PICKUP);
        System.out.println("Order processed. Status updated to: " + order.getStatus());
    }

    /**
     * Views all orders in the system.
     * @param orders The list of orders to view.
     */
    public void viewAllOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("There are no orders to view.");
            return;
        }

        // Display all orders
        System.out.println("All Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Type: " + (order.isTakeaway() ? "Takeaway" : "Dine-in"));
            System.out.println("Order Status: " + order.getStatus());
            System.out.println("Total Price: " + order.getTotalPrice());
        }
    }

    /**
     * Viewing the menu items from the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
	public void viewMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("The menu is currently empty.");
            return;
        }
        System.out.println("Menu Items:");
        for (MenuItem item : menuItems) {
            item.printMenuItems();
        }
		throw new UnsupportedOperationException();
	}

    /**
     * Add a staff member by admin to the list of supervised staff in the assigned branch.  
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void addedSupervisedStaff(Staff staff) {
        if (staff != null) {
            supervisedStaff.add(staff);
        }
        throw new UnsupportedOperationException();
    }

     /**
     * Viewing all staff details from the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
	public void viewMyStaff() {
        if (supervisedStaff.isEmpty()) {
            System.out.println("No staff members are currently supervised.");
            return;
        }
        System.out.println("Supervised Staff Details:");
        for (Staff staff : supervisedStaff) {
            staff.printStaff(); 
        }
        throw new UnsupportedOperationException();
    }		
}

// alvin - to implement order function methods into manager.
// things havent implement yet - ( i created every functions of how it roughly works)
// 1. orderId (when customer order something an orderId is received)
// 2. order status and (due to watchdog timer check whether it exceeds 30 sec before changing to cancelled status) example on my function 



// package entities;

// import constants.OrderStatus;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;

// import utilities.Time;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;

// /**
//  * Represents an order placed by a customer.
//  */
// public class Order {
//     /** Unique identifier for the order. */
//     private int orderId;
    
//     /** List of items included in the order. */
//     private List<MenuItem> items;
    
//     /** Indicates whether the order is for takeaway or dine-in. */
//     private boolean isTakeaway;
    
//     /** Current status of the order. */
//     private OrderStatus status;
    
//     /** Total price of all items in the order. */
//     private double totalPrice;
    
//     /** Date and time when the order was created. */
//     private LocalDateTime orderDateTime; 

//     /**
//      * Constructs a new order.
//      * @param isTakeaway True if the order is for takeaway, false if it is for dine-in.
//      */
//     public Order(boolean isTakeaway) {
//         this.orderId = orderId;
//         this.items = new ArrayList<>();
//         this.isTakeaway = isTakeaway;
//         this.status = OrderStatus.NEW;
//         this.totalPrice = 0.0;
//         this.orderDateTime = LocalDateTime.now();  // Store the order date and time when the order is created
//     }

//     /**
//      * Checks if the order has exceeded the specified timeframe for pickup.
//      * @param minutes The specified timeframe in minutes.
//      * @return True if the order has exceeded the specified timeframe, false otherwise.
//      */
//     public boolean hasExceededTimeframeForPickup(int minutes) {
//         LocalDateTime currentDateTime = LocalDateTime.now();

//         // Calculate the difference in minutes between the current time and the order time
//         long minutesDifference = ChronoUnit.MINUTES.between(orderDateTime, currentDateTime);

//         // Return true if the difference exceeds the specified timeframe, false otherwise
//         return minutesDifference >= minutes;
//     }

//     /**
//      * Adds a menu item to the order.
//      * @param item The menu item to add.
//      * @param customization Customization details for the menu item.
//      */
//     public void addItem(MenuItem item, String customization) {
//          // Create a new MenuItem with customization and add it to the order
//         MenuItem customizedItem = new MenuItem(item.getFood(), item.getPrice(), item.getCategory(), customization);
//         if (customization.isEmpty())
//         {
//             items.add(item); // Add menu items in order without customization 
//             updateTotalPrice();
//         }
//         else
//         {
//             items.add(customizedItem); // Add menu items in order with customization
//             updateTotalPrice();
//         }
//     }

//     /**
//      * Removes a menu item from the order.
//      * @param item The menu item to remove.
//      * @return True if the item was successfully removed, false otherwise.
//      */
//     public boolean removeItem(MenuItem item) {
//         boolean removed = items.remove(item);
//         if (removed) {
//             updateTotalPrice();
//         }
//         return removed;
//     }

//     /**
//      * Updates the total price of the order based on the items.
//      */
//     private void updateTotalPrice() {
//         totalPrice = items.stream().mapToDouble(MenuItem::getPrice).sum();
//     }

//     /**
//      * Gets the unique identifier of the order.
//      * @return The order ID.
//      */
//     public int getOrderId() {
//         return orderId;
//     }

//     /**
//      * Gets the list of items in the order.
//      * @return The list of items.
//      */
//     public List<MenuItem> getItems() {
//         return new ArrayList<>(items); 
//     }

//     /**
//      * Checks if the order is for takeaway.
//      * @return True if the order is for takeaway, false otherwise.
//      */
//     public boolean isTakeaway() {
//         return isTakeaway;
//     }

//     /**
//      * Sets whether the order is for takeaway or dine-in.
//      * @param isTakeaway True for takeaway, false for dine-in.
//      */
//     public void setTakeaway(boolean isTakeaway) {
//         this.isTakeaway = isTakeaway;
//     }

//     /**
//      * Gets the status of the order.
//      * @param orderId The unique identifier of the order.
//      * @return The status of the order.
//      */
//     public OrderStatus getStatus() {
//         // Yet to implement orderId to capture status of the order ...
//         return status;
//     }

//     /**
//      * Sets the status of the order.
//      * @param status The status of the order.
//      */
//     public void setStatus(OrderStatus status) {
//         this.status = status;
//     }

//     /**
//      * Gets the total price of the order.
//      * @return The total price.
//      */
//     public double getTotalPrice() {
//         return totalPrice;
//     }

//     /**
//      * Counts the total number of items in the order.
//      * @return The total number of items.
//      */
//     public int countTotalItems(){
//         return items.size();
//     }

//     /**
//      * Prints the details of the order.
//      */
//     public void printOrderDetails() {
//         System.out.println("Order ID: " + orderId);
//         System.out.println("Order Type: " + (isTakeaway ? "Takeaway" : "Dine-in"));
//         System.out.println("Order Status: " + status);
//         System.out.println("Total Items in Order: " + countTotalItems());
//         System.out.println("Items in Order:");
//         items.forEach(item -> {
//             System.out.println("- " + item.getFood() + ", " + item.getPrice() + ", " + item.getCategory() + ", " + item.getCustomization());
//         });
//         System.out.println("Total Price: " + totalPrice);
//         // Add function for payment after listing down order details...
//     }
// }
