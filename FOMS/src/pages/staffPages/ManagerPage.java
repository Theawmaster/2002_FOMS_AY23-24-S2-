/**
 * [ARCHIVED] please archive this when youre done transferring the functions to the correct page
 */

package pages.staffPages;

import entities.MenuItem;
import entities.Order;
import entities.OrderManager;
import test.archivedLoginPage;
import entities.MenuManager;
import constants.OrderStatus;
import constants.MealCategory;
import utilities.LoadStaffs;
import java.util.Scanner;

public class ManagerPage {
     /**
     * Constructor for ManagerPage class.
     * Creates a new instance of the ManagerPage and displays the manager menu.
     */
    public ManagerPage() {
        showManagerMenu(); // Show the manager menu upon creation
    }

    /**
     * Displays the manager menu and handles manager commands.
     * Allows managers to simulate orders or return to the login page.
     */
    public void showManagerMenu() {

        Scanner scanner = new Scanner(System.in);


        String command;

        do {
            System.out.println("                    _______                                          ______                                       ");
            System.out.println(" ______ ______     |   |   |.---.-.-----.---.-.-----.-----.----.    |   __ \\.---.-.-----.-----.     ______ ______ ");
            System.out.println("|______|______|    |       ||  _  |     |  _  |  _  |  -__|   _|    |    __/|  _  |  _  |  -__|    |______|______|");
            System.out.println("                   |__|_|__||___._|__|__|___._|___  |_____|__|      |___|   |___._|___  |_____|                   ");
            System.out.println("                                              |_____|                             |_____|                          ");
            System.out.println("Manager Commands:");
            System.out.println("  1 - Order Management");
            System.out.println("  2 - Menu Management");
            System.out.println("  3 - View all Staffs");
            System.out.println("  q - Return to Login Page");
            System.out.print("\nEnter manager command: ");
            command = scanner.nextLine().trim();

            switch (command) {
                case "1":
                    OrderManager orderManager = new entities.OrderManager();
                    orderManager.runOrderManagement();
                    break;
                case "2":
                    MenuManager menuManager = new entities.MenuManager();
                    menuManager.runMenuManagement();
                    break;
                case "3":
                    LoadStaffs loadStaffs = new LoadStaffs();
                    loadStaffs.viewAllStaff();
                    break;
                case "q":
                    System.out.println("Returning to Login Page.");
                    new archivedLoginPage().showLoginPage(); // Return to the login page
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equals("q"));
                // Close the scanner to prevent resource leak
                scanner.close();
    }
}
