package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

public class BrowseSetMealPage extends BrowseCategoriesPage{
    public BrowseSetMealPage(Session s){
        super(s);
        this.category = MealCategory.SETMEAL;
    }
}