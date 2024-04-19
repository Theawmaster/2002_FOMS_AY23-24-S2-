package services;

import utilities.LoadMenuItems;
import utilities.Session;

import utilities.UserInputHelper;
import entities.MenuItem;
import entities.Branch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import constants.FilePaths;
import constants.MealCategory;

/**
 * The {@link ManageMenuService} class contains methods to manage the menu items in the system
 * @param session
 * @return a list of MenuItem objects with information loaded in
 * @author @Theawmaster
 */
public class ManageMenuService {

    private static void displayOutput(ArrayList<MenuItem> menuItems) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s%-25s%-10s%-20s%-35s%-15s\n", "Branch", "Food", "Price", "Category", "Description", "Customizable");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        for (MenuItem menuItem : menuItems) {
            String output = String.format("%-25s%-25s%-10.2f%-20s%-35s%-15s",
                    menuItem.getBranch(), menuItem.getFood(), menuItem.getPrice(), menuItem.getCategory(), menuItem.getDescription(), menuItem.getCustomization());
            System.out.println(output);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * This method filters the menu items based on the branch
     * @param session
     * @param whichBranch
     * @return
     */
    private static ArrayList<MenuItem> filterBranch(Session session, Branch whichBranch) {
        ArrayList<MenuItem> filteredMenuItems = new ArrayList<>();
        for (MenuItem m : session.getAllMenuItems()) {
            if (m.getBranch().getBranchName().equals(whichBranch.getBranchName())) {
                filteredMenuItems.add(m);
            }
        }
        return filteredMenuItems;
    
    }

    /**
     * This method displays all menu items
     * @param session
     */
    public static void displayAllMenuItems(Session session) {
        ArrayList<MenuItem> menuItems = filterBranch(session, session.getCurrentActiveBranch());
        displayOutput(menuItems);
    }

    /**
     * This method allows the user to add a menu item
     * @param session
     */
    public static void addMenuItem(Session session) {
        String food = UserInputHelper.getInput("Enter food name: ");
        double price = UserInputHelper.getDoubleInput("Enter price: ");
        MealCategory category = UserInputHelper.choosMealCategory("Enter category: ");
        String description = UserInputHelper.getInput("Enter description: ");
        String customization = "NA";
        Branch branch = session.getCurrentActiveBranch();

        MenuItem newMenuItem = new MenuItem(food, price, branch, category, description, customization);
        
        if(LoadMenuItems.addMenuToCSV(newMenuItem)){
            session.getAllMenuItems().add(newMenuItem);
            System.out.println("Added "+newMenuItem.getFood()+" successfully. Looking Fiesty!!!!");
        }
        else{
            System.out.println("Failed to add "+newMenuItem.getFood()+". GET THE FUCK OUT");
        }

    }

    /**
     * Removes a menu item from the session's list of menu items.
     * @param session The current session containing the list of menu items.
     */
    public static void removeMenuItem(Session session) {
        System.out.println("Select the menu item to remove:");
        MenuItem itemToRemove = UserInputHelper.chooseMenuItem(session.getAllMenuItems());
        if (itemToRemove == null) {
            System.out.println("No valid menu item selected.");
            return;
        }

        // Assume LoadMenuItems handles actual file operations and returns success/failure
        if (LoadMenuItems.removeMenuItem(itemToRemove)) {
            session.getAllMenuItems().remove(itemToRemove);
            System.out.println("Menu item removed successfully.");
        } else {
            System.out.println("Failed to remove the menu item.");
        }
    }

    public static void editMenuItem(Session session) {

        ArrayList<MenuItem> filteredItems = filterBranch(session, session.getCurrentActiveBranch());
        if (filteredItems.isEmpty()) {
            System.out.println("No menu items found for this branch.");
            return;
        }

        System.out.println("Select the menu item to edit:");
        MenuItem itemToEdit = UserInputHelper.chooseMenuItem(filteredItems);
    
        System.out.println("Choose an attribute to edit:");
        System.out.println("1. Food Name\n2. Price\n3. Category\n4. Description\n5. Customization");
        int choice = UserInputHelper.getUserChoice("Enter your choice:", 5);
    
        switch (choice) {
            case 1:
                itemToEdit.setFood(UserInputHelper.getInput("Enter new food name:"));
                break;
            case 2:
                itemToEdit.setPrice(UserInputHelper.getDoubleInput("Enter new price:"));
                break;
            case 3:
                itemToEdit.setCategory(UserInputHelper.choosMealCategory("Choose a category:"));
                break;
            case 4:
                itemToEdit.setDescription(UserInputHelper.getInput("Enter new description:"));
                break;
            case 5:
                itemToEdit.setCustomization(UserInputHelper.getInput("Enter new customization options:"));
                break;
            default:
                System.out.println("Invalid choice. Operation canceled.");
                return;
        }
    
        // Directly update the CSV through LoadMenuItems utility function
        if (updateMenuItem(session, itemToEdit)) {
            System.out.println("Menu item updated successfully.");
        } else {
            System.out.println("Failed to update the menu item.");
        }
    }
    
    public static boolean updateMenuItem(Session session, MenuItem updatedItem) {
        ArrayList<MenuItem> allItems = session.getAllMenuItems();
        boolean isUpdated = false;
    
        // Update the item in the global list
        for (int i = 0; i < allItems.size(); i++) {
            MenuItem item = allItems.get(i);
            if (item.getFood().equals(updatedItem.getFood()) && item.getBranch().getBranchName().equals(updatedItem.getBranch().getBranchName())) {
                allItems.set(i, updatedItem); // Update the item with new details
                isUpdated = true;
                break;
            }
        }
    
        // Write the complete, updated list back to the CSV
        if (isUpdated) {
            return rewriteCSV(allItems); // Pass the entire list to rewrite
        }
    
        return false;
    }

    public static boolean rewriteCSV(ArrayList<MenuItem> items) {
        File file = new File(FilePaths.menuListPath.getPath());
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println("Name,Price,Branch,Category,Description,Customization"); // Write header
            for (MenuItem item : items) {
                pw.printf("%s,%.2f,%s,%s,%s,%s\n",
                    item.getFood(), item.getPrice(), item.getBranch().getBranchName(),
                    item.getCategory().toString(), item.getDescription(), item.getCustomization());
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    
    

}