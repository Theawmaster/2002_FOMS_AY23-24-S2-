package entities;

import constants.MealCategory;

/**
 * The MenuItem class encapsulates the attributes and contains the methods for operations related to MenuItem
 * MenuItems are cloneable as there may be more than 1 instance of an item. For example your Cheeseburger with no cheese, and my Cheeseburger with no patty
 */
public class MenuItem implements Cloneable{

    /**
     * The name of the menu item
     */
    private String food;
    /**
     * The price of this item
     */
    private double price;
    /**
     * The category of this item (see MealCategory enums)
     */
    private MealCategory category;
    /**
     * A description for this item that describes what this item is for the customers
     */
    private String description;
    /**
     * A field for the customer to write their special requests (if any)
     */
    private String customization;
    /**
     * The branch that this menu item is available in
     */
    private Branch branch;
    /**
     * The constructor for the MenuItem object
     * @param food The name of the item
     * @param price The price of the item
     * @param branch The branch that this item is available in
     * @param category The category of this item
     * @param description A description for this item
     * @param customization A field for the customer to write their special requests (if any)
     */
    public MenuItem(String food, double price, Branch branch, MealCategory category, String description, String customization) {
        this.food = food; 
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.description = description;
        this.customization = customization;
    }
    /**
     * The method to print our the menu items' details
     */
    public void printMenuItems(){
        System.out.println("Food: " + getFood());
        System.out.println("Price: " + getPrice());
        System.out.println("Branch: "+ branch.getBranchName());
        System.out.println("Category: " + getCategory());
        System.out.println("Description: " + getDescription());
    } 
    /**
     * The getter method to get the item name
     * @return food name
     */
    public String getFood() {
        return food;
    }
    /**
     * The setter method to set the name of this menu item
     * @param food the new name to set
     */
    public void setFood(String food) {
        this.food = food;
    }
    /**
     * The getter method to get the price of the menu item
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }
    /**
     * The setter method to set the price of the menu item
     * @param price the new price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * The getter method to get the branch of the menu item where it is available
     * @return the branch object itself
     */
    public Branch getBranch() {
        return branch;
    }
    /**
     * The getter method to get the category of the menu item
     * @return the MealCategory enum
     */    
    public MealCategory getCategory(){
        return category;
    }
    /**
     * The setter method to set the category of the menu item
     * @param category the new category
     */
    public void setCategory(MealCategory category){
        this.category = category;
    }
    /**
     * The getter method to get the description of the menu item
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }
    /**
     * The setter method to set the description of the menu item
     * @param description the description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * The getter method to get the customisation of the menu item
     * @return the customisation 
     */
    public String getCustomization() {
        return customization; 
    }
    /**
     * The setter method to get the customisation of the menu item
     * @param customization the new customisation string
     */
    public void setCustomization(String customization) {
        this.customization = customization;
    }
    /**
     * The overridden clone method to clone a new object every time a customer wants this item
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

