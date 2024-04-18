/**
 * [OUTDATED] replaced by AdminManageBranchPage
 */

package entities;

import constants.FilePaths;
import test.archives.archiveAdminPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The BranchManagement class allows administrators to manage branches.
 * Administrators can open new branches, close existing branches, and exit branch management.
 */
public class BranchManagement {

    /**
     * Main method to run the Branch Management system.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        BranchManagement management = new BranchManagement();
        management.runManagement();
    }

    /**
     * Run the branch management system.
     */
    public void runManagement() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("                  ____                        __       __  ___                                                  __               ");
            System.out.println("                 / __ )_________ _____  _____/ /_     /  |/  /___ _____  ____ _____ ____  ____ ___  ___  ____  / /_              ");
            System.out.println(" ____________   / __  / ___/ __ `/ __ \\/ ___/ __ \\   / /|_/ / __ `/ __ \\/ __ `/ __ `/ _ \\/ __ `__ \\/ _ \\/ __ \\/ __/  ____________");
            System.out.println("/_____/_____/  / /_/ / /  / /_/ / / / / /__/ / / /  / /  / / /_/ / / / / /_/ / /_/ /  __/ / / / / /  __/ / / / /_   /_____/_____/");
            System.out.println("              /_____/_/   \\__,_/_/ /_/\\___/_/ /_/  /_/  /_/\\__,_/_/ /_/\\__,_/\\__, /\\___/_/ /_/ /_/\\___/_/ /_/\\__/                ");
            System.out.println("                                                                            /____/                                               ");
            System.out.println("\nBranch Management Commands:");
            System.out.println("1. Open New Branch");
            System.out.println("2. Close Existing Branch");
            System.out.println("3. Exit Branch Management...");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        openNewBranch(scanner);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        closeBranch(scanner);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting Branch Management...");
                    new archiveAdminPage().showAdminPage();
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Opens a new branch.
     * @param scanner The scanner object to read user input.
     * @throws IOException If an I/O error occurs.
     */
    private void openNewBranch(Scanner scanner) throws IOException {
        System.out.print("Enter New Branch Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Staff Quota: ");
        int quota = Integer.parseInt(scanner.nextLine());

        List<String> branches = Files.readAllLines(Paths.get(FilePaths.branchListPath.getPath()));
        for (String branch : branches) {
            if (branch.contains(name)) {
                System.out.println("Branch already exists!");
                return;
            }
        }

        String newBranch = name + "," + location + "," + quota;
        Files.write(Paths.get(FilePaths.branchListPath.getPath()), (newBranch + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        System.out.println("New branch opened successfully.");
    }

    /**
     * Closes an existing branch.
     * @param scanner The scanner object to read user input.
     * @throws IOException If an I/O error occurs.
     */
    private void closeBranch(Scanner scanner) throws IOException {
        List<String> branches = Files.readAllLines(Paths.get(FilePaths.branchListPath.getPath()));
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + ". " + branches.get(i));
        }

        System.out.print("Enter the number of the branch to close: ");

        System.out.println("Do you confirm to close the branch? (Y/N)");
        String confirm = scanner.nextLine().toUpperCase();
        if (!confirm.equals("Y")) {
            System.out.println("Branch closure cancelled.");
            return;
        }

        int branchIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (branchIndex >= 0 && branchIndex < branches.size()) {
            branches.remove(branchIndex);
            Files.write(Paths.get(FilePaths.branchListPath.getPath()), branches);
            System.out.println("Branch closed successfully.");
        } else {
            System.out.println("Invalid branch number.");
        }
    }
}
