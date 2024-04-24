package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

/**
 * This page is responsible for displaying and handling inputs for the set meal category, and inherits from BrowseCategoriesPage
 * @author Siah Yee Long
 * @author Jed
 */
public class BrowseSetMealPage extends BrowseCategoriesPage{
    public BrowseSetMealPage(Session s){
        super(s);
        this.category = MealCategory.SETMEAL;
    }
}