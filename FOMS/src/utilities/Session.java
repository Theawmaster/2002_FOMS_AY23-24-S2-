package utilities;

import java.util.ArrayList;

import constants.OrderStatus;
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
    private ArrayList<Branch> allBranches;
    private ArrayList<Branch> allOpenBranches;
    private ArrayList<MenuItem> allMenuItems;
    private ArrayList<Order> allOrders;
    private ArrayList<iPaymentService> allPaymentServices;

    private Staff currentActiveStaff;
    private Branch currentActiveBranch;
    private Order currentActiveOrder;
    private MenuItem currentActiveMenuItem;
    private int nextOrderId;


    /**
     * Initialised in PageViewer only. Loads all data from LoadData's child classes to set the context of the current session
     */
    public Session(){

        LoadBranches initLoadBranches = new LoadBranches(null, null);
        this.allBranches = initLoadBranches.getLoadedData();

        this.allOpenBranches = new ArrayList<>();
        for(Branch b : this.allBranches){
            if(b.getStatus().equalsIgnoreCase("Open")){
                this.allOpenBranches.add(b);
            }
        }
    
        LoadStaffs initLoadStaffs = new LoadStaffs(this.allBranches, null);
        this.allStaffs = initLoadStaffs.getLoadedData();
    
        LoadMenuItems initLoadMenuItems = new LoadMenuItems(this.allBranches, null);
        this.allMenuItems = initLoadMenuItems.getLoadedData();

        this.nextOrderId = 1;
    
        allPaymentServices = new ArrayList<>();
        allPaymentServices.add(new VisaPaymentService());
        allPaymentServices.add(new CashPaymentService());
        allPaymentServices.add(new ApplepayPaymentService());
        // add more payment services if added
    }

    // Update session if required
    public void updateSession(){
        LoadBranches initLoadBranches = new LoadBranches(null, null);
        this.allBranches = initLoadBranches.getLoadedData();

        this.allOpenBranches = new ArrayList<>();
        for(Branch b : this.allBranches){
            if(b.getStatus().equalsIgnoreCase("Open")){
                this.allOpenBranches.add(b);
            }
        }
    
        LoadStaffs initLoadStaffs = new LoadStaffs(this.allBranches, null);
        this.allStaffs = initLoadStaffs.getLoadedData();
    
        LoadMenuItems initLoadMenuItems = new LoadMenuItems(this.allBranches, null);
        this.allMenuItems = initLoadMenuItems.getLoadedData();

        // if there is another instance of the programme, there will be orders that are not loaded in yet.
        LoadOrders initLoadOrders = new LoadOrders(this.allBranches, this.allMenuItems);
        this.allOrders = initLoadOrders.getLoadedData();

        if(this.currentActiveOrder==null || this.currentActiveOrder.getStatus()==OrderStatus.COMPLETED || this.currentActiveOrder.getStatus()==OrderStatus.CANCELLED){
            // only update the next orderID accordingly if the current order has been completed / cancelled / undefined
            for (Order o : this.allOrders) {
                if (o.getOrderId() >= this.nextOrderId) {
                    this.nextOrderId = o.getOrderId() + 1;
                }
            }
        }
    }

    // Getters
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
    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }
    public ArrayList<Branch> getAllOpenBranches(){
        return this.allOpenBranches;
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
    public void setAllOrders(ArrayList<Order> allOrders) {
        this.allOrders = allOrders;
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

    // public void addNewStaff(Staff s){
    //     this.allStaffs.add(s);
    // }
    // public void removeStaff(Staff s){
    //     this.allStaffs.remove(s);
    // }

    public void makeNewOrder(){
        this.currentActiveOrder = new Order(this.nextOrderId, this.currentActiveBranch);
        this.nextOrderId++;
    }

}
