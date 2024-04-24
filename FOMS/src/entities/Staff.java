package entities;

import constants.Role;
import services.authenticator.LoginDetail;

/**
 * The Staff class encapsulates the attributes and contains the methods for operations related to a Staff.
 * A Manager will be an instance of a Staff object
 * An Admin will be an instance of a Staff object
 */
public class Staff {
    /** The first name of the staff. e.g. Alvin Aw Yong's first name will be Alvin */
	private String firstName;
    /** The last name of the staff. e.g. Alvin Aw Yong's last name will be Aw (currently no support for double-barrelled surnames) */
	private String lastName;
    /** The loginID of the staff */
    private String loginID;
    /** The role of the staff. (See Role enums) */
    private Role role;
    /** The gender of the staff. T=Female, F=Male (Because men are always wrong) */
	private boolean gender;
    /** The age of a staff */
	private int age;
    /** The login details of the staff */
	private LoginDetail loginDetail;
    /** The branch this staff belongs to */
	private Branch branch;

    /**
     * The constructor for a staff
     * @param firstName
     * @param lastName
     * @param loginID
     * @param role
     * @param gender
     * @param age
     * @param staffPassword default set to "password"
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
    /**
     * The method to print the details of this staff
     */
    public void printStaff(){
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Login ID: " + getLoginID());
        System.out.println("Role: " + getRole());
        System.out.println("Gender: " + (getGender() ? "F" : "M"));
        System.out.println("Age: " + getAge());
        System.out.println("Branch: " + branch.getBranchName());
    }
    /**
     * The setter method to set the staff's first name
     * @param firstName the staff's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * The setter method to set the staff's last name
     * @param lastName the staff's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * The setter method to set the staff's gender
     * @param gender the staff's gender
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }
    /**
     * The setter method to set the staff's age
     * @param age the staff's age
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * The setter method to set the staff's role
     * @param loginID the staff's role
     */
    public void setRole(Role role) {
        this.role = role;
    }
    /**
     * The setter method to set the staff's branch
     * @param loginID the staff's branch
     */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	/**
     * The getter method to get the staff's first name
     * @return the staff's first name
     */
	public String getFirstName() {
		return this.firstName;
	}
    /**
     * The getter method to get the staff's last name
     * @return the staff's last name
     */
	public String getLastName() {
        return this.lastName;
	}
    /**
     * The getter method to get the staff's loginID
     * @return the staff's loginID
     */
    public String getLoginID(){
        return this.loginID;
    }
    /**
     * The getter method to get the staff's role
     * @return the staff's role
     */
    public Role getRole(){
        return this.role;
    }
    /**
     * The getter method to get the staff's gender
     * @return the staff's gender
     */
    public boolean getGender() {
        return this.gender;
    }
    /**
     * The getter method to get the staff's age
     * @return the staff's age
     */
	public int getAge() {
        return this.age;
	}
    /**
     * The getter method to get the staff's login details
     * @return the staff's login details
     */
	public LoginDetail getLoginDetail(){
		return this.loginDetail;
	}
    /**
     * The getter method to get the staff's branch
     * @return the staff's branch
     */
	public Branch getBranch() {
		return this.branch;
	}
    /**
     * Returns a string representation of the object. In this case, it returns
     * a formatted string containing the person's name, login ID, role, gender,
     * and age.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        // Determine the gender string representation
        String genderStr = gender ? "Female" : "Male";

        String branchInfo = (branch != null) ? branch.toString() : "No Branch Assigned"; 
        
        // Format the string with the person's information
        return "Name: " + firstName + " " + lastName + ", Login ID: " + loginID + 
            ", Role: " + role + ", Gender: " + genderStr + ", Age: " + age + ", Branch: " + branchInfo;
    }

}
