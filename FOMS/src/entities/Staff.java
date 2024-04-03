package entities;

import constants.Role;
import utilities.authenticator.LoginDetail;

public class Staff {

	private String firstName;
	private String lastName;
    private String loginID;
    private Role role;
	private boolean gender; // True: F, False: Male, because the woman is always right
	private int age;
	private LoginDetail loginDetail;
	private Branch branch; 
	// private Branch branch;
	// private Stats stats;

    /** 
     * Constructor for Staff
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     */
	public Staff(String firstName, String lastName, String loginID, Role role, boolean gender, int age, String staffPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginID = loginID;
        this.role = role;
        this.gender = gender;
        this.age = age;
		this.loginDetail = new LoginDetail(loginID, staffPassword); // sets default login details first. default pw is loginID
	}

    public void printStaff(){
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Login ID: " + getLoginID());
        System.out.println("Role: " + getRole());
        System.out.println("Gender: " + (getGender() ? "F" : "M"));
        System.out.println("Age: " + getAge());
    }

	// Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	// New method to set the Branch of a Staff member
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	// Getters
	protected String getFirstName() {
		return this.firstName;
	}

	protected String getLastName() {
        return this.lastName;
	}

    protected String getLoginID(){
        return this.loginID;
    }
    
    protected Role getRole(){
        return this.role;
    }

    protected boolean getGender() {
        return this.gender;
    }
    
	protected int getAge() {
        return this.age;
	}
	public LoginDetail getLoginDetail(){
		return this.loginDetail;
	}

	// If you also need to get the Branch of a Staff member, add a getter method
	public Branch getBranch() {
		return this.branch;
	}

	// protected void getBranch() {
	// 	return;
	// }


	// protected void getLogInDetails() {
	// 	// TODO - implement Staff.getLogInDetails
	// 	throw new UnsupportedOperationException();
	// }

	// public void processOrders() {
	// 	// TODO - implement Staff.processOrders
	// 	throw new UnsupportedOperationException();
	// }

	// public void viewAllOrders() {
	// 	// TODO - implement Staff.viewAllOrders
	// 	throw new UnsupportedOperationException();
	// }

	// public void viewSpecificOrder() {
	// 	// TODO - implement Staff.viewSpecificOrder
	// 	throw new UnsupportedOperationException();
	// }

}
