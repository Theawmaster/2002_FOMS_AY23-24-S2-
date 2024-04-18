package entities;

/**
 * Branch
 */
public class Branch {
    
    private String branchName;
    private String location;
    private int branchQuota;
    private String status;

    private int staffCount;
    private int managerCount;
    private int minAllowedManagers;

    /**
     * Constructor for Branch
     * @param branchName
     * @param location
     * @param branchQuota
     * @param status
     */

    // we dont need this
    // public Branch(String s){
    //     this.branchName = s;
    //     this.branchQuota = getbranchQuota();
    //     this.location = getLocation();
    //     this.status = getStatus();
    // }
    public Branch(String branchName, String location, int branchQuota, String status){
        this.branchName = branchName;
        this.location = location;
        this.branchQuota = branchQuota;
        this.status = status;

        this.staffCount = 0;
        this.managerCount = 0;
        this.minAllowedManagers = 1;
    }

    public void printBranch(){
        System.out.println("Branch Name: " + getBranchName());
        System.out.println("Location: " + getLocation());
        System.out.println("Quota: " + getbranchQuota());
        System.out.println("Status: " + getStatus()); // Added by @Theawmaster for open/close of branch
    }

    public String getBranchName(){
        return this.branchName;
    }

    public String getLocation(){
        return this.location;
    }

    public int getbranchQuota(){
        return this.branchQuota;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return branchName + " (" + location + ")";
    }

    public int calculateMinManagers() {
        return (this.staffCount < 5) ? 1 : (this.staffCount < 9) ? 2 : 3;
    }

    public void incrementStaffCount(){
        this.staffCount++;
        this.minAllowedManagers = calculateMinManagers();
    }
    public void incrementManagerCount(){
        this.managerCount++;
    }

    public int getStaffCount(){ return this.staffCount; }
    public int getManagerCount(){ return this.managerCount; }
    public int getMinAllowedManagers(){ return this.minAllowedManagers; }
}
