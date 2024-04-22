package utilities;

import java.util.Scanner;
import javax.swing.*;

import constants.MealCategory;
import constants.OrderStatus;

import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.InputMismatchException;

import entities.Branch;
import entities.MenuItem;
import entities.Order;
import services.payments.iPaymentService;

public class UserInputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

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

    public static int getUserChoice(String prompt, int maxChoice) {
        int choice;
        do {
            System.out.println(prompt);
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > maxChoice) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice + ".");
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);
    }
    // overloading getUserChoice method
    public static int getUserChoice(String prompt, int maxChoice, String cancelCharacter) {
        String choice;
        int intChoice;
        do {
            try {
                choice = getInput(prompt);
                if(cancelCharacter.equals(choice)){
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

    public static Branch chooseBranch(ArrayList<Branch> branches) {
        int numOptions = 0;
        for(Branch b : branches){
            numOptions++;
            System.out.println(numOptions + ". " + b.getBranchName());
        }
        return branches.get(getUserChoice("Select a branch: ", numOptions)-1);
    }

    public static MenuItem chooseMenuItem(ArrayList<MenuItem> menuItems){
        int numOptions = 0;
        for(MenuItem m : menuItems){
            numOptions++;
            System.out.println(numOptions + ". " + m.getFood());
        }
        return menuItems.get(getUserChoice("Select a menu item:", numOptions)-1);
    }

    public static MealCategory choosMealCategory(String prompt) {
        System.out.println(prompt);
        int i = 1;
        for (MealCategory mc : MealCategory.values()) {
            System.out.println(i + ". " + mc.toString());
            i++;
        }
        return MealCategory.values()[getUserChoice(prompt, i)-1];
    }    
    
    public static Order chooseOrder(ArrayList<Order> order, Branch branch){
        while(true){
            int option = getUserChoice("Enter your order ID (c to cancel): ", 999, "c");
            if(option == -1) return null;
            for(Order o : order){
                if(o.getOrderId() == option && o.getBranchName().equals(branch.getBranchName())){
                    return o;
                }
            }
            System.out.println("No ID found!");
        }
    }

    
}

