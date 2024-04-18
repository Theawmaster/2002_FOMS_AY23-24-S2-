package entities;

/**
 * Branch
 */
public class Branch {
    
    private String branchName;
    private String location;
    // private Manager managers;
    // private Staff staffs;
    private int quota;
    private String status;

    /**
     * Constructor for Branch
     * @param branchName
     * @param location
     * @param quota
     * @param status
     */

     public Branch(String branchname){
        this.branchName = branchname;
        this.location = "";
        this.quota = 0;
        this.status = "";
    }
    
    public Branch(String branchName, String location, int quota, String status){
        this.branchName = branchName;
        this.location = location;
        this.quota = quota;
        this.status = status;
    }

    public void printBranch(){
        System.out.println("Branch Name: " + getBranchName());
        System.out.println("Location: " + getLocation());
        System.out.println("Quota: " + getQuota());
        System.out.println("Status: " + getStatus()); // Added by @Theawmaster for open/close of branch
    }

    public String getBranchName(){
        return this.branchName;
    }

    public String getLocation(){
        return this.location;
    }

    public int getQuota(){
        return this.quota;
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        return branchName + " (" + location + ")";
    }

}
