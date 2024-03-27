package entities;

import constants.Role;

public class Staffs {

	private String firstName;
	private String lastName;
    private String loginID;
    private Role role;
	private boolean gender; // True: F, False: Male, because the woman is always right
	private int age;
	// private Branch branch;
	// private LogIn logInDetails;
	// private Stats stats;

    /**
     * Constructor for Staff
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     */
	public Staffs(String firstName, String lastName, String loginID, Role role, boolean gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginID = loginID;
        this.role = role;
        this.gender = gender;
        this.age = age;
	}

    public void prinStaff(){
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Login ID: " + getLoginID());
        System.out.println("Role: " + getRole());
        System.out.println("Gender: " + (getGender() ? "F" : "M"));
        System.out.println("Age: " + getAge());
    }

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

	// protected void getBranch() {
	// 	return;
	// }

	/**
	 * 
	 * @param branch
	 */
	// protected void setBranch(Branch branch) {
	// 	this.branch = branch;
	// }

	// protected void getLogInDetails() {
	// 	// TODO - implement Staff.getLogInDetails
	// 	throw new UnsupportedOperationException();
	// }

	/**
	 * 
	 * @param logInDetails
	 */
	// protected void setLogInDetails(int logInDetails) {
	// 	this.logInDetails = logInDetails;
	// }

	// public void logIn() {
	// 	// TODO - implement Staff.logIn
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