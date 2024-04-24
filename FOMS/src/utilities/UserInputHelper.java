package utilities;

import java.util.Scanner;
import javax.swing.*;

import constants.MealCategory;
import constants.Settings;

import java.util.ArrayList;

import entities.Branch;
import entities.MenuItem;
import entities.Order;

/**
 * This class contains methods that handle user input
 * This class is necessary to ensure that user input is handled correctly and consistently
 * @author Alvin Aw Yong
 * @author Siah Yee Long
 */
public class UserInputHelper {
    /**
     * Scanner object to read user input
     */
    private static Scanner scanner = new Scanner(System.in);
    /**
     * This method gets the basic string input from users
     * @param prompt The prompt to display to the user
     * @return The user's input as a string
     */
    public static String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }
    /**
     * This method gets the password input from users using a JPasswordField
     * This is just an additional feature such that the user can key in their passwords with masking
     * @param prompt The prompt to display to the user
     * @return The user's input as a string
     */
    public static String getPasswordInput(String prompt) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(prompt);
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Password Input",
                                                  JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                                  null, options, options[0]);
        if (option == 0) { // Pressing OK button
            char[] password = pass.getPassword();
            return new String(password);
        }
        return ""; // If user cancels, return empty string
    }   
    /**
     * This method gets the double input from users
     * @param prompt The prompt to display to the user
     * @return The user's input as a double
     */
    public static double getDoubleInput(String prompt) {
        double input = 0.0;
        boolean validInput = false;
        do {
            try {
                System.out.println(prompt);
                input = Double.parseDouble(scanner.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice!");
            }
        } while (!validInput);
        return input;
    }
    /**
     * This method gets the integer input from users ranging from 1 to maxChoice, with an option to cancel the selection
     * @param prompt The prompt to display to the user
     * @param maxChoice The maximum choice that the user can choose
     * @return The user's input as an integer, or -1 if the user chooses to cancel
     */
    public static int getUserChoice(String prompt, int maxChoice) {
        String choice;
        int intChoice;
        do {
            try {
                choice = getInput(prompt + " ("+(String)Settings.CANCEL_CHARACTER.getValue()+" to cancel): ");
                if(((String)Settings.CANCEL_CHARACTER.getValue()).equalsIgnoreCase(choice)){
                    return -1;
                }
                intChoice = Integer.parseInt(choice);
                if (intChoice < 1 || intChoice > maxChoice) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice + ".");
                } else {
                    return intChoice;
                }
            } catch (NumberFormatException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);
    }
    /**
     * This method gets the user's choice of Roles shortened to S/M/A
     * @return The user's input as a string
     */
    public static String chooseRole() {
        String roleInput;
        do {
            roleInput = getInput("Enter Role (S/M/A): ").toUpperCase();
            if (!"S".equals(roleInput) && !"M".equals(roleInput) && !"A".equals(roleInput)) {
                System.out.println("Invalid input!");
            }
        } while (!"S".equals(roleInput) && !"M".equals(roleInput) && !"A".equals(roleInput));
        return roleInput;
    }
    /**
     * This method gets the user's choice of gender
     * @return The user's input as a string
     */
    public static String chooseGender() {
        String gender;
        do {
            gender = getInput("Enter Gender (M/F): ").toUpperCase();
            if (!"M".equals(gender) && !"F".equals(gender)) {
                System.out.println("Invalid input. Please enter 'M' for Male or 'F' for Female!");
            }
        } while (!"M".equals(gender) && !"F".equals(gender));
        return gender;
    }
    /**
     * This method gets the user's choice of age
     * @return The user's input as an integer
     */
    public static int chooseAge() {
        int age = -1;
        do {
            try {
                age = Integer.parseInt(getInput("Enter Age: "));
                if (age < 0 || age > 100) {
                    System.out.println("Age must be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for age.");
            }
        } while (age < 0 || age > 100);
        return age;
    }
    /**
     * This method gets the user's choice of branch from a list of branches
     * @param branches The list of branches to choose from
     * @return The user's choice as a Branch object, or null if the user chooses to cancel
     */
    public static Branch chooseBranch(ArrayList<Branch> branches) {
        int numOptions = 1;
        for(Branch b : branches){
            System.out.println(numOptions + ". " + b.getBranchName());
            numOptions++;
        }
        int option = getUserChoice("Select a branch", numOptions-1);
        if (option == -1) return null;
        return branches.get(option-1);
    }
    /**
     * This method gets the user's choice of menu item from a list of menu items
     * @param menuItems The list of menu items to choose from
     * @return The user's choice as a MenuItem object, or null if the user chooses to cancel
     */
    public static MenuItem chooseMenuItem(ArrayList<MenuItem> menuItems){
        int numOptions = 1;
        for(MenuItem m : menuItems){
            System.out.println(numOptions + ". " + m.getFood());
            numOptions++;
        }
        int option = getUserChoice("Select a menu item", numOptions-1);
        if (option == -1) return null;
        return menuItems.get(option-1);
    }
    /**
     * This method gets the user's choice of meal category (See MealCategory enum)
     * @param prompt The prompt to display to the user
     * @return The user's choice as a MealCategory object, or null if the user chooses to cancel
     */
    public static MealCategory choosMealCategory(String prompt) {
        System.out.println(prompt);
        int i = 1;
        for (MealCategory mc : MealCategory.values()) {
            if(mc == MealCategory.UNDEFINED) continue;
            System.out.println(i + ". " + mc.toString());
            i++;
        }
        int option = getUserChoice("Select a menu item", i-1);
        if (option == -1) return null;
        return MealCategory.values()[option-1];
    }    
    /**
     * This method gets the user's choice of order from a list of orders
     * @param order The list of orders to choose from
     * @param branch The branch that the order belongs to
     * @return The user's choice as an Order object
     */
    public static Order chooseOrder(ArrayList<Order> order, Branch branch){
        while(true){
            int option = getUserChoice("Enter your order ID", 999);
            if(option == -1) return null;
            for(Order o : order){
                if(o.getOrderId() == option && o.getBranchName().equals(branch.getBranchName())){
                    return o;
                }
            }
            System.out.println("No ID found!");
        }
    }
    /**
     * This method gets the user's choice of whether to takeaway or dine in
     * @param order The order to set the takeaway status
     * @return true if the user chooses to takeaway, false if the user chooses to dine in
     */
    public static boolean chooseTakeaway(Order order){
        String choice = getInput("Enter your choice");
        switch (choice) {
            case "1":
                order.setTakeaway(false);
                return true;
            case "2":
                order.setTakeaway(true);
                return true;
            case "c":
            case "C":
                return false;
            default:
                System.out.println("Please enter a valid choice!");
                return chooseTakeaway(order);
        }
    }
}

