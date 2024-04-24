package utilities.exceptionHandlers;

/**
 * This class is a custom exception class that is thrown when a payment service is disabled
 * @author Siah Yee Long
 */
public class PaymentServiceDisabledException extends TransactionFailedException{
    /**
     * This constructor creates an exception with a custom message
     * @param s
     */
    public PaymentServiceDisabledException(String s){
        super(s);
    }
    /**
     * This constructor creates an exception with a default message
     */
    public PaymentServiceDisabledException(){
        super("This payment method is currently unavailable!");
    }
}
