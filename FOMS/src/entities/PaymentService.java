package entities;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import utilities.SerialiseCSV;
import constants.FilePaths;
import utilities.LoadPaymentService;


/**
 * The PaymentService class implements the iPaymentMethods interface
 * and provides functionality for managing payments.
 */
public class PaymentService implements iPaymentMethods {
    /** A map to store payments with their unique IDs. */
    private Map<String, Payment> payments = new HashMap<>();

    /**
     * Adds a new payment.
     * Generates a unique payment ID, prompts for payment details, and stores the payment.
     */
    public void addPayment() {
        // Generate a unique payment ID
        String paymentId = UUID.randomUUID().toString();
        Scanner scanner = new Scanner(System.in);
        double amount;

        while (true) {
            System.out.print("Enter payment amount: ");
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();  // Consume newline
                break;  // Exit the loop if input was successful
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine();  // Consume the invalid input
            }
        }

        System.out.println("                                                            ");
        System.out.println(",------.                                             ,--.   ");
        System.out.println("|  .--. ' ,--,--.,--. ,--.,--,--,--. ,---. ,--,--, ,-'  '-. ");
        System.out.println("|  '--' |' ,-.  | \\  '  / |        || .-. :|      \\ '-.  .-' ");
        System.out.println("|  | --' \\ '-'  |  \\   '  |  |  |  |\\   --.|  ||  |  |  |   ");
        System.out.println("`--'      `--`--'.-'  /   `--`--`--' `----'`--''--'  `--'   ");
        System.out.println("                 `---'                                       ");
        System.out.println("Choose payment type:");
        System.out.println("1. Card");
        System.out.println("2. Online");
        System.out.println("3. Cash");
        System.out.print("Enter choice (1-3): ");
        
        String paymentType = "";
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    paymentType = "card";
                    simulateCardPayment(scanner);
                    break;
                case 2:
                    paymentType = "online";
                    simulateOnlinePayment(scanner);
                    break;
                case 3:
                    paymentType = "cash";
                    simulateCashPayment(amount);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1, 2, or 3.");
                    continue;
            }
            break;  // Exit the loop if a valid choice was made
        }



        Payment payment = new Payment(paymentId, amount, paymentType);
        payments.put(paymentId, payment);
        System.out.println("Payment added. Payment ID: " + paymentId);

        // Write payment data to CSV file
        String csvData = paymentId + "," + amount + "," + paymentType;
        SerialiseCSV.writeToCSV(csvData, FilePaths.paymentListPath.getPath());
        
    }

    /**
     * Prompts the user to enter a payment ID and displays the details of the corresponding payment.
     * The method first checks the in-memory map for the payment ID. If not found, it attempts to
     * load the payment details from the CSV file. If the payment ID is still not found, it informs
     * the user that the payment ID does not exist.
     */
    public void viewPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter payment ID to view: ");
        String paymentId = scanner.nextLine();
    
        if (payments.containsKey(paymentId)) {
            Payment payment = payments.get(paymentId);
            displayPaymentDetails(paymentId, payment);
        } else {
            // Use the PaymentLoader utility to load the payment from the CSV
            Payment payment = LoadPaymentService.loadPaymentFromCSV(paymentId);
            if (payment != null) {
                displayPaymentDetails(paymentId, payment);
            } else {
                System.out.println("Payment ID not found.");
            }
        }
    }

        /**
         * Displays the details of a payment given its ID and the Payment object.
         * This helper method is used to consolidate the logic for displaying payment details,
         * making it reusable within the class wherever payment details need to be displayed.
         *
         * @param paymentId The ID of the payment whose details are to be displayed.
         * @param payment The Payment object containing the details of the payment.
         */
    private void displayPaymentDetails(String paymentId, Payment payment) {
        System.out.println("Payment Details:");
        System.out.println("ID: " + paymentId);
        System.out.println("Amount: " + payment.getAmount());
        System.out.println("Type: " + payment.getPaymentType());
        // Add more details as necessary
    }

    /**
     * Removes an existing payment based on the payment ID.
     */
    public void removePayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter payment ID to remove: ");
        String paymentId = scanner.nextLine();

        String csvFilePath = FilePaths.paymentListPath.getPath();
        ArrayList<String> linesToKeep = new ArrayList<>();

        try {
            // Read all lines from the CSV file
            List<String> allLines = Files.readAllLines(Paths.get(csvFilePath));

            // Filter out the line with the specified payment ID
            for (String line : allLines) {
                if (!line.startsWith(paymentId + ",")) {
                    linesToKeep.add(line);
                }
            }

            // Rewrite the file with the remaining lines
            Files.write(Paths.get(csvFilePath), linesToKeep, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Payment removed from CSV file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while removing the payment from the CSV file: " + e.getMessage());
        }
    }

    /**
     * Simulates a card payment process.
     *
     * @param scanner the scanner object used for user input
     */
    private void simulateCardPayment(Scanner scanner) {
        System.out.println("Simulating card payment...");
        // You could prompt for and simulate validating card details here
    }

    /**
     * Simulates an online payment process.
     *
     * @param scanner the scanner object used for user input
     */
    private void simulateOnlinePayment(Scanner scanner) {
        System.out.println("Simulating online payment...");
        // You could prompt for and simulate validating online payment account details here
    }

    /**
     * Simulates a cash payment process.
     *
     * @param amount the amount of cash to be paid
     */
    private void simulateCashPayment(double amount) {
        System.out.println("Simulating cash payment for amount: " + amount);
        // Cash payments might just involve confirming the received amount
    }

}
