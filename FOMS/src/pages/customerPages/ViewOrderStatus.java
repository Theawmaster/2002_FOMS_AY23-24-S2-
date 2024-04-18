package pages.customerPages;

import java.util.Scanner;
import pages.iPage;
import pages.pageViewer;
import entities.MenuItem;
import utilities.Session;
import entities.Order;
public class ViewOrderStatus implements iPage{
    private Session session;
    private Scanner scanner;
    
    public ViewOrderStatus(Session s){
        this.session = s;
        this.scanner = new Scanner(System.in);
    }

    public void viewOptions(){
        Order currentOrder = session.getCurrentActiveOrder();
        if (currentOrder == null) {
            System.out.println("No active order found.");
            pageViewer.changePage("MainPage");
            return;
        }
        // Display order details
        currentOrder.printOrderDetails();
        System.out.println("[1] Would you like to pick up your order?");
        System.out.println("[B] Return to customer page");
    }
    public void handleInput(String input){
        
    }

}
