package pages.staffPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

public class StaffViewOrderDetailsPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public StaffViewOrderDetailsPage(Session s){
        this.session = s;
    }
    /**
     * Method to view order details options
     */
    public void viewOptions(){
        // to insert and display the order details given by the orderid
        System.out.println("[1] Process Order");
        System.out.println("[B] Return to Staff Process Order Page");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
            // to implement after calling payment class specifically to display order id details with payment made.
                break;
            case "b":
            case "B":
                pageViewer.changePage("StaffProcessOrderPage");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
