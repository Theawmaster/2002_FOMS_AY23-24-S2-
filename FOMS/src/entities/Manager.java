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
