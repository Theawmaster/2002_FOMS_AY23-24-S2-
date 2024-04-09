package pages;

import entities.PaymentService;
import utilities.LoadStaffs;
import java.util.Scanner;

/**
 * This class provides a simulation command-line interface (CLI) for managing payments
 */
public class AdminPage {

    private PaymentService paymentService;

    /**
     * Constructor for AdminPage, initializing the PaymentService instance.
     */
    public AdminPage() {
        this.paymentService = new PaymentService();
        showAdminPage();
    }

    /**
     * Displays the admin page with various commands for managing payments.
     */
    public void showAdminPage() {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("                    _______     __            __             ______                                       ");
            System.out.println(" ______ ______     |   _   |.--|  |.--------.|__|.-----.    |   __ \\.---.-.-----.-----.     ______ ______ ");
            System.out.println("|______|______|    |       ||  _  ||        ||  ||     |    |    __/|  _  |  _  |  -__|    |______|______|");
            System.out.println("                   |___|___||_____||__|__|__||__||__|__|    |___|   |___._|___  |_____|                   ");
            System.out.println("                                                                          |_____|                          ");
            System.out.println("Admin Commands:");
            System.out.println("  1 - Add a new payment");
            System.out.println("  2 - View an existing payment");
            System.out.println("  3 - Remove an existing payment");
            System.out.println("  4 - View all staff members");
            System.out.println("  q - Exit the admin panel & return to login page");
            System.out.print("\nEnter admin command: ");
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "1":
                    // Simulate adding a payment
                    paymentService.addPayment();
                    break;
                case "2":
                    // Simulate viewing a payment
                    paymentService.viewPayment();
                    break;
                case "3":
                    // Simulate removing a payment
                    paymentService.removePayment();
                    break;
                case "4":
                    // Simulate viewing all staff members
                    LoadStaffs loadStaffs = new LoadStaffs();
                    loadStaffs.viewAllStaff();
                    break;
                case "q":
                    // Exit the admin panel
                    System.out.println("Exiting Admin Panel and returning to Login Page...");
                    new LoginPage().showLoginPage();
                default:
                    // Handle other admin tasks...
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }
}
