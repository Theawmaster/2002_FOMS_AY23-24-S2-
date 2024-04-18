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
    private ArrayList<Branch> allBranches;
    private ArrayList<MenuItem> allMenuItems;
    private ArrayList<iPaymentService> allPaymentServices;

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

    public Staff getCurrentActiveStaff(){
        return this.currentActiveStaff;
    }
    public Branch getCurrentActiveBranch(){
        return this.currentActiveBranch;
    }
    public Order getCurrentActiveOrder(){
        return this.currentActiveOrder;
    }

     /**
     * Counts the number of staff (excluding managers) in a given branch.
     * @param branchName The name of the branch.
     * @return The count of staff in the specified branch.
     */
    public long getStaffCount(String branchName) {
        return allStaffs.stream()
            .filter(staff -> staff.getBranch().getBranchName().equalsIgnoreCase(branchName) && !staff.getRole().equalsIgnoreCase("MANAGER"))
            .count();
    }

    /**
     * Counts the number of managers in a given branch.
     * @param branchName The name of the branch.
     * @return The count of managers in the specified branch.
     */
    public long getManagerCount(String branchName) {
        return allStaffs.stream()
            .filter(staff -> staff.getBranch().getBranchName().equalsIgnoreCase(branchName) && staff.getRole().equalsIgnoreCase("MANAGER"))
            .count();
    }

    /**
     * Retrieves the staff quota for a specific branch.
     * @param branchName The name of the branch.
     * @return The staff quota for the branch or -1 if the branch is not found.
     */
    public int getBranchQuota(String branchName) {
        return allBranches.stream()
            .filter(branch -> branch.getBranchName().equalsIgnoreCase(branchName))
            .findFirst()
            .map(Branch::getbranchQuota)
            .orElse(0);  // Default to 0 if not found
    }

    public int calculateAllowedManagers(long staffCount) {
        return (staffCount < 5) ? 1 : (staffCount < 9) ? 2 : 3;
    }

}
