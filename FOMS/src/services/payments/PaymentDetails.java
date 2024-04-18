package services.payments;

/**
 * This class represents a record of payment transaction.
 * @author Siah Yee Long
 */
public class PaymentDetails {
    private double amount;
    private int customerID;
    private iPaymentService paymentType;
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
        this.confirmation = "Customer "+ this.customerID + " paid $" + this.amount + " with " + this.paymentType.getPaymentTypeName();
    }
    /**
     * Simply prints out the confirmation slip (just to prove the customer paid $x using what payment type)
     */
    public void printConfirmation(){ System.out.println(this.confirmation); }
}
