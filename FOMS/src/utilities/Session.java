package utilities;

import java.util.ArrayList;

import entities.Staff;
import services.payments.iPaymentService;
import services.payments.VisaPaymentService;
import services.payments.CashPaymentService;
import services.payments.ApplepayPaymentService;
import entities.Branch;
import entities.MenuItem;
import entities.Order;


/**
 * This class acts as a data store and contains all active entities in the programme.
 * @author Siah Yee Long
 * TODO: add in javadoc comments 
 */
public class Session {
    private ArrayList<Staff> allStaffs;
    protected ArrayList<Branch> allBranches;
    private ArrayList<MenuItem> allMenuItems;
    private ArrayList<iPaymentService> allPaymentServices;

    private Staff currentActiveStaff;
    private Branch currentActiveBranch;
    private Order currentActiveOrder;
    private MenuItem currentActiveMenuItem;


    /**
     * Initialised in pageViewer only. Loads all data from LoadData's child classes to set the context of the current session
     */
    public Session(){

        LoadBranches initLoadBranches = new LoadBranches(null);
        this.allBranches = initLoadBranches.getLoadedData();
    
        LoadStaffs initLoadStaffs = new LoadStaffs(this.allBranches);
        this.allStaffs = initLoadStaffs.getLoadedData();
    
        LoadMenuItems initLoadMenuItems = new LoadMenuItems(this.allBranches);
        this.allMenuItems = initLoadMenuItems.getLoadedData();
    
        allPaymentServices = new ArrayList<>();
        allPaymentServices.add(new VisaPaymentService());
        allPaymentServices.add(new CashPaymentService());
        allPaymentServices.add(new ApplepayPaymentService());
        // add more payment services if added
    }

    public ArrayList<Staff> getAllStaffs(){
        return this.allStaffs;
    }
    public ArrayList<Branch> getAllBranches(){
        return this.allBranches;
    }
    public ArrayList<MenuItem> getAllMenuItems(){
        return this.allMenuItems;
    }
    public ArrayList<iPaymentService> getAllPaymentServices(){
        return this.allPaymentServices;
    }

    public void setCurrentActiveStaff(Staff s){
        this.currentActiveStaff = s;
    }
    public void setCurrentActiveBranch(Branch b){
        this.currentActiveBranch = b;
    }
    public void setCurrentActiveOrder(Order o){
        this.currentActiveOrder = o;
    }
    public void setCurrentActiveMenuItem(MenuItem menuItem){
        this.currentActiveMenuItem = menuItem;
    }

    public Staff getCurrentActiveStaff(){
        return this.currentActiveStaff;
    }
    public Branch getCurrentActiveBranch(){
        return this.currentActiveBranch;
    }
    public Order getCurrentActiveOrder(){
        return this.currentActiveOrder;
    }
    public MenuItem getCurrentActiveMenuItem(){
        return this.currentActiveMenuItem;
    }
}
