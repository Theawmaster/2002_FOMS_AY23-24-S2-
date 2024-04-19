package entities;

import constants.MealCategory;

public class MenuItem {

    private String food;
    private double price;
    private MealCategory category;
    private String description;
    private String customization;
    private Branch branch;
       // private Branch branch; 
       // for branch need to extend 
        
    public MenuItem(String food, double price, Branch branch, MealCategory category, String description, String customization) {
        this.food = food;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.description = description;
        this.customization = customization;
    }

    public void printMenuItems(){
        System.out.println("Food: " + getFood());
        System.out.println("Price: " + getPrice());
        System.out.println("Branch: "+ branch.getBranchName());
        System.out.println("Category: " + getCategory());
        System.out.println("Description: " + getDescription());
    } 

    public String getFood() {
        return food;
    }

    protected void setFood(String food) {
        this.food = food;
    }

    public double getPrice() {
        return price;
    }

    protected void setPrice(double price) {
        this.price = price;
    }

    public Branch getBranch() {
        return branch;
    }

    protected void setBranch(Branch branch) {
        this.branch = branch;
    }
        
    public MealCategory getCategory(){
        return category;
    }

    protected void setCategory(MealCategory category){
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }
}

