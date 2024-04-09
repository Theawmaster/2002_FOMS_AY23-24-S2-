package entities;

import java.util.List;

import constants.OrderStatus;
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
        System.out.println("Branch: " + branch.getBranchName());
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
	public String getFirstName() {
		return this.firstName;
	}

	protected String getLastName() {
        return this.lastName;
	}

    public String getLoginID(){
        return this.loginID;
    }
    
    public Role getRole(){
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

	/**
     * Facilitates processing of orders by updating their statuses.
     * @param orders The list of orders to process.
     */
    public void processOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("There are no orders to process.");
            return;
        }

		// Display the orders to process
        System.out.println("Processing Orders:");
        for (Order order : orders) {
            // Process each order here, for example, updating its status
            order.setStatus(OrderStatus.READY_TO_PICKUP);
            System.out.println("Order ID: " + order.getOrderId() + " processed. Status updated to: " + order.getStatus());
        }
    }
    
	/**
     * Views all orders in the system.
     * @param orders The list of orders to view.
     */
    public void viewAllOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("There are no orders to view.");
            return;
        }

        // Display all orders
        System.out.println("All Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Type: " + (order.isTakeaway() ? "Takeaway" : "Dine-in"));
            System.out.println("Order Status: " + order.getStatus());
            System.out.println("Total Price: " + order.getTotalPrice());
        }
    }

	/**
	* Displays the details of a specific order identified by the orderId.
	* If the order is found, its details are printed; otherwise, a message indicating 
	* that the order does not exist is printed.
	* 
	* @param orders The list of orders to search for the specific order.
	* @param orderId The ID of the order to be viewed.
	*/
	public void viewSpecificOrder(List<Order> orders, int orderId) {
		// Find the order with the given orderId and print its details
		// If the order is not found, print a message indicating that the order does not exist
		boolean found = false;
		for (Order order : orders) {
			if (order.getOrderId() == orderId) {
				order.printOrderDetails();
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Order with ID " + orderId + " does not exist.");
		}
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
