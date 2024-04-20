// //[OUTDATED], some are having wrong implementations

// package test.archives;


// import utilities.LoadStaffs;
// import utilities.LoadBranches;
// import utilities.Session;
// import utilities.UserInputHelper;
// import java.util.List;

// import entities.Staff;

// import java.util.ArrayList;
// import java.io.IOException;

// /**
//  * This class provides services to manage staff
//  * @author @Theawmaster
//  */
// public class archiveManageStaffService {
//     private LoadStaffs loadStaffs = new LoadStaffs();
//     private LoadBranches loadBranches = new LoadBranches();
//     private Session session = new Session();
//     private UserInputHelper userInputHelper = new UserInputHelper(session);

//     /**
//      * Method to display all staff
//      */
//     public void displayAllStaff() {
//         ArrayList<Staff> staffList = loadStaffs.loadDatafromCSV();
//         if (staffList.isEmpty()) {
//             System.out.println("No staff records available.");
//             return;
//         }
//         for (Staff staff : staffList) {
//             System.out.println(staff); // Assuming Staff class has a properly overridden toString() method
//         }
//     }

//      /**
//      * Adds a new staff member.
//      *
//      * @param scanner The Scanner object for user input.
//      * @throws IOException If an I/O error occurs while reading or writing data.
//      */
//     public void addStaff() {
//         // Gather necessary input for new staff member
//         String name = userInputHelper.getInput("Enter Staff Name: ");
//         String id = userInputHelper.getInput("Enter Staff ID: ");
//         String role = userInputHelper.chooseRole();
//         String gender = userInputHelper.chooseGender();
//         int age = userInputHelper.chooseAge();
//         String branch = userInputHelper.chooseBranch(session.getAllBranches()); // Assumes branches are fetched from session
    
//         // Get current staff and manager counts for the chosen branch
//         long staffCount = session.getStaffCount(branch);
//         long managerCount = session.getManagerCount(branch);
//         int staffQuota = session.getBranchQuota(branch);
//         int allowedManagers = session.calculateAllowedManagers(staffCount);

//         System.out.println("Current Managers: " + managerCount);
//         System.out.println("Allowed Managers: " + allowedManagers);
//         System.out.println("Current Staff: " + staffCount);
//         System.out.println("Staff Quota: " + staffQuota);

    
//         // Check manager quota constraints
//         if ("MANAGER".equalsIgnoreCase(role) && managerCount >= allowedManagers) {
//             System.out.println("XXXX Sien.... Cannot add more managers to this branch due to quota constraints! XXXX");
//             return;
//         }
    
//         // Check staff quota constraints
//         if (staffCount >= staffQuota) {
//             System.out.println("XXXX Sien.... Cannot add more staff to this branch due to quota constraints! XXXX");
//             return;
//         }
    
//         // Proceed to add the new staff if all checks pass
//         boolean success = loadStaffs.addStaffToCSV(name, id, role, gender, age, branch);
//         if (success) {
//             System.out.println("Hooray! New staff member added successfully!");
//         } else {
//             System.out.println("Aww... Failed to add new staff member...");
//         }
//     }
    
//     /**
//      * Removes a staff member.
//      */
//     public void fireStaff() {
//         try {
//             List<String> lines = loadStaffs.readAllStaffLines();
//             for (int i = 0; i < lines.size(); i++) {
//                 System.out.println((i + 1) + ". " + lines.get(i));
//             }
    
//             int choice = userInputHelper.getUserChoice("Enter the Line number of the staff member to remove:", lines.size());
            
//             if (!userInputHelper.confirmAction("??? Are you sure you want to remove the following staff member ??? Enter 'Y' to confirm:")) {
//                 System.out.println("Staff removal cancelled ~~~");
//                 return;
//             }
    
//             loadStaffs.removeStaffLine(choice - 1);  // Adjust loadStaffs to handle the removal logic
    
//             System.out.println("Staff removed successfully...");
//         } catch (IOException e) {
//             System.err.println("An error occurred while removing staff!");
//             e.printStackTrace();
//         }
//     }

//     /**
//      * Promotes a staff member to manager.
//      */
//     public void promoteToManager() {
//         try {
//             String staffId = userInputHelper.getStaffID();
//             boolean success = loadStaffs.promoteStaffToManager(staffId);
//             if (success) {
//                 System.out.println("YAYYY!!!! Staff promoted to Manager successfully!!!");
//             }
//         } catch (IOException e) {
//             System.err.println("An error occurred while promoting staff!");
//             e.printStackTrace();
//         }
//     }

//     public void transferStaff() {
//         try {
//             String staffId = userInputHelper.getStaffIDForTransfer();
//             List<String> branchNames = loadBranches.getBranchNames();
//             String newBranch = userInputHelper.getNewBranch(branchNames);
    
//             if (!loadBranches.branchExists(newBranch)) {
//                 System.out.println("The branch does not exist. Please enter a valid branch.");
//                 return;
//             }
    
//             if (!loadStaffs.updateStaffBranch(staffId, newBranch)) {
//                 System.out.println("Staff ID not found or transfer not possible.");
//                 return;
//             }
    
//             System.out.println("HOORAY! Staff/Manager transferred successfully!!!");
//         } catch (IOException e) {
//             System.err.println("An error occurred while transferring staff!");
//             e.printStackTrace();
//         }
//     }

// }
