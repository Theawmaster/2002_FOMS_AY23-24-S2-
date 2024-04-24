package utilities.exceptionHandlers;

public class TransactionFailedException extends Exception{
    public TransactionFailedException(String s){
        super(s);
    }
    public TransactionFailedException(){
        super("The transaction failed!");
    }
}
