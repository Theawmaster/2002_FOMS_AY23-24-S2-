package utilities.exceptionHandlers;

/**
 * This class is a custom exception class that is thrown when a transaction fails
 * @author Siah Yee Long
 */
public class TransactionFailedException extends Exception{
    /**
     * This constructor creates an exception with a custom message
     * @param s
     */
    public TransactionFailedException(String s){
        super(s);
    }
    /**
     * This constructor creates an exception with a default message
     */
    public TransactionFailedException(){
        super("The transaction failed!");
    }
}
