package services;

import utilities.LoadMenuItems;
import utilities.Session;

import utilities.UserInputHelper;
import entities.MenuItem;
import entities.Branch;
import constants.MealCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The {@link ManageMenuService} class contains methods to manage the menu items in the system
 * @param session
 * @return a list of MenuItem objects with information loaded in
 * @author @Theawmaster
 */
public class ManageMenuService {

    private static void displayOutput(ArrayList<MenuItem> menuItems) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s%-25s%-10s%-20s%-35s\n", "Branch", "Food", "Price", "Category", "Description");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        for (MenuItem menuItem : menuItems) {
            String output = String.format("%-25s%-25s%-10.2f%-20s%-35s",
                    menuItem.getBranch(), menuItem.getFood(), menuItem.getPrice(), menuItem.getCategory(), menuItem.getDescription());
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
        Collections.sort(menuItems, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem m1, MenuItem m2){
                return m1.getCategory().compareTo(m2.getCategory());
            }   
        });
        displayOutput(menuItems);
    }

    /**
     * This method allows the user to add a menu item
     * @param session
     */
    public static void addMenuItem(Session session) {
        String food = UserInputHelper.getInput("Enter food name: ");
        for(MenuItem m : session.getAllMenuItems()){
            if (m.getFood().equalsIgnoreCase(food) && m.getBranch().getBranchName().equals(session.getCurrentActiveBranch().getBranchName())) {
                System.out.println("Can't add the same item "+food);
                return;
            }
        }
        double price = UserInputHelper.getDoubleInput("Enter price: ");
        MealCategory category = UserInputHelper.choosMealCategory("Enter category: ");
        String description = UserInputHelper.getInput("Enter description: ");
        String customization = "NA";
        Branch branch = session.getCurrentActiveBranch();

        MenuItem newMenuItem = new MenuItem(food, price, branch, category, description, customization);

        
        if(LoadMenuItems.addMenuToCSV(newMenuItem)){
            session.getAllMenuItems().add(newMenuItem);
            System.out.println("Added "+newMenuItem.getFood()+" successfully. Looking Fiesty!!!!");
            return;
        }
        else{
            System.out.println("Failed to add "+newMenuItem.getFood()+". GET THE FUCK OUT");
            return;
        }

    }

    /**
     * Removes a menu item from the session's list of menu items.
     * @param session The current session containing the list of menu items.
     */
    public static void removeMenuItem(Session session) {
        ArrayList<MenuItem> filteredItems = filterBranch(session, session.getCurrentActiveBranch());
        if (filteredItems.isEmpty()) {
            System.out.println("No menu items found for this branch.");
            return;
        }

        System.out.println("Select the menu item to remove:");
        MenuItem itemToRemove = UserInputHelper.chooseMenuItem(filteredItems);
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
        MenuItem oldItem = UserInputHelper.chooseMenuItem(filteredItems);
        if(oldItem == null){
            System.out.println("No valid menu item selected.");
            return;
        }
        MenuItem newItem;
        try{ newItem = (MenuItem)oldItem.clone();} // clone the old item first
        catch (Exception e){ return; } // there shouldnt be an exception
    
        System.out.println("Choose an attribute to edit:");
        System.out.println("1. Food Name\n2. Price\n3. Category\n4. Description");
        int choice = UserInputHelper.getUserChoice("Enter your choice:", 4);
        if (choice == -1) {
            return;
        }
    
        switch (choice) {
            case 1:
                String foodEdit = UserInputHelper.getInput("Enter new food name:");
                for(MenuItem m : session.getAllMenuItems()){
                    if (m.getFood().equals(foodEdit) && m.getBranch().getBranchName().equals(session.getCurrentActiveBranch().getBranchName())) {
                        System.out.println("Can't add the same item "+foodEdit);
                        return;
                    }
                }
                newItem.setFood(foodEdit);
                break;
            case 2:
                newItem.setPrice(UserInputHelper.getDoubleInput("Enter new price:"));
                break;
            case 3:
                newItem.setCategory(UserInputHelper.choosMealCategory("Choose a category:"));
                break;
            case 4:
                newItem.setDescription(UserInputHelper.getInput("Enter new description:"));
                break;
            default:
                System.out.println("Invalid choice. Operation canceled.");
                return;
        }
        // replace MenuItem data stored in CSV
        if(LoadMenuItems.replaceMenuItem(oldItem, newItem)){
            session.updateSession(); // quick way to reset loaded menu item data
            return;
        }   
        else{
            System.out.println("Something went wrong with saving data");
            return;
        }
    }
}