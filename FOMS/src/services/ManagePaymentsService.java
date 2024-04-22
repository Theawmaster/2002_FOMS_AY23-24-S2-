package services;

import exceptionHandlers.TransactionFailedException;
import services.payments.PaymentDetails;
import services.payments.iPaymentService;
import utilities.Session;
import utilities.UserInputHelper;

/**
 * This class contains mainly static methods pertaining to payment management.
 * @author Siah Yee Long
 */
public class ManagePaymentsService {
    /**
     * This method displays all payment methods, and displays if it has been disabled.
     * @param session the current context
     */
    public static void viewAllPaymentMethods(Session session){
        for(iPaymentService p: session.getAllPaymentServices()){
            System.out.print("- " + p.getPaymentTypeName());
            if(p.checkEnabled()) System.out.println();
            else System.out.println(" [DISABLED]");
        }
        System.out.println();
    }
    /**
     * This method enables the chosen payment method.
     * @param session the current context
     */
    public static void enablePaymentsService(Session session){
        System.out.println("Select which payment service you want to enable:");
        int i=1;
        for(iPaymentService p : session.getAllPaymentServices()){
            System.out.println(i + ". " + p.getPaymentTypeName());
            i++;
        }
        int choice = UserInputHelper.getUserChoice("Enter your choice", i-1);
        session.getAllPaymentServices().get(choice-1).setEnabled(true);
        System.out.println("Successfully enabled "+session.getAllPaymentServices().get(choice-1).getPaymentTypeName());
        return;
    }
    /**
     * This method disables the chosen payment method.
     * @param session the current context
     */
    public static void disablePaymentsService(Session session){
        System.out.println("Select which payment service you want to disable:");
        int i=1;
        for(iPaymentService p : session.getAllPaymentServices()){
            System.out.println(i + ". " + p.getPaymentTypeName());
            i++;
        }
        int choice = UserInputHelper.getUserChoice("Enter your choice", i-1);
        session.getAllPaymentServices().get(choice-1).setEnabled(false);
        System.out.println("Successfully disabled "+session.getAllPaymentServices().get(choice-1).getPaymentTypeName());
        return;
    }
    /**
     * This method processes the payment from customers
     * @param session the current context
     * @param orderID the customer ID 
     * @param amount the amount to pay
     */
    public static boolean makePayment(Session session, int orderID, double amount){
        while(true){
            System.out.println("Select payment type:");
            int i=1;
            for(iPaymentService p : session.getAllPaymentServices()){
                System.out.println(i + ". " + p.getPaymentTypeName());
                i++;
            }
            try{
                int choice = UserInputHelper.getUserChoice("Enter your choice (c to cancel)", i-1, "c");
                if(choice == -1) return false; // user devided to cancel payment
                session.getAllPaymentServices().get(choice-1).pay(orderID, amount);
                return true;
            }
            catch (TransactionFailedException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void printAllTransactions(Session session){
        for(iPaymentService p : session.getAllPaymentServices()){
            int c = 1;
            System.out.println();
            System.out.println("Transactions made with " + p.getPaymentTypeName());
            for(PaymentDetails d : p.getTransactionHist()){
                System.out.print(c + ". ");
                d.printConfirmation();
                c++;
            }
        }
    }
}
