package services.payments;

import java.util.ArrayList;

import utilities.Logger;
import utilities.UserInputHelper;
import utilities.exceptionHandlers.PaymentServiceDisabledException;
import utilities.exceptionHandlers.TransactionFailedException;

public class PaypalPaymentService implements iPaymentService{

    private boolean isEnabled;
    private ArrayList<PaymentDetails> transactionHist;

    public PaypalPaymentService(){
        this.isEnabled = true;
        transactionHist = new ArrayList<>();
    }

    @Override
    public String getPaymentTypeName(){ return "Paypal"; }
    @Override
    public boolean checkEnabled(){ return this.isEnabled; }
    @Override
    public void setEnabled(boolean enable){
        this.isEnabled = enable;
    }
    @Override
    public void pay(int customerID, double amount) throws TransactionFailedException{
        if(this.isEnabled){
            try{
                simulatePay(amount);
                this.transactionHist.add(new PaymentDetails(customerID, amount, this));
                System.out.println(Logger.ANSI_GREEN+"Payment via " + this.getPaymentTypeName() + " successful."+Logger.ANSI_RESET);
                return;
            }
            catch (TransactionFailedException e){throw e;}
        }
        else
            throw new PaymentServiceDisabledException(Logger.ANSI_RED+this.getPaymentTypeName() + " is currently disabled!"+Logger.ANSI_RESET);
    }
    @Override
    public ArrayList<PaymentDetails> getTransactionHist(){
        return this.transactionHist;
    }
    private void simulatePay(double amount) throws TransactionFailedException{
        // get user to type "tap" to simulate Apple pay payment
        String simulatedSwipe = UserInputHelper.getInput("Please type 'Paypal' to transact $"+String.format("%.2f", amount));
        if(simulatedSwipe.equalsIgnoreCase("paypal")) return;
        else throw new TransactionFailedException(Logger.ANSI_RED+"Visa transaction failed"+Logger.ANSI_RESET);
    }
}