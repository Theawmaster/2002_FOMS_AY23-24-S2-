package test.archives;

import entities.Payment;
import constants.FilePaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class archiveLoadPaymentService {

    /**
     * Attempts to load a payment from the CSV file based on the given payment ID.
     * The method reads the CSV file line by line, searching for a matching payment ID.
     * If found, it creates and returns a new Payment object with the details from the CSV.
     * If the payment ID is not found in the CSV file, the method returns null.
     *
     * @param paymentId The ID of the payment to be loaded from the CSV file.
     * @return A new Payment object if the payment ID is found in the CSV file; otherwise, null.
     */
    public static Payment loadPaymentFromCSV(String paymentId) {
        String csvFilePath = FilePaths.paymentListPath.getPath();
        try {
            List<String> lines = Files.readAllLines(Paths.get(csvFilePath));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(paymentId)) {
                    double amount = Double.parseDouble(parts[1]);
                    String type = parts[2];
                    return new Payment(paymentId, amount, type); // Assume Payment constructor exists
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading payments from CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("An error occurred while parsing payment amount: " + e.getMessage());
        }
        return null;
    }
}
