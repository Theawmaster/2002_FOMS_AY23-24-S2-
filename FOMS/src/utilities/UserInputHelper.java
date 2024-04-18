package utilities;

import java.util.Scanner;
import java.util.List;
import entities.Branch;

public class UserInputHelper {
    private Scanner scanner;
    private Session session;

    public UserInputHelper(Session session) {
        this.scanner = new Scanner(System.in);
        this.session = session;
    }

    public String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public String chooseRole() {
        String roleInput;
        do {
            roleInput = getInput("Enter Role (S for Staff / M for Manager): ").toUpperCase();
            if (!"S".equals(roleInput) && !"M".equals(roleInput)) {
                System.out.println("Invalid input. Please enter 'S' for Staff or 'M' for Manager.");
            }
        } while (!"S".equals(roleInput) && !"M".equals(roleInput));
        return "S".equals(roleInput) ? "STAFF" : "MANAGER";
    }

    public String chooseGender() {
        String gender;
        do {
            gender = getInput("Enter Gender (M/F): ").toUpperCase();
            if (!"M".equals(gender) && !"F".equals(gender)) {
                System.out.println("Invalid input. Please enter 'M' for Male or 'F' for Female.");
            }
        } while (!"M".equals(gender) && !"F".equals(gender));
        return gender;
    }

    public int chooseAge() {
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

    public String chooseBranch(List<Branch> branches) {
        branches.forEach(branch -> System.out.println(branch.getBranchName() + " - Quota: " + branch.getbranchQuota()));
        while (true) {
            System.out.println("Enter the name of the branch you wish to assign the staff to:");
            final String branchInput = scanner.nextLine().trim().toUpperCase(); // Make input effectively final
            boolean exists = branches.stream().anyMatch(b -> b.getBranchName().equalsIgnoreCase(branchInput));
            if (exists) {
                return branchInput;  // Return immediately on valid input
            }
            System.out.println("Invalid branch. Please enter a valid branch name from the list.");
        }
    }
    
    public int getUserChoice(String prompt, int maxChoice) {
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
    
    public boolean confirmAction(String message) {
        System.out.println(message);
        String input = scanner.nextLine().trim().toLowerCase();
        return "y".equals(input);
    }

    public String getStaffID() {
        System.out.println("Enter the Staff ID of the staff you want to promote to Manager:");
        return scanner.nextLine().trim();
    }

    public String getStaffIDForTransfer() {
        System.out.println("Enter the Staff ID of the staff/manager you want to transfer:");
        return scanner.nextLine().trim();
    }
    
    public String getNewBranch(List<String> branchNames) {
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

