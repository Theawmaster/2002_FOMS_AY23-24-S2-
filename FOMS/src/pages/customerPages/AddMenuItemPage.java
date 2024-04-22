package pages.customerPages;

import pages.iPage;
import pages.PageViewer;
import utilities.Session;

public class AddMenuItemPage implements iPage{
    private Session session;

    public AddMenuItemPage(Session s){
        this.session = s;
    }

    public void viewOptions(){
        System.out.println("<< " + this.session.getCurrentActiveMenuItem().getFood()+ " >>");
        System.out.println("Price: $ "+this.session.getCurrentActiveMenuItem().getPrice());
        System.out.println("\n'"+this.session.getCurrentActiveMenuItem().getDescription()+"'");
        System.out.println("Category: " + this.session.getCurrentActiveMenuItem().getCategory());
        System.out.println("Branch: " + this.session.getCurrentActiveMenuItem().getBranch().getBranchName());
        System.out.println("\nAdd this item to your order? [Y/N]:");
    }
    public void handleInput(String choice){
        switch (choice) {
            case "Y":
            case "y":
                if(this.session.getCurrentActiveOrder()==null) this.session.makeNewOrder();
                this.session.getCurrentActiveOrder().addItem(this.session.getCurrentActiveMenuItem());
                System.out.println("Item added to your order");
                //TODO: ask to pay or something?
                PageViewer.changePage("CustomerPage");
                break;
            case "N":
            case "n":
                System.out.println("Item not added");
                PageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
