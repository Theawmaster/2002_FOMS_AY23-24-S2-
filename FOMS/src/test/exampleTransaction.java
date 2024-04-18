package test;

import services.ManagePaymentsService;
import utilities.Session;

/**
 * This is just an example of how a transaction can be made. Play to view it in action
 * @author Siah Yee Long
 */
public class exampleTransaction {
    public static void main(String[] args) {
        Session s = new Session();

        //iterate through 10 transactions to ask for $10.40
        for(int i=0; i<10; i++){
            //this method will get user to choose what type to pay with
            ManagePaymentsService.makePayment(s, i, 10.4);
        }
        //before terminating, see all the transactions recorded
        ManagePaymentsService.printAllTransactions(s);
    }
}
