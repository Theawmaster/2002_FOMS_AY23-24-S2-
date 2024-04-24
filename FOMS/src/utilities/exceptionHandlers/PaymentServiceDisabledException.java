package utilities.exceptionHandlers;

public class PaymentServiceDisabledException extends TransactionFailedException{
    public PaymentServiceDisabledException(String s){
        super(s);
    }
    public PaymentServiceDisabledException(){
        super("This payment method is currently unavailable!");
    }
}
