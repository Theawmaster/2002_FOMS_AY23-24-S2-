package utilities.exceptionHandlers;

public class ExceededBranchQuotaException extends StaffManagementException{
    public ExceededBranchQuotaException(String message) {
        super(message);
    }
    public ExceededBranchQuotaException(){
        super("This branch has exceeded its staff quota!");
    }
}
