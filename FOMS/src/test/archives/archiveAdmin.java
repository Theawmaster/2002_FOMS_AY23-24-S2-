// package test.archives;

// import java.util.HashMap;
// import java.util.InputMismatchException;
// import java.util.Map;
// import java.util.Scanner;
// import entities.Branch;
// import java.util.UUID;
// import constants.Role;
// import entities.PaymentService;
// import entities.Staff;
// import utilities.LoadStaffs;
// import entities.StaffManagement;
// import java.lang.reflect.Method;


// /**
//  * The {@code Admin} class extends the {@code Staff} class and provides various administrative functionalities
//  * such as managing payments, staff, and branch operations within the system.
//  */
// public class archiveAdmin extends Staff {

//     private Branch managedBranch; // branch managed by the admin
//     private PaymentService paymentService; // payment manager to handle payments
//     private Map<String, Staff> staffMembers; // map of staff members

//     /**
//      * Constructor for Admin, extending Staff with additional properties.
//      * 
//      * @param firstName The first name of the admin.
//      * @param lastName The last name of the admin.
//      * @param loginID The login ID for the admin.
//      * @param role The role of the admin, likely Role.ADMIN or similar.
//      * @param gender The gender of the admin (true for female, false for male).
//      * @param age The age of the admin.
//      * @param staffPassword The password for the admin's account.
//      */
//     public archiveAdmin(String firstName, String lastName, String loginID, Role role, boolean gender, int age, String staffPassword) {
//         super(firstName, lastName, loginID, role, gender, age, staffPassword);
//         this.paymentService = new PaymentService();
//         this.staffMembers = new HashMap<>(); // Assuming each staff member can be identified by their loginID
//     }

//     /**
//      * Manage staff members in the system.
//      */
//     public void manageStaff() {
//         try {
//             // Create an instance of the StaffManagement class
//             StaffManagement staffManagement = new StaffManagement();
            
//             // Get the Class object of StaffManagement
//             Class<?> staffManagementClass = staffManagement.getClass();
            
//             // Get the declared method "activate_" from the StaffManagement class
//             Method activateMethod = staffManagementClass.getDeclaredMethod("activate_");
            
//             // Set the method accessible (since it's private)
//             activateMethod.setAccessible(true);
            
//             // Invoke the activate_() method on the staffManagement object
//             activateMethod.invoke(staffManagement);
//         } catch (Exception e) {
//             // Handle any exceptions
//             e.printStackTrace();
//         }
//     }

//     /**
//      * Adds a payment using the associated PaymentService instance.
//      */
//     public void addPayment() {
//         paymentService.addPayment();
//     }
    
//      /**
//      * Removes a payment using the associate PaymentService instance.
//      */
//     public void removePayment() {
//         paymentService.removePayment();
//     }


// }
