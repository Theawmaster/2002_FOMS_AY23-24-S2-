/**
 * This class provides a simulation command-line interface (CLI) for managing payments
 */

package entities;

import java.util.Scanner;

public class PaymentCLI {

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Payment Management CLI ===");
        System.out.println("Commands:");
        System.out.println("  add - Add a new payment");
        System.out.println("  view - View an existing payment");
        System.out.println("  remove - Remove an existing payment");
        System.out.println("  exit - Exit the program");

        while (true) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "add":
                    // Simulate adding a payment
                    paymentService.addPayment();
                    break;
                    case "view":
                    // Simulate viewing a payment
                    paymentService.viewPayment();
                    break;
                case "remove":
                    // Simulate removing a payment
                    paymentService.removePayment();
                    break;
                case "exit":
                    // Exit the CLI
                    System.out.println("Exiting Payment Management CLI.");
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }
}
