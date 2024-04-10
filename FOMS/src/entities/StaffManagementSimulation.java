package entities;

import entities.Staff;
import constants.FilePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StaffManagementSimulation {

    public static void main(String[] args) {
        StaffManagementSimulation simulation = new StaffManagementSimulation();
        simulation.runSimulation();
    }

    private void runSimulation() {
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
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Add staff
                    addStaff(scanner);
                    break;
                case "2":
                    // Edit staff
                    try {
                        editStaff();
                    } catch (IOException e) {
                        System.out.println("An error occurred while editing staff details: " + e.getMessage());
                    }
                    break;
                case "3":
                    // Remove staff
                    removeStaff(scanner);
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting Staff Management Simulation...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again...");
            }
        }

        scanner.close();
    }

    private void addStaff(Scanner scanner) {
        System.out.print("Enter Staff Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Staff ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Staff Role: ");
        String role = scanner.nextLine();

        System.out.print("Enter Staff Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Staff Age: ");
        String age = scanner.nextLine();

        System.out.print("Assigning Staff Branch: ");
        String branch = scanner.nextLine();

        String newLine = String.join(",", name, id, role, gender, age, branch);

        try {
            Files.write(Paths.get(FilePaths.staffListPath.getPath()), 
                        (newLine + System.lineSeparator()).getBytes(), 
                        StandardOpenOption.APPEND);
            System.out.println("Staff added successfully!");
        } catch (IOException e) {
            System.err.println("An error occurred while adding staff!");
            e.printStackTrace();
        }
    }

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

            System.out.println("Are you sure you want to remove the following staff member?");
            System.out.print("Enter 'Y' or 'y' to confirm: ");
            char confirm = scanner.nextLine().charAt(0);

            if (confirm != 'Y' && confirm != 'y') {
                System.out.println("Staff removal cancelled.");
                return;
            }
            
            lines.remove(choice - 1);
            Files.write(Paths.get(FilePaths.staffListPath.getPath()), lines);

            System.out.println("Staff removed successfully!");
        } catch (IOException e) {
            System.err.println("An error occurred while removing staff!");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid number!");
        }
    }

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
        System.out.print("Enter new role: ");
        String newRole = scanner.nextLine();
        System.out.print("Enter new age: ");
        String newAge = scanner.nextLine();
        System.out.print("Enter new branch: ");
        String newBranch = scanner.nextLine();

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
                    parts[2] = newRole;
                    parts[4] = newAge;
                    parts[5] = newBranch; 
                    
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

}
