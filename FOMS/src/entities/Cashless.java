package FOMSpackage.entities;

/**
 * The Cashless class represents a payment method without using physical cash.
 * It implements the iPaymentMethods interface.
 */
public class Cashless implements iPaymentMethods {

    /** The platform service used for cashless transactions. */
    private String platformService;

    /**
     * Calculates the total amount to be paid through a cashless transaction.
     *
     * @throws UnsupportedOperationException if the method is not implemented.
     */
    public void totalAmount() {
        // TODO - implement Cashless.totalAmount
        throw new UnsupportedOperationException();
    }
}
