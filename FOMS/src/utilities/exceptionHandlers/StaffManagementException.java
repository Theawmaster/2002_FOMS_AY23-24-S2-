package utilities.exceptionHandlers;

public class StaffManagementException extends Exception{
    public StaffManagementException(String message) {
        super(message);
    }
    public StaffManagementException(){
        super("An error occurred while managing staff!");
    }
}
