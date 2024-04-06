/**
 * This class provides a simulation command-line interface (CLI) for managing payments
 */

package entities;

import java.util.Scanner;

public class PaymentCLI {

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Commands:");
        System.out.println("  1 - Add a new payment");
        System.out.println("  2 - View an existing payment");
        System.out.println("  3 - Remove an existing payment");
        System.out.println("  q - Exit the program");

        while (true) {
            System.out.print("\nEnter command: ");
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
                    // Exit the CLI
                    System.out.println("Exiting Payment Management CLI.");
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }
}
