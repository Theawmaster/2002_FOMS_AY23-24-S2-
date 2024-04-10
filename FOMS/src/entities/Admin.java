package entities;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import entities.Branch;
import java.util.UUID;
import constants.Role;
import entities.PaymentService;
import utilities.LoadStaffs;
import entities.StaffManagement;
import java.lang.reflect.Method;


/**
 * The {@code Admin} class extends the {@code Staff} class and provides various administrative functionalities
 * such as managing payments, staff, and branch operations within the system.
 */
public class Admin extends Staff {

    private Branch managedBranch; // branch managed by the admin
    private PaymentService paymentService; // payment manager to handle payments
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
        this.paymentService = new PaymentService();
        this.staffMembers = new HashMap<>(); // Assuming each staff member can be identified by their loginID
    }

    /**
     * Manage staff members in the system.
     */
    public void manageStaff() {
        try {
            // Create an instance of the StaffManagement class
            StaffManagement staffManagement = new StaffManagement();
            
            // Get the Class object of StaffManagement
            Class<?> staffManagementClass = staffManagement.getClass();
            
            // Get the declared method "activate_" from the StaffManagement class
            Method activateMethod = staffManagementClass.getDeclaredMethod("activate_");
            
            // Set the method accessible (since it's private)
            activateMethod.setAccessible(true);
            
            // Invoke the activate_() method on the staffManagement object
            activateMethod.invoke(staffManagement);
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

    /**
     * Adds a payment using the associated PaymentService instance.
     */
    public void addPayment() {
        paymentService.addPayment();
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
     * Removes a payment using the associate PaymentService instance.
     */
    public void removePayment() {
        paymentService.removePayment();
    }


    /**
     * Views all staff members in the system.
     */
    public void viewAllStaff() {
        if (staffMembers.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            LoadStaffs loadStaffs = new LoadStaffs();
            loadStaffs.viewAllStaff();
            
        }
    }

}
