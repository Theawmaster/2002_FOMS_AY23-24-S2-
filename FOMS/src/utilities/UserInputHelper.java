package utilities;

import java.util.Scanner;
import javax.swing.*;

import constants.MealCategory;

import java.util.ArrayList;
import entities.Branch;
import entities.MenuItem;

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

    public static double getDoubleInput(String prompt){
        System.out.println(prompt);
        try{
            return scanner.nextDouble();
        }
        catch (NumberFormatException e){
            System.out.println("Please enter a valid choice!");
            return getDoubleInput(prompt);
        }
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
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= MealCategory.values().length) {
                    return MealCategory.values()[choice - 1];
                } else {
                    System.out.println("Invalid input. Please enter a valid number between 1 and " + MealCategory.values().length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
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
  
    
    
    // public static boolean confirmAction(String message) {
    //     System.out.println(message);
    //     String input = scanner.nextLine().trim().toLowerCase();
    //     return "y".equals(input);
    // }
    
    // public static String getNewBranch(List<String> branchNames) {
    //     System.out.println("Enter the new Branch:");
    //     String branch;
    //     do {
    //         branch = scanner.nextLine().trim().toUpperCase();
    //         if (!branchNames.contains(branch)) {
    //             System.out.println("The branch does not exist. Please enter a valid branch:");
    //         } else {
    //             break;
    //         }
    //     } while (true);
    //     return branch;
    // }
}

