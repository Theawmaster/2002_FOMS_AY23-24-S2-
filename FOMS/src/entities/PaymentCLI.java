package entities;

import java.util.Scanner;

public class PaymentCLI {

    public static void main(String[] args) {
        PaymentManager paymentManager = new PaymentManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Payment Management CLI ===");
        System.out.println("Commands:");
        System.out.println("  add - Add a new payment");
        System.out.println("  remove - Remove an existing payment");
        System.out.println("  exit - Exit the program");

        while (true) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "add":
                    // Simulate adding a payment
                    paymentManager.addPayment();
                    break;
                case "remove":
                    // Simulate removing a payment
                    paymentManager.removePayment();
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
