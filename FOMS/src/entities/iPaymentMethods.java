package entities;

/**
 * The iPaymentMethods interface defines methods for managing payments.
 */
public interface iPaymentMethods {
    /**
     * Adds a new payment.
     */
    void addPayment();

    /**
     * Views all payments.
     */
    void viewPayment();

    /**
     * Removes an existing payment.
     */
    void removePayment();
}