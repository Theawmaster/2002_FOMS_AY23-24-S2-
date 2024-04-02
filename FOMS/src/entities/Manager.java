package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import constants.MealCategory;
import constants.Role;

/**
 * The {@code Manager} extends the {@code Staff} class and provide various management functionalities 
 * such as managing orders, menu items and staffs within the system.
 */
public class Manager extends Staff {
 
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
     * The list of the menu items in an array list.
     */
    private List<MenuItem> menuItems = new ArrayList<>();
    /**
     * The list of the supervised staff names in the branch using array list.
     */
	private List<Staff> supervisedStaff = new ArrayList<>();

    public Manager (String firstName, String lastName, String loginID, Role role, boolean gender, int age, String staffPassword)
    {
        super(firstName, lastName, loginID, role, gender, age, staffPassword);
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
     * Facilitate orders processing in the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
	public void processOrders() {
		// TODO - implement Manager.processOrders
		throw new UnsupportedOperationException();
	}

    /**
     * Viewing all customer orders from the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
	public void viewAllOrders() {
		// TODO - implement Manager.viewAllOrders
		throw new UnsupportedOperationException();
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
