package pages;

import entities.PaymentService;
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
        System.out.println("      _____        _____        ______  _______    ____  _____   ______              _____         _____         _____         ______   ");
        System.out.println("  ___|\\    \\   ___|\\    \\      |      \\/       \\  |    ||\\    \\ |\\     \\         ___|\\    \\    ___|\\    \\    ___|\\    \\    ___|\\     \\  ");
        System.out.println(" /    /\\    \\ |    |\\    \\    /          /\\     \\ |    | \\ \\    \\| \\     \\       |    |\\    \\  /    /\\    \\  /    /\\    \\  |     \\     \\ ");
        System.out.println("|    |  |    ||    | |    |  /     /\\   / /\\     ||    |  \\|    \\  \\     |      |    | |    ||    |  |    ||    |  |____| |     ,_____/|");
        System.out.println("|    |__|    ||    | |    | /     /\\ \\_/ / /    /||    |   |     \\  |    |      |    |/____/||    |__|    ||    |    ____ |     \\--'\\_|/");
        System.out.println("|    .--.    ||    | |    ||     |  \\|_|/ /    / ||    |   |    |\\ \\|    |      |    ||    |||    .--.    ||    |   |    ||     /___/|  ");
        System.out.println("|    |  |    ||    | |    ||     |       |    |  ||    |   |    || \\    |      |    ||____|/|    |  |    ||    |   |_,  ||     \\____|\\ ");
        System.out.println("|____|  |____||____|/____/||\\____\\       |____|  /|____|   |____||\\_____/|      |____|       |____|  |____||\\ ___\\___/  /||____ '     /|");
        System.out.println("|    |  |    ||    /    | || |    |      |    | / |    |   |    |/ \\   ||      |    |       |    |  |    || |   /____ / ||    /_____/ |");
        System.out.println("|____|  |____||____|____|/  \\|____|      |____|/  |____|   |____|   |___|/      |____|       |____|  |____| \\|___|    | / |____|     | / ");
        System.out.println("  \\(      )/    \\(    )/       \\(          )/       \\(       \\(       )/          \\(           \\(      )/     \\( |____|/    \\( |_____|/ ");
        System.out.println("   '      '      '    '         '          '         '        '       '            '            '      '       '   )/        '    )/    ");
        System.out.println("                                                                                                                   '              '       ");
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin Commands:");
        System.out.println("  1 - Add a new payment");
        System.out.println("  2 - View an existing payment");
        System.out.println("  3 - Remove an existing payment");
        System.out.println("  4 - Other admin tasks...");
        System.out.println("  q - Exit the admin panel & return to login page");

        while (true) {
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
