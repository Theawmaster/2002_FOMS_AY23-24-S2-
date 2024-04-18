package utilities;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import entities.Branch;

public class UserInputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
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
        while(true){
            try{
                // ask user to choose branch
                int choiceBranch = Integer.parseInt(getInput("Select a branch: "));
                return branches.get(choiceBranch-1);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Please select a branch within the range only! ");
                Logger.error(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("Please enter only integer values! ");
                Logger.error(e.getMessage());
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
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);
    }
    
    public static boolean confirmAction(String message) {
        System.out.println(message);
        String input = scanner.nextLine().trim().toLowerCase();
        return "y".equals(input);
    }
    
    public static String getNewBranch(List<String> branchNames) {
        System.out.println("Enter the new Branch:");
        String branch;
        do {
            branch = scanner.nextLine().trim().toUpperCase();
            if (!branchNames.contains(branch)) {
                System.out.println("The branch does not exist. Please enter a valid branch:");
            } else {
                break;
            }
        } while (true);
        return branch;
    }
}

