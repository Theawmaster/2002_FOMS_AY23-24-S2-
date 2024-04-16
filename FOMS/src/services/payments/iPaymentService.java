package services.payments;

import java.util.ArrayList;

import exceptionHandlers.TransactionFailedException;

/**
 * This interface is implemented by every new type of payment service class. PaymentServices should be able to do the following functions.
 * Each type of payment service may have their own way of processing payment.
 * This interface exemplifies SRP and OCP -- the methods are focused, and extending of payment service types is fairly easy
 * @author Siah Yee Long
 */
public interface iPaymentService {
    String getPaymentTypeName();
    boolean checkEnabled();
    void setEnabled(boolean enable);
    void pay(int customerID, double amount) throws TransactionFailedException;
    ArrayList<PaymentDetails> getTransactionHist();
}
