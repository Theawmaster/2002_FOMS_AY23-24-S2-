package utilities;

import java.util.ArrayList;

import entities.Staff;
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
    private ArrayList<MenuItem> allMenuItems;

    private Staff currentActiveStaff;
    private Branch currentActiveBranch;
    private Order currentActiveOrder;

    /**
     * Initialised in pageViewer only. Loads all data from LoadData's child classes to set the context of the current session
     */
    public Session(){
        LoadStaffs initLoadStaffs = new LoadStaffs();
        this.allStaffs = initLoadStaffs.loadDatafromCSV();

        LoadBranches initLoadBranches = new LoadBranches();
        this.allBranches = initLoadBranches.loadDatafromCSV();

        LoadMenuItems initLoadMenuItems = new LoadMenuItems();
        this.allMenuItems = initLoadMenuItems.loadDatafromCSV();
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

    public void setCurrentActiveStaff(Staff s){
        this.currentActiveStaff = s;
    }
    public void setCurrentActiveBranch(Branch b){
        this.currentActiveBranch = b;
    }
    public void setCurrentActiveOrder(Order o){
        this.currentActiveOrder = o;
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


}
