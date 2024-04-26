package entities;

import utilities.exceptionHandlers.ExceededBranchQuotaException;
import utilities.exceptionHandlers.NotEnoughManagersException;
import utilities.exceptionHandlers.StaffManagementException;

/**
 * The Branch class encapsulates the attributes and contains the methods for operations related to Branches
 */
public class Branch {
    /**
     * The branch name (Usually an all-uppercase short string)
     */
    private String branchName;
    /**
     * The location of the branch (Usually a Title-case string)
     */
    private String location;
    /**
     * The maximum number of staff this branch can have 
     */
    private int branchQuota;
    /**
     * Whether this branch is currently Open or Close
     */
    private String status;
    /**
     * The number of staff currently in this branch
     */
    private int staffCount;
    /**
     * The number of managers currently in this branch. In this case, Manager != Staff
     */
    private int managerCount;
    /**
     * The minimum number of managers allowed according to the number of staff currently in this branch.
     */
    private int minAllowedManagers;

    /**
     * The constructor for the Branch object
     * @param branchName The branch name
     * @param location The location of the branch
     * @param branchQuota The maximum number of staff this branch can have
     * @param status Whether this branch is currently Open or Close
     */
    public Branch(String branchName, String location, int branchQuota, String status){
        this.branchName = branchName;
        this.location = location;
        this.branchQuota = branchQuota;
        this.status = status;

        this.staffCount = 0;
        this.managerCount = 0;
        this.minAllowedManagers = 1;
    }
    /**
     * The method to print out the details of the branch
     */
    public void printBranch(){
        System.out.println("Branch Name: " + getBranchName());
        System.out.println("Location: " + getLocation());
        System.out.println("Quota: " + getbranchQuota());
        System.out.println("Status: " + getStatus());
    }
    /**
     * The getter method to get the branch name
     * @return branch name
     */
    public String getBranchName(){
        return this.branchName;
    }
    /**
     * The getter method to get the location of the branch
     * @return location
     */
    public String getLocation(){
        return this.location;
    }
    /**
     * The getter method to get the quota of the branch
     * @return maximum number of staff this branch can have
     */
    public int getbranchQuota(){
        return this.branchQuota;
    }
    /**
     * The getter method to get the status of the branch
     * @return Open / Close
     */
    public String getStatus(){
        return this.status;
    }
    /**
     * The getter method to get the branch's staff count
     * @return the number of staff in this branch
     */
    public int getStaffCount(){ return this.staffCount; }
    /**
     * The getter method to get the branch's manager count
     * @return the number of managers in this branch
     */
    public int getManagerCount(){ return this.managerCount; }
    /**
     * The getter method to get the minimum number of managers allowed in this branch
     * @return the minimum number of managers allowed
     */
    public int getMinAllowedManagers(){ return this.minAllowedManagers; }
    /**
     * The setter method to set status of the branch
     * @param status Open / Close
     */
    public void setStatus(String status){
        this.status = status;
    }
    /**
     * The overridden toString() class that prints the branch name with location
     * e.g. NTU(North spine Plaza)
     */
    @Override
    public String toString() {
        return branchName + " (" + location + ")";
    }
    /**
     * The private method to calculate the minimum number of managers possible for the branch given the number of staff in this branch
     * According to the rules given in the task sheet: 
     * 1-4 staffs = 1 manager
     * 5-8 staffs = 2 managers
     * 9-15 staffs = 3 managers
     * @return the min number of managers allowed
     */
    private int calculateMinManagers() {
        return (this.staffCount < 5) ? 1 : (this.staffCount < 9) ? 2 : 3;
    }
    /**
     * The method to increment the number of staff in this branch. The method also updates the minimum allowed managers after every increment
     * @throws ExceededBranchQuotaException if there are too many staff in this branch
     */
    public void incrementStaffCount() throws ExceededBranchQuotaException{
        if(this.staffCount+this.managerCount+1 > this.branchQuota)
            throw new ExceededBranchQuotaException("This branch has exceeded its staff quota!");
        this.staffCount++;
        this.minAllowedManagers = calculateMinManagers();
    }
    /**
     * The method to increment the number of managers in this branch. Assume there is no maximum number of staff
     * @throws ExceededBranchQuotaException if there are too many staff in this branch
     */
    public void incrementManagerCount() throws ExceededBranchQuotaException{
        if(this.staffCount+this.managerCount+1 > this.branchQuota)
            throw new ExceededBranchQuotaException("This branch has exceeded its staff quota!");
        this.managerCount++;
    }
    /**
     * The method to decrement the number of staff in this branch. The method also updates the minimum allowed managers after every decrement
     * @throws StaffManagementException if the staff count is negative
     */
    public void decrementStaffCount() throws StaffManagementException{
        if(this.staffCount-1 < 0)
            throw new StaffManagementException("Staff count cannot be negative!");
        this.staffCount--;
        this.minAllowedManagers = calculateMinManagers();
    }
    /**
     * The method to decrement the number of managers in this branch. Assume there is no minimum number of managers
     * @throws StaffManagementException if the manager count is negative
     * @throws NotEnoughManagersException if the branch does not have enough managers
     */
    public void decrementManagerCount() throws StaffManagementException{
        if(this.managerCount-1 < this.minAllowedManagers)
            throw new NotEnoughManagersException("This branch does not have enough managers!");
        if(this.managerCount-1 <= 0)
            throw new StaffManagementException("Manager count cannot be negative!");
        else this.managerCount--;
    }
}
