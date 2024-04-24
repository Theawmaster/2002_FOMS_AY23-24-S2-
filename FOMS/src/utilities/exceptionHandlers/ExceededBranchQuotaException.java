package utilities.exceptionHandlers;

/**
 * This class is a custom exception class that is thrown when a branch has exceeded its staff quota
 * @author Siah Yee Long
 */
public class ExceededBranchQuotaException extends StaffManagementException{
    /**
     * This constructor creates an exception with a custom message
     * @param message
     */
    public ExceededBranchQuotaException(String message) {
        super(message);
    }
    /**
     * This constructor creates an exception with a default message
     */
    public ExceededBranchQuotaException(){
        super("This branch has exceeded its staff quota!");
    }
}
