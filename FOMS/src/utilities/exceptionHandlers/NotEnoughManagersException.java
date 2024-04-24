package utilities.exceptionHandlers;

public class NotEnoughManagersException extends StaffManagementException{
    public NotEnoughManagersException(String message) {
        super(message);
    }
    public NotEnoughManagersException(){
        super("This branch does not have enough managers!");
    }
}
