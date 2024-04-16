package services;

import java.util.Scanner;

import exceptionHandlers.TransactionFailedException;
import services.payments.PaymentDetails;
import services.payments.iPaymentService;
import utilities.Logger;
import utilities.Session;

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
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                i = sc.nextInt()-1;

                session.getAllPaymentServices().get(i).setEnabled(true);
                System.out.println("Successfully enabled "+session.getAllPaymentServices().get(i).getPaymentTypeName());
                return;
            }
            catch (NumberFormatException e){
                System.out.println("Please enter only integer values! ");
                Logger.error(e.getMessage());
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Please select a payment service within the range only! ");
                Logger.error(e.getMessage());
            }
        }
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
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                i = sc.nextInt()-1;

                session.getAllPaymentServices().get(i).setEnabled(false);
                System.out.println("Successfully disabled "+session.getAllPaymentServices().get(i).getPaymentTypeName());
                return;
            }
            catch (NumberFormatException e){
                System.out.println("Please enter only integer values! ");
                Logger.error(e.getMessage());
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Please select a payment service within the range only! ");
                Logger.error(e.getMessage());
            }
        }
    }
    /**
     * This method processes the payment from customers
     * @param session the current context
     * @param custID the customer ID 
     * @param amount the amount to pay
     */
    public static void makePayment(Session session, int custID, double amount){
        while(true){
            System.out.println("Select payment type:");
            int i=1;
            for(iPaymentService p : session.getAllPaymentServices()){
                System.out.println(i + ". " + p.getPaymentTypeName());
                i++;
            }
            Scanner sc = new Scanner(System.in);
            try{
                i = sc.nextInt()-1;
                
                session.getAllPaymentServices().get(i).pay(custID, amount);
                return;
            }
            catch (TransactionFailedException e){
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("Please enter only integer values! ");
                Logger.error(e.getMessage());
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Please select a payment service within the range only! ");
                Logger.error(e.getMessage());
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
