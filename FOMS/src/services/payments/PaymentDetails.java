package services.payments;

/**
 * This class represents a record of payment transaction.
 * @author Siah Yee Long
 */
public class PaymentDetails {
    /**
     * The amount paid by the customer
     */
    private double amount;
    /**
     * The ID of the customer
     */
    private int customerID;
    /**
     * The payment type used by the customer
     */
    private iPaymentService paymentType;
    /**
     * The confirmation slip
     */
    private String confirmation;
    /**
     * Constructor to initialise the payment details
     * @param ID
     * @param amt
     * @param pmtType
     */
    public PaymentDetails(int ID, double amt, iPaymentService pmtType){
        this.customerID = ID;
        this.amount = amt;
        this.paymentType = pmtType;
        this.confirmation = "Customer "+ this.customerID + String.format(" paid $%.2f",  this.amount)  + " with " + this.paymentType.getPaymentTypeName();
    }
    /**
     * Simply prints out the confirmation slip (just to prove the customer paid $x using whatever payment type)
     */
    public void printConfirmation(){ System.out.println(this.confirmation); }
}
