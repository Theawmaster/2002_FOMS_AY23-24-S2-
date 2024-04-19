package services;

import utilities.LoadMenuItems;
import utilities.Session;

import utilities.UserInputHelper;
import entities.MenuItem;
import entities.Branch;

import java.util.ArrayList;

import constants.MealCategory;

/**
 * The {@link ManageMenuService} class contains methods to manage the menu items in the system
 * @param session
 * @return a list of MenuItem objects with information loaded in
 * @author @Theawmaster
 */
public class ManageMenuService {

    private static void displayOutput(ArrayList<MenuItem> menuItems) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s%-25s%-10s%-20s%-45s%-15s\n", "Branch", "Food", "Price", "Category", "Description", "Customizable");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (MenuItem menuItem : menuItems) {
            String output = String.format("%-25s%-25s%-10.2f%-20s%-45s%-15s",
                    menuItem.getBranch(), menuItem.getFood(), menuItem.getPrice(), menuItem.getCategory(), menuItem.getDescription(), menuItem.getCustomization());
            System.out.println(output);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ƒ");
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
        String customization = UserInputHelper.getInput("Enter customization: "); // i thought this one is the customer write one?
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

    public static void removeMenuItem(Session session){
        MenuItem badMenuItem = UserInputHelper.chooseMenuItem(session.getAllMenuItems());
        if(LoadMenuItems.removeMenuItem(badMenuItem)){
            session.getAllMenuItems().remove(badMenuItem);
            System.out.println("Removal of menu item successful");
        }
        else{
            System.out.println("Something went wrong while trying to remove menu item");
        }
    }

    public static void editMenuItem(Session session){
        //@alvin can help me do this please
    }
}