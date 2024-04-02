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
     * Removes an existing payment.
     */
    void removePayment();
}