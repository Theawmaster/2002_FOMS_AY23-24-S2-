package entities;

import entities.Staff;
import test.AdminPage;
import constants.FilePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * The {@code StaffManagement} class provides functionalities for managing staff members.
 * This includes adding, editing, removing, promoting to manager, and transferring staff members.
 */
public class StaffManagement implements StaffManagementFriend{

    /**
     * The main method to start the staff management program.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        StaffManagement program_ = new StaffManagement();
        program_.activate_();
    }

    /**
     * Activates the staff management program.
     * This method displays the staff management menu and handles user input for various operations.
     */
    public void activate_() {
        System.out.println("                  _____ __        ________   __  ___                                                  __               ");
        System.out.println("                 / ___// /_____ _/ __/ __/  /  |/  /___ _____  ____ _____ ____  ____ ___  ___  ____  / /_              ");
        System.out.println(" ____________    \\__ \\/ __/ __ `/ /_/ /_   / /|_/ / __ `/ __ \\/ __ `/ __ `/ _ \\/ __ `__ \\/ _ \\/ __ \\/ __/  ____________");
        System.out.println("/_____/_____/   ___/ / /_/ /_/ / __/ __/  / /  / / /_/ / / / / /_/ / /_/ /  __/ / / / / /  __/ / / / /_   /_____/_____/");
        System.out.println("               /____/\\__/\\__,_/_/ /_/    /_/  /_/\\__,_/_/ /_/\\__,_/\\__, /\\___/_/ /_/ /_/\\___/_/ /_/\\__/                ");
        System.out.println("                                                                  /____/                                               ");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nStaff Management commands:");
            System.out.println("1. Add Staff");
            System.out.println("2. Edit Staff");
            System.out.println("3. Remove Staff");
            System.out.println("4. Promote Staff to Manager");
            System.out.println("5. Transfer Staff");
            System.out.println("6. Exit to Admin Main Page");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Add staff
                    try {
                        addStaff(scanner);
                    } catch (IOException e) {
                        System.out.println("Error adding staff: " + e.getMessage());
                    }
                    break;
                case "2":
                    // Edit staff
                    try {
                        editStaff();
                    } catch (IOException e) {
                        System.out.println("Error editing staff: " + e.getMessage());
                    }
                    break;
                case "3":
                    // Remove staff
                    removeStaff(scanner);
                    break;
                case "4":
                    // Promote staff to manager
                    try {
                        promoteToManager(scanner);
                    } catch (IOException e) {
                        System.out.println("Error promoting staff to manager: " + e.getMessage());
                    }
                    break;
                case "5":
                    // Transfer staff
                    try {
                        transferStaff(scanner);
                    } catch (IOException e) {
                        System.out.println("Error transferring staff: " + e.getMessage());
                    }
                    break;
                case "6":
                    running = false;
                    System.out.println("Exiting Staff Management...");
                    new AdminPage().showAdminPage();
                default:
                    System.out.println("Invalid choice, please try again...");
            }
        }

        scanner.close();
    }

    /**
     * Adds a new staff member.
     *
     * @param scanner The Scanner object for user input.
     * @throws IOException If an I/O error occurs while reading or writing data.
     */
    private void addStaff(Scanner scanner) throws IOException {
        // Load branches from branch_list.csv
        List<String> branchLines = Files.readAllLines(Paths.get(FilePaths.branchListPath.getPath()));
        List<String> branchNames = branchLines.stream()
                .skip(1) // Assuming the first line is a header
                .map(line -> line.split(",")[0].trim()) // Assuming the branch name is the first column
                .collect(Collectors.toList());

        // Load existing staff data
        List<String> staffLines = Files.readAllLines(Paths.get(FilePaths.staffListPath.getPath()));
        Map<String, Long> staffCountPerBranch = staffLines.stream()
            .map(line -> line.split(","))
            .filter(parts -> parts.length >= 6 && !parts[2].trim().equalsIgnoreCase("MAN")) // Excluding managers
            .collect(Collectors.groupingBy(parts -> parts[5].trim(), Collectors.counting()));

        Map<String, Long> managerCountPerBranch = staffLines.stream()
                .map(line -> line.split(","))
                .filter(parts -> parts.length >= 6 && parts[2].trim().equalsIgnoreCase("MAN")) // Only managers
                .collect(Collectors.groupingBy(parts -> parts[5].trim(), Collectors.counting()));


        System.out.println("Enter Staff Name: ");
        String name = scanner.nextLine();
    
        System.out.println("Enter Staff ID: ");
        String id = scanner.nextLine();
    
        // Ensure role is either S or M
        String ui_role = "";
        String role = "";
        while (!ui_role.equals("S") && !ui_role.equals("s") && !ui_role.equals("M") && !ui_role.equals("m")) {
            System.out.println("Enter Role (S for Staff / M for Manager): ");
            ui_role = scanner.nextLine().toUpperCase();
            if (!ui_role.equals("S") && !ui_role.equals("M")) {
                System.out.println("Invalid input. Please enter 'S' for Staff or 'M' for Manager.");
            }
            if (ui_role.equals("S")) {
                role = "STAFF";
            } else if (ui_role.equals("M")) {
                role = "MAN";
            }
        }
        
        // Ensure gender is either M or F
        String gender = "";
        while (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Enter Gender (M/F): ");
            gender = scanner.nextLine().toUpperCase();
            if (!gender.equals("M") && !gender.equals("F")) {
                System.out.println("Invalid input. Please enter 'M' for Male or 'F' for Female.");
            }
        }
        
        // Ensure age is within 0 - 100
        int age = -1;
        while (age < 0 || age > 100) {
            System.out.println("Enter Age: ");
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age < 0 || age > 100) {
                    System.out.println("Invalid input. Age must be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for age.");
            }
        }
        
        String branch;
        do {
            System.out.println("Available Branches:");
            branchNames.forEach(b -> {
                long staffCount = staffCountPerBranch.getOrDefault(b, 0L);
                long managerCount = managerCountPerBranch.getOrDefault(b, 0L);
                System.out.println(b + " - Staff: " + staffCount + ", Managers: " + managerCount);
            });
            System.out.print("Enter Branch: ");
            branch = scanner.nextLine().toUpperCase();
            if (!branchNames.contains(branch)) {
                System.out.println("Invalid branch. Please enter a branch from the following list: " + String.join(", ", branchNames));
            }
        } while (!branchNames.contains(branch));

        String finalBranch = branch;
        long staffCount = staffLines.stream()
                                    .filter(line -> line.contains(finalBranch) && !line.contains("MAN"))
                                    .count();
        long managerCount = staffLines.stream()
                                        .filter(line -> line.contains(finalBranch) && line.contains("MAN"))
                                        .count();

        // Determine allowed manager count based on staff count
        int allowedManagers = (staffCount < 5) ? 1 : (staffCount < 9) ? 2 : 3;

        // Check manager quota constraints
        if ("MAN".equalsIgnoreCase(role) && managerCount >= allowedManagers) {
            System.out.println("XXXX Cannot add more managers to this branch due to quota constraints! XXXX");
            return;
        }

        // Check staff quota constraints
        int staffQuota = getBranchQuota(branch, branchLines);
        if (staffCount >= staffQuota) {
            System.out.println("XXXX Cannot add more staff to this branch due to quota constraints! XXXX");
            return;
        }

        // Construct the new staff member line
        String newStaffMember = String.join(",", name, id, role, gender, String.valueOf(age), branch);

        // Append the new staff member to the file
        Files.write(Paths.get(FilePaths.staffListPath.getPath()), 
                    (newStaffMember + System.lineSeparator()).getBytes(), 
                    StandardOpenOption.APPEND);

        System.out.println("YAYYYY! New staff member added successfully!");
    }
    
    /**
     * Gets the staff quota for a specific branch.
     *
     * @param branchName The name of the branch.
     * @param branchLines The list of lines from the branch list file.
     * @return The staff quota for the branch.
     */
    private int getBranchQuota(String branchName, List<String> branchLines) {
        for (String line : branchLines) {
            String[] parts = line.split(",");
            if (parts.length >= 3 && parts[0].trim().equalsIgnoreCase(branchName)) {
                try {
                    return Integer.parseInt(parts[2].trim()); // Return the staff quota
                } catch (NumberFormatException e) {
                    // Handle parsing error
                }
            }
        }
        return 0; // Return 0 if branch not found or quota not available
    }

    /**
     * Removes a staff member.
     *
     * @param scanner The Scanner object for user input.
     */
    private void removeStaff(Scanner scanner) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FilePaths.staffListPath.getPath()));
            for (int i = 0; i < lines.size(); i++) {
                System.out.println((i + 1) + ". " + lines.get(i));
            }

            System.out.print("Enter the Line number of the staff member to remove: ");
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice < 1 || choice > lines.size()) {
                System.out.println("Invalid staff number.");
                return;
            }

            System.out.println("??? Are you sure you want to remove the following staff member ????");
            System.out.print("Enter 'Y' or 'y' to confirm.... ");
            char confirm = scanner.nextLine().charAt(0);

            if (confirm != 'Y' && confirm != 'y') {
                System.out.println("Staff removal cancelled ~~~");
                return;
            }
            
            lines.remove(choice - 1);
            Files.write(Paths.get(FilePaths.staffListPath.getPath()), lines);

            System.out.println("Staff removed successfully...");
        } catch (IOException e) {
            System.err.println("An error occurred while removing staff!");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid number!");
        }
    }

    /**
     * Edits details of an existing staff member.
     *
     * @throws IOException If an I/O error occurs while reading or writing data.
     */
    public static void editStaff() throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt for the staff's login ID you want to edit
        System.out.print("Enter the login ID of the staff you want to edit: ");
        String loginID = scanner.nextLine();

        // Prompt for the new details
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new ID: ");
        String newID = scanner.nextLine();
        System.out.print("Enter new age: ");
        String newAge = scanner.nextLine();
        scanner.close();

        // Read all lines from the staff list file
        List<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(FilePaths.staffListPath.getPath())));
        
        // Iterate through the lines to find the staff with the given login ID
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains(loginID)) {
                // Split the line into parts
                String[] parts = line.split(",");

                // Check if the line has enough parts to edit (to avoid ArrayIndexOutOfBoundsException)
                if (parts.length >= 6) {
                    // Update all the details
                    parts[0] = newName;
                    parts[1] = newID;
                    parts[4] = newAge;
                    
                    // Reconstruct the line with the updated details
                    lines.set(i, String.join(",", parts));
                }
                break;
            }
        }

        // Write the updated lines back to the file
        Files.write(Paths.get(FilePaths.staffListPath.getPath()), lines);

        System.out.println("Staff details updated successfully.");
    }

    /**
     * Promotes a staff member to a manager.
     *
     * @param scanner The Scanner object for user input.
     * @throws IOException If an I/O error occurs while reading or writing data.
     */
    private void promoteToManager(Scanner scanner) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FilePaths.staffListPath.getPath()));
        System.out.println("Enter the Staff ID of the staff you want to promote to Manager:");
        String staffId = scanner.nextLine();
    
        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            if (parts[1].trim().equals(staffId) && parts[2].trim().equalsIgnoreCase("STAFF")) {  // Check if it's a staff
                String branch = parts[5].trim();
                if (canAddManager(branch, lines)) {
                    parts[2] = "MANAGER";  // Change role to Manager
                    lines.set(i, String.join(",", parts));
                    found = true;
                    break;
                } else {
                    System.out.println("ORH OH... Manager quota exceeded for this branch.. Promotion not possible..");
                    return;
                }
            }
        }
    
        if (!found) {
            System.out.println("BOOOO Staff ID not found or the employee is already a manager...");
            return;
        }
    
        Files.write(Paths.get(FilePaths.staffListPath.getPath()), lines);
        System.out.println("YAYYY!!!! Staff promoted to Manager successfully!!!");
    }

    /**
     * Transfers a staff member to another branch.
     *
     * @param scanner The Scanner object for user input.
     * @throws IOException If an I/O error occurs while reading or writing data.
     */
    private void transferStaff(Scanner scanner) throws IOException {
        // Load branches from branch_list.csv
        List<String> branchLines = Files.readAllLines(Paths.get(FilePaths.branchListPath.getPath()));
        List<String> branchNames = branchLines.stream()
                .skip(1) // Assuming the first line is a header
                .map(line -> line.split(",")[0].trim()) // Assuming the branch name is the first column
                .collect(Collectors.toList());

        // Load existing staff data
        List<String> staffLines = Files.readAllLines(Paths.get(FilePaths.staffListPath.getPath()));
        Map<String, Long> staffCountPerBranch = staffLines.stream()
            .map(line -> line.split(","))
            .filter(parts -> parts.length >= 6 && !parts[2].trim().equalsIgnoreCase("MAN")) // Excluding managers
            .collect(Collectors.groupingBy(parts -> parts[5].trim(), Collectors.counting()));

        Map<String, Long> managerCountPerBranch = staffLines.stream()
                .map(line -> line.split(","))
                .filter(parts -> parts.length >= 6 && parts[2].trim().equalsIgnoreCase("MAN")) // Only managers
                .collect(Collectors.groupingBy(parts -> parts[5].trim(), Collectors.counting()));

        // Display available branches with staff and manager counts
        System.out.println("Available Branches:");
            branchNames.forEach(b -> {
                long staffCount = staffCountPerBranch.getOrDefault(b, 0L);
                long managerCount = managerCountPerBranch.getOrDefault(b, 0L);
                System.out.println(b + " - Staff: " + staffCount + ", Managers: " + managerCount);
            });
    
        System.out.println("Enter the Staff ID of the staff/manager you want to transfer:");
        String staffId = scanner.nextLine();
        boolean foundStaff = false;
    
        for (int i = 0; i < staffLines.size(); i++) {
            String[] parts = staffLines.get(i).split(",");
            if (parts[1].trim().equals(staffId)) {
                foundStaff = true;
    
                System.out.println("Enter the new Branch:");
                String newBranch = scanner.nextLine().toUpperCase();
    
                // Check if the new branch exists
                boolean branchExists = branchLines.stream().anyMatch(line -> line.contains(newBranch));
                if (!branchExists) {
                    System.out.println("The branch does not exist leh.. Please enter a valid branch >,<");
                    return;
                }
    
                // Check if the staff/manager can be added to the new branch based on manager quota
                if (parts[2].trim().equalsIgnoreCase("MAN") && !canAddManager(newBranch, staffLines)) {
                    System.out.println("UH OHHH Manager quota exceeded for the new branch :( Transfer not possible..");
                    return;
                }
    
                parts[5] = newBranch;  // Update the branch
                staffLines.set(i, String.join(",", parts));
                break;
            }
        }
    
        if (!foundStaff) {
            System.out.println("Staff ID not found...");
            return;
        }
    
        // Write the updated staff list back to the file
        Files.write(Paths.get(FilePaths.staffListPath.getPath()), staffLines);
        System.out.println("HOORAY! Staff/Manager transferred successfully!!!");
    }
    
    /**
     * Checks if it's possible to add a manager to a branch based on quota constraints.
     *
     * @param branch The branch to check.
     * @param lines  The list of staff lines.
     * @return {@code true} if a manager can be added, {@code false} otherwise.
     */
    private boolean canAddManager(String branch, List<String> lines) {
        long currentManagers = lines.stream()
                                    .filter(line -> line.contains(branch) && line.split(",")[2].trim().equalsIgnoreCase("MANAGER"))
                                    .count();
    
        // Get staff count for quota calculation
        long staffCount = lines.stream()
                               .filter(line -> line.contains(branch) && line.split(",")[2].trim().equalsIgnoreCase("STAFF"))
                               .count();
    
        // Calculate allowed managers based on staff count
        int allowedManagers = (staffCount < 5) ? 1 : (staffCount < 9) ? 2 : 3;
    
        return currentManagers < allowedManagers;
    }

}
