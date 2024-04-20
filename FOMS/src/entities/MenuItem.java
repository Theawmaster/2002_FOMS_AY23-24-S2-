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

    public MenuItem(MenuItem itemToEdit) {
        //TODO Auto-generated constructor stub
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

    public void setFood(String food) {
        this.food = food;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public void setCategory(MealCategory category){
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }

    public char[] toCSV() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toCSV'");
    }
}

