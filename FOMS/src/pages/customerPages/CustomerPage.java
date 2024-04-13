package pages.customerPages;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;

public class CustomerPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public CustomerPage(Session s){
        this.session = s;
    }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        System.out.println("[1] Browse menu");
        System.out.println("[2] View cart");
        //...
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                pageViewer.changePage("BrowseCategoriesPage");
                break;
            case "2":
                pageViewer.changePage("ViewOrderPage");
                break;
                //...
            default:
                break;
        }
    }
}
