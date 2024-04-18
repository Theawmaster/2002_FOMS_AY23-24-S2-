package entities;

/**
 * Branch
 */
public class Branch {
    
    private String branchName;
    private String location;
    // private Manager managers;
    // private Staff staffs;
    private int branchQuota;
    private String status;

    /**
     * Constructor for Branch
     * @param branchName
     * @param location
     * @param branchQuota
     * @param status
     */
    public Branch(String branchName, String location, int branchQuota, String status){
        this.branchName = branchName;
        this.location = location;
        this.branchQuota = branchQuota;
        this.status = status;
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

    @Override
    public String toString() {
        return branchName + " (" + location + ")";
    }

}
