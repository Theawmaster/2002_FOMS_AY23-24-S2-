package entities;

import constants.MealCategory;

public class MenuItem {

	private String food;
	private double price;
    private MealCategory category;
    private String customization;
	// private Branch branch; 
    // for branch need to extend 
	

    /**
     * Constructor for Staff
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     */
	public MenuItem(String food, double price, MealCategory category, String customization) {
        this.food = food;
        this.price = price;
        this.category = category;
        this.customization = customization;
	}
    // to do: implement branch 

    public void printMenuItems(){
        System.out.println("Food: " + getFood());
        System.out.println("Price: " + getPrice());
        System.out.println("Category: " + getCategory());
    } // to do: implement getbranch

	protected String getFood() {
		return this.food;
	}

    protected String setFood(String food) {
        return this.food;
    }

	protected double getPrice() {
        return this.price;
	}

    protected double setPrice(double price) {
        return this.price;
    }
    
    protected MealCategory getCategory(){
        return this.category;
    }

    protected MealCategory setCategory(MealCategory category){
        return this.category;
    }

    protected String getCustomization() {
        return customization;
    }

    protected void setCustomization(String customization) {
        this.customization = customization;
    }
    
    // to do: implement getbranch 
}
