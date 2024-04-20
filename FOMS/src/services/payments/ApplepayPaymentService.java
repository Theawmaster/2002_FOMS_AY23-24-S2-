package services.payments;

import java.util.ArrayList;

import exceptionHandlers.TransactionFailedException;
import utilities.UserInputHelper;
import exceptionHandlers.PaymentServiceDisabledException;

/**
 * @author Siah Yee Long
 */
public class ApplepayPaymentService implements iPaymentService {
    /**
     * Determines if current payment service is enabled
     */
    private boolean isEnabled;
    /**
     * A list of PaymentDetails which used this service
     */
    ArrayList<PaymentDetails> transactionHist;
    /**
     * Constructor called from Session
     */
    public ApplepayPaymentService(){
        this.isEnabled = true;
        transactionHist = new ArrayList<>();
    }
    /**
     * @return the name of the payment service
     */
    @Override
    public String getPaymentTypeName(){ return "Apple pay"; }
    /**
     * @return if the current payment service is enabled
     */
    @Override
    public boolean checkEnabled(){ return this.isEnabled; }
    /**
     * @param enable to enable / disable the payment service 
     */
    @Override
    public void setEnabled(boolean enable){
        this.isEnabled = enable;
    }
    /**
     * The method used to make a transaction. Payment details stored in the transaction history
     * @param customerID the ID of the customer
     * @param amount the amount to pay
     */
    @Override
    public void pay(int customerID, double amount) throws TransactionFailedException{
        if(this.isEnabled){
            try{
                simulateTap(amount);
                this.transactionHist.add(new PaymentDetails(customerID, amount, this));
                System.out.println("Payment via " + this.getPaymentTypeName() + " successful.");
            }
            catch (TransactionFailedException e){
                throw e;
            }
        }
        else
            throw new PaymentServiceDisabledException(this.getPaymentTypeName() + " is currently disabled!");
    }
    /**
     * @return an array list of payment details
     */
    @Override
    public ArrayList<PaymentDetails> getTransactionHist(){
        return this.transactionHist;
    }
    /**
     * Private method to simulate waiting for user to tap iPhone
     * @param amount amount user has to pay
     * @throws TransactionFailedException if user fails to tap properly
     */
    private void simulateTap(double amount) throws TransactionFailedException{
        // get user to type "tap" to simulate Apple pay payment
        String simulatedTap = UserInputHelper.getInput("Please tap your phone to transact $"+amount+". (type 'tap'):");
        if(simulatedTap.equalsIgnoreCase("tap")) return;
        else throw new TransactionFailedException("Apple pay transaction failed");
    }
}
