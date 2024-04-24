package utilities.exceptionHandlers;

/**
 * This class is a custom exception class that is thrown when an error occurs while managing staff
 * @author Siah Yee Long
 */
public class StaffManagementException extends Exception{
    /**
     * This constructor creates an exception with a custom message
     * @param message
     */
    public StaffManagementException(String message) {
        super(message);
    }
    /**
     * This constructor creates an exception with a default message
     */
    public StaffManagementException(){
        super("An error occurred while managing staff!");
    }
}
