package utilities.exceptionHandlers;

/**
 * This class is a custom exception class that is thrown when a branch does not have enough managers
 * @author Siah Yee Long
 */
public class NotEnoughManagersException extends StaffManagementException{
    /**
     * This constructor creates an exception with a custom message
     * @param message
     */
    public NotEnoughManagersException(String message) {
        super(message);
    }
    /**
     * This constructor creates an exception with a default message
     */
    public NotEnoughManagersException(){
        super("This branch does not have enough managers!");
    }
}
