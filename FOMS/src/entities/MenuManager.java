package entities;

import constants.FilePaths;
import test.ManagerPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuManager {

    public static void main(String[] args) {
        MenuManager manager = new MenuManager();
        manager.runMenuManagement();
    }

    public void runMenuManagement() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("                  __  ___                    __  ___                                                  __               ");
            System.out.println("                 /  |/  /__  ____  __  __   /  |/  /___ _____  ____ _____ ____  ____ ___  ___  ____  / /_              ");
            System.out.println(" ____________   / /|_/ / _ \\/ __ \\/ / / /  / /|_/ / __ `/ __ \\/ __ `/ __ `/ _ \\/ __ `__ \\/ _ \\/ __ \\/ __/  ____________");
            System.out.println("/_____/_____/  / /  / /  __/ / / / /_/ /  / /  / / /_/ / / / / /_/ / /_/ /  __/ / / / / /  __/ / / / /_   /_____/_____/");
            System.out.println("              /_/  /_/\\___/_/ /_/\\__,_/  /_/  /_/\\__,_/_/ /_/\\__,_/\\__, /\\___/_/ /_/ /_/\\___/_/ /_/\\__/                ");
            System.out.println("                                                                  /____/                                                ");
            System.out.println("1. Add Food Item");
            System.out.println("2. Remove Food Item");
            System.out.println("3. Edit Food Item");
            System.out.println("4. View Menu Item");
            System.out.println("5. Exiting Menu Management...");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        addFoodItem(scanner);
                    } catch (IOException e) {
                        System.err.println("Error adding food item: " + e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        removeFoodItem(scanner);
                    } catch (IOException e) {
                        System.err.println("Error removing food item: " + e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        editFoodItem(scanner);
                    } catch (IOException e) {
                        System.err.println("Error editing food item: " + e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        viewFoodItem();
                    } catch (IOException e) {
                        System.err.println("Error viewing food item: " + e.getMessage());
                    }
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting Menu Management...");
                    new ManagerPage().showManagerMenu();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public void addFoodItem(Scanner scanner) throws IOException {
        System.out.print("Enter Food Name: ");
        String name = scanner.nextLine();
    
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());
    
        // Read all branches from branch_list.csv
        List<String> branches = Files.readAllLines(Paths.get(FilePaths.branchListPath.getPath()))
                                     .stream()
                                     .skip(1) // Skip header line
                                     .map(line -> line.split(",")[0]) // Extract branch name
                                     .collect(Collectors.toList());
    
        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine();
    
        // Validate if the entered branch exists
        if (!branches.contains(branch)) {
            System.out.println("Branch does not exist. Please enter a valid branch name.");
            return; // Exit the method if the branch does not exist
        }
    
        System.out.print("Enter Category (e.g., side, burger, drink, set meal): ");
        String category = scanner.nextLine().toLowerCase();
    
        String newFoodItem = String.join(",", name, String.valueOf(price), branch, category);
        Files.write(Paths.get(FilePaths.menuListPath.getPath()), (newFoodItem + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        System.out.println("Food item added successfully.");
    }
    

    private void removeFoodItem(Scanner scanner) throws IOException {
        System.out.println("Name,Price,Branch,Category");
        List<String> menuItems = Files.readAllLines(Paths.get(FilePaths.menuListPath.getPath()));
        for (int i = 1; i < menuItems.size(); i++) {
            System.out.println((i) + ". " + menuItems.get(i));
        }

        System.out.print("Enter the number of the food item to remove: ");
        int itemIndex = Integer.parseInt(scanner.nextLine());

        if (itemIndex >= 1 && itemIndex < menuItems.size()) {
            menuItems.remove(itemIndex);
            Files.write(Paths.get(FilePaths.menuListPath.getPath()), menuItems);
            System.out.println("Food item removed successfully.");
        } else {
            System.out.println("Invalid food item number.");
        }
    }

    public void editFoodItem(Scanner scanner) throws IOException {
        System.out.println("Name,Price,Branch,Category");
        List<String> menuItems = Files.readAllLines(Paths.get(FilePaths.menuListPath.getPath()));
        for (int i = 1; i < menuItems.size(); i++) {
            System.out.println((i) + ". " + menuItems.get(i));
        }
        
        System.out.print("Enter the number of the food item to edit: ");
        int itemIndex = Integer.parseInt(scanner.nextLine());

        if (itemIndex >= 1 && itemIndex < menuItems.size()) {
            String[] itemParts = menuItems.get(itemIndex).split(",");
            String name = itemParts[0];
            double price = Double.parseDouble(itemParts[1]);
            String branch = itemParts[2];
            String category = itemParts[3];

            System.out.print("Enter new Food Name (leave empty to keep the same): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                name = newName;
            }

            System.out.print("Enter new Price (leave empty to keep the same): ");
            String newPriceInput = scanner.nextLine();
            if (!newPriceInput.isEmpty()) {
                price = Double.parseDouble(newPriceInput);
            }

            System.out.print("Enter new Branch (leave empty to keep the same): ");
            String newBranch = scanner.nextLine();
            if (!newBranch.isEmpty()) {
                branch = newBranch;
            }

            System.out.print("Enter new Category (leave empty to keep the same): ");
            String newCategory = scanner.nextLine().toLowerCase();
            if (!newCategory.isEmpty()) {
                category = newCategory;
            }

            // Construct the edited food item string
            String editedFoodItem = String.join(",", name, String.valueOf(price), branch, category);
            menuItems.set(itemIndex, editedFoodItem);

            // Write the updated menu items back to the file
            Files.write(Paths.get(FilePaths.menuListPath.getPath()), menuItems);
            System.out.println("Food item edited successfully.");
        } else {
            System.out.println("Invalid food item number.");
        }
    }

    public void viewFoodItem() throws IOException {
        List<String> menuItems = Files.readAllLines(Paths.get(FilePaths.menuListPath.getPath()));
        if (menuItems.isEmpty()) {
            System.out.println("The menu is currently empty.");
            return;
        }
        else {
            System.out.println("Name,Price,Branch,Category");
            for (int i = 1; i < menuItems.size(); i++) {
                System.out.println((i) + ". " + menuItems.get(i));
            }
        }
    }
}