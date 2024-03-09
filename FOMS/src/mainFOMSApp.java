/*
This is the main application for the FOMS. Run this to begin the ordering system
 */
import java.util.Scanner;

public class mainFOMSApp {
    public static void main(String[] args) throws Exception {
        System.out.println("1. Browse menu");
        System.out.println("2. Checkout");
        System.out.println("3. Check order status");
        System.out.println("4. Pick up order");
        System.out.println("0. Log in as staff");

        try{ // TODO: implement try-catch for invalid choice
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    
                    break;
                
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
        catch(Exception e){ // TODO: implement catch
            System.out.println("Invalid input! Please try again.");
            System.out.println(e);
        }

    }
}
