package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

public class BrowseSidesPage extends BrowseCategoriesPage{
    public BrowseSidesPage(Session s){
        super(s);
        this.category = MealCategory.SIDE;
    }
}