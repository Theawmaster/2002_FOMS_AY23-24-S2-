package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

/**
 * This page is responsible for displaying and handling inputs for the set meal category, and inherits from BrowseCategoriesPage
 * @author Siah Yee Long
 * @author Lee Jedidiah
 */
public class BrowseSetMealPage extends BrowseCategoriesPage{
    /**
     * The constructor for the BrowseSetMealPage. It sets the category to MealCategory.SETMEAL
     * @param s the current session
     */
    public BrowseSetMealPage(Session s){
        super(s);
        this.category = MealCategory.SETMEAL;
    }
}