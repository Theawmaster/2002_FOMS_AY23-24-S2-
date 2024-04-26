package utilities;

import java.util.ArrayList;

import entities.Staff;
import entities.Branch;
import entities.MenuItem;
import entities.Order;
import constants.OrderStatus;
import services.payments.iPaymentService;
import services.payments.VisaPaymentService;
import services.payments.CashPaymentService;
import services.payments.ApplepayPaymentService;
// import services.payments.PaypalPaymentService;


/**
 * This class acts as a data store and contains all active entities within the runtime of the programme (i.e. the current session)
 * @author Siah Yee Long
 */
public class Session {

    /** The list of staffs in the programme */
    private ArrayList<Staff> allStaffs;
    /** The list of branches in the programme */
    private ArrayList<Branch> allBranches;
    /** The list of branches that are open in the programme */
    private ArrayList<Branch> allOpenBranches;
    /** The list of menu items in the programme */
    private ArrayList<MenuItem> allMenuItems;
    /** The list of orders in the programme */
    private ArrayList<Order> allOrders;
    /** The list of payment services in the programme */
    private ArrayList<iPaymentService> allPaymentServices;
    /** The current active staff in the session */
    private Staff currentActiveStaff;
    /** The current active branch in the session */
    private Branch currentActiveBranch;
    /** The current active order in the session */
    private Order currentActiveOrder;
    /** The current active menu item in the session */
    private MenuItem currentActiveMenuItem;
    /** The next order ID to be assigned to a new order */
    private int nextOrderId;
    /**
     * Constructor for Session
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
        // allPaymentServices.add(new PaypalPaymentService());
        // add more payment services if added
    }
    /**
     * Updates the session with the latest data if there has been a change in the CSV files
     */
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
    /**
     * Returns all staffs in the session
     * @return allStaffs
     */
    public ArrayList<Staff> getAllStaffs(){ return this.allStaffs; }
    /**
     * Returns all branches in the session
     * @return allBranches
     */
    public ArrayList<Branch> getAllBranches(){ return this.allBranches; }
    /**
     * Returns all menu items in the session
     * @return allMenuItems
     */
    public ArrayList<MenuItem> getAllMenuItems(){ return this.allMenuItems; }
    /**
     * Returns all payment services in the session
     * @return allPaymentServices
     */
    public ArrayList<iPaymentService> getAllPaymentServices(){ return this.allPaymentServices; }
    /**
     * Returns all orders in the session
     * @return allOrders
     */
    public ArrayList<Order> getAllOrders() { return allOrders; }
    /**
     * Returns the next order ID to be assigned to a new order
     * @return nextOrderId
     */
    public ArrayList<Branch> getAllOpenBranches(){ return this.allOpenBranches; }
    /**
     * Sets the current active staff in the session
     * @param s The staff to be set as the current active staff
     */
    public void setCurrentActiveStaff(Staff s){ this.currentActiveStaff = s; }
    /**
     * Sets the current active branch in the session
     * @param b The branch to be set as the current active branch
     */
    public void setCurrentActiveBranch(Branch b){ this.currentActiveBranch = b; }
    /**
     * Sets the current active order in the session
     * @param o The order to be set as the current active order
     */
    public void setCurrentActiveOrder(Order o){ this.currentActiveOrder = o; }
    /**
     * Sets the current active menu item in the session
     * @param menuItem The menu item to be set as the current active menu item
     */
    public void setCurrentActiveMenuItem(MenuItem menuItem){ this.currentActiveMenuItem = menuItem; }
    /**
     * Sets the orders in the session
     * @param allOrders The orders to be set in the session
     */
    public void setAllOrders(ArrayList<Order> allOrders) { this.allOrders = allOrders; }
    /**
     * Returns the current active staff in the session
     * @return currentActiveStaff
     */
    public Staff getCurrentActiveStaff(){ return this.currentActiveStaff; }
    /**
     * Returns the current active branch in the session
     * @return currentActiveBranch
     */
    public Branch getCurrentActiveBranch(){ return this.currentActiveBranch; }
    /**
     * Returns the current active order in the session
     * @return currentActiveOrder
     */
    public Order getCurrentActiveOrder(){ return this.currentActiveOrder; }
    /**
     * Returns the current active menu item in the session
     * @return currentActiveMenuItem
     */
    public MenuItem getCurrentActiveMenuItem(){ return this.currentActiveMenuItem; }
    /**
     * Create a new order that exists within the session
     */
    public void makeNewOrder(){
        this.currentActiveOrder = new Order(this.nextOrderId, this.currentActiveBranch);
        this.nextOrderId++;
    }

}
