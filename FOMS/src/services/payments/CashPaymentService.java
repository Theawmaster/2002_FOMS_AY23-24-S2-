package services.payments;

import java.util.ArrayList;

import utilities.Logger;
import utilities.UserInputHelper;
import utilities.exceptionHandlers.PaymentServiceDisabledException;
import utilities.exceptionHandlers.TransactionFailedException;

/**
 * This class is used to handle Cash transactions. It implements iPaymentService
 * @author Siah Yee Long
 */
public class CashPaymentService implements iPaymentService{
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
    public CashPaymentService(){
        this.isEnabled = true;
        transactionHist = new ArrayList<>();
    }
    /**
     * @return the name of the payment service
     */
    @Override
    public String getPaymentTypeName(){ return "Cash"; }
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
                askForCash(amount);
                this.transactionHist.add(new PaymentDetails(customerID, amount, this));
                System.out.println(Logger.ANSI_GREEN+"Payment via " + this.getPaymentTypeName() + " successful."+Logger.ANSI_RESET);
            }
            catch (TransactionFailedException e){
                throw e;
            }
        }
        else
            throw new PaymentServiceDisabledException(Logger.ANSI_RED+this.getPaymentTypeName() + " is currently disabled!"+Logger.ANSI_RESET);
    }
    /**
     * @return an array list of payment details
     */
    @Override
    public ArrayList<PaymentDetails> getTransactionHist(){
        return this.transactionHist;
    }
    private void askForCash(double amount) throws TransactionFailedException{
        double insertedAmt = 0;
        double in;
        System.out.println();
        while(insertedAmt<amount){
            System.out.println(String.format("Please pay $%.2f",(amount-insertedAmt)));
            in = UserInputHelper.getDoubleInput("Insert cash into the terminal (-1 if you no more money, otherwise type amount inserted): ");
            if(in < 0) throw new TransactionFailedException(Logger.ANSI_RED+"Customer is broke! No food for you!"+Logger.ANSI_RESET);
            insertedAmt += in;
        }
        System.out.println(String.format(Logger.ANSI_GREEN+"Here's your change of $%.2f"+Logger.ANSI_RESET,(insertedAmt-amount)));
        return;
    }
}
