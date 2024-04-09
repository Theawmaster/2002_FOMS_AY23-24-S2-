package entities;

public class Branch {
    
    private String branchName;
    private String location;
    // private Manager managers;
    // private Staff staffs;
    private int quota;

    /**
     * Constructor for Branch
     * @param branchName
     * @param location
     * @param quota
     */
    public Branch(String branchName, String location, int quota){
        this.branchName = branchName;
        this.location = location;
        this.quota = quota;
    }

    public void printBranch(){
        System.out.println("Branch Name: " + getBranchName());
        System.out.println("Location: " + getLocation());
        System.out.println("Quota: " + getQuota());
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

    @Override
    public String toString() {
        return branchName + " (" + location + ")";
    }

}
