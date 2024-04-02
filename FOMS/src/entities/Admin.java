package entities;

import java.util.InputMismatchException;
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
     * Edits the details of a manager in the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void editManager() {
        // TODO - implement Admin.editManager
        throw new UnsupportedOperationException();
    }

    /**
     * Edits staff information in the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void editStaff() {
        // TODO - implement Admin.editStaff
        throw new UnsupportedOperationException();
    }

    /**
     * Terminates the employment of a staff member.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void fireStaff() {
        // TODO - implement Admin.fireStaff
        throw new UnsupportedOperationException();
    }

    /**
     * Hires a new staff member into the organization.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void hireStaff() {
        // TODO - implement Admin.hireStaff
        throw new UnsupportedOperationException();
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
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void promoteStaff() {
        // TODO - implement Admin.promoteStaff
        throw new UnsupportedOperationException();
    }

     /**
     * Removes a payment using the associated PaymentManager instance.
     */
    public void removePayment() {
        paymentManager.removePayment();
    }

    /**
     * Transfers a staff member from one branch to another.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void transferStaff() {
        // TODO - implement Admin.transferStaff
        throw new UnsupportedOperationException();
    }

    /**
     * Views all staff members in the system.
     * This method is currently not implemented and will throw an {@code UnsupportedOperationException} if called.
     */
    public void viewAllStaff() {
        // TODO - implement Admin.viewAllStaff
        throw new UnsupportedOperationException();
    }

}
