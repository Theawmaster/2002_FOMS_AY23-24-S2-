package test.archives;

/**
 * The Payment class represents a payment made in the system.
 */
public class archivePayment {
    /** The unique identifier for the payment. */
    private String paymentId;
    
    /** The amount of the payment. */
    private double amount;
    
    /** The type of payment (e.g., card, online, cash). */
    private String paymentType;

    /**
     * Constructs a new Payment object with the specified details.
     *
     * @param paymentId   the unique identifier for the payment
     * @param amount      the amount of the payment
     * @param paymentType the type of payment
     */
    public archivePayment(String paymentId, double amount, String paymentType) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    /**
     * Retrieves the payment ID.
     *
     * @return the payment ID
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Retrieves the amount of the payment.
     *
     * @return the amount of the payment
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the type of payment.
     *
     * @return the type of payment
     */
    public String getPaymentType() {
        return paymentType;
    }

}
