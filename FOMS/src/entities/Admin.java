package entities;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import entities.Branch;
import java.util.UUID;

import constants.Role;


/**
 * The {@code Admin} class extends the {@code Staff} class and provides various administrative functionalities
 * such as managing payments, staff, and branch operations within the system.
 */
public class Admin extends Staff {

    private Branch managedBranch; // branch managed by the admin
    private PaymentManager paymentManager; // payment manager to handle payments
    private Map<String, Staff> staffMembers; // map of staff members

    /**
     * Constructor for Admin, extending Staff with additional properties.
     * 
     * @param firstName The first name of the admin.
     * @param lastName The last name of the admin.
     * @param loginID The login ID for the admin.
     * @param role The role of the admin, likely Role.ADMIN or similar.
     * @param gender The gender of the admin (true for female, false for male).
     * @param age The age of the admin.
     * @param staffPassword The password for the admin's account.
     */
    public Admin(String firstName, String lastName, String loginID, Role role, boolean gender, int age, String staffPassword) {
        super(firstName, lastName, loginID, role, gender, age, staffPassword);
        this.paymentManager = new PaymentManager();
        this.staffMembers = new HashMap<>(); // Assuming each staff member can be identified by their loginID
    }

    /**
     * Adds a payment using the associated PaymentManager instance.
     */
    public void addPayment() {
        paymentManager.addPayment();
    }

   /**
     * Closes the currently managed branch, if any.
     */
    public void closeBranch() {
        if (this.managedBranch == null) {
            System.out.println("No branch is currently managed, so no branch to close.");
        } else {
            System.out.println("Closing the branch: " + this.managedBranch.getBranchName());
            // Effectively "removes" the branch by setting managedBranch to null
            this.managedBranch = null;
        }
    }

    /**
     * Edits the details of a manager.
     */
    public void editManager(String loginID, String newFirstName, String newLastName, boolean newGender, int newAge) {
        Staff manager = staffMembers.get(loginID);
        if (manager != null && manager.getRole() == Role.MANAGER) {
            manager.setFirstName(newFirstName);
            manager.setLastName(newLastName);
            manager.setGender(newGender);
            manager.setAge(newAge);
            System.out.println("Manager details updated for login ID: " + loginID);
        } else {
            System.out.println("Manager with login ID: " + loginID + " not found.");
        }
    }

    /**
     * Edits the details of a staff member.
     * 
     * @param loginID The login ID of the staff member to edit.
     * @param newFirstName The new first name for the staff member.
     * @param newLastName The new last name for the staff member.
     * @param newGender The new gender for the staff member (true for female, false for male).
     * @param newAge The new age for the staff member.
     */
    public void editStaff(String loginID, String newFirstName, String newLastName, boolean newGender, int newAge) {
        Staff staff = staffMembers.get(loginID);
        if (staff != null) {
            staff.setFirstName(newFirstName);
            staff.setLastName(newLastName);
            staff.setGender(newGender);
            staff.setAge(newAge);
            System.out.println("Staff details updated for login ID: " + loginID);
        } else {
            System.out.println("Staff member with login ID: " + loginID + " not found.");
        }
    }

     /**
     * Fires a manager by loginID.
     */
    public void fireStaff(String loginID) {
        if (staffMembers.containsKey(loginID) && staffMembers.get(loginID).getRole() == Role.MANAGER) {
            staffMembers.remove(loginID);
            System.out.println("Manager with login ID " + loginID + " has been fired.");
        } else {
            System.out.println("Manager with login ID " + loginID + " not found.");
        }
    }

    /**
     * Hires a new manager.
     * 
     * @param firstName the first name of the new manager
     * @param lastName the last name of the new manager
     * @param loginID the login ID of the new manager
     * @param gender the gender of the new manager (true for female, false for male)
     * @param age the age of the new manager
     * @param staffPassword the password for the new manager's login
     */
    public void hireStaff(String firstName, String lastName, String loginID, boolean gender, int age, String staffPassword) {
        // Assuming Role.MANAGER is an enum value
        Staff newManager = new Staff(firstName, lastName, loginID, Role.MANAGER, gender, age, staffPassword);
        staffMembers.put(loginID, newManager);
        System.out.println("New manager hired: " + firstName + " " + lastName);
    }


      /**
     * Opens a new branch, replacing any currently managed branch.
     * branchName The name of the new branch.
     * location The location of the new branch.
     * quota The quota for the new branch.
     */
    public void openBranch() {

        Scanner scanner = new Scanner(System.in);

        // Prompt user for branch details
        System.out.print("Enter branch name: ");
        String branchName = scanner.nextLine();

        System.out.print("Enter branch location: ");
        String location = scanner.nextLine();

        int quota = 0;
        System.out.print("Enter branch quota: ");
        while (true) {
            try {
                quota = scanner.nextInt();
                break; // Exit loop if input was successful
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for quota.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        // If there's already a managed branch, simulate its closure before opening a new one
        if (this.managedBranch != null) {
            System.out.println("Automatically closing the currently managed branch: " + this.managedBranch.getBranchName());
        }
        
        // Create a new Branch instance and assign it to managedBranch
        this.managedBranch = new Branch(branchName, location, quota);
        System.out.println("Opened a new branch: " + branchName + " at " + location + " with quota " + quota);
    }



    /**
     * Promotes a staff member within the organization.
     */
    public void promoteStaff(String loginID, Role newRole) {
        Staff staff = staffMembers.get(loginID);
        if (staff != null) {
            staff.setRole(newRole);  // Assuming there's a setRole method in Staff
            System.out.println("Staff member " + staff.getFirstName() + " " + staff.getLastName() + " promoted to " + newRole);
        } else {
            System.out.println("Staff member with login ID " + loginID + " not found.");
        }
    }
    

     /**
     * Removes a payment using the associated PaymentManager instance.
     */
    public void removePayment() {
        paymentManager.removePayment();
    }

    /**
     * Transfers a staff member from one branch to another.
     * 
     * @param loginID the login ID of the staff member to transfer
     * @param newBranch the new branch to transfer the staff member to
     */
    public void transferStaff(String loginID, Branch newBranch) {
        Staff staff = staffMembers.get(loginID);
        if (staff != null) {
            staff.setBranch(newBranch);  // Assuming there's a setBranch method in Staff
            System.out.println("Staff member " + staff.getFirstName() + " " + staff.getLastName() + " transferred to " + newBranch.getBranchName());
        } else {
            System.out.println("Staff member with login ID " + loginID + " not found.");
        }
    }

    /**
     * Views all staff members in the system.
     */
    public void viewAllStaff() {
        if (staffMembers.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            for (Staff staff : staffMembers.values()) {
                staff.printStaff();  // Assuming Staff has a method to print its details
            }
        }
    }

}
