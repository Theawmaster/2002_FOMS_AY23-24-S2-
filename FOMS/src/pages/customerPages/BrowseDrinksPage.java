package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

/**
 * This page is responsible for displaying and handling inputs for the drinks category, and inherits from BrowseCategoriesPage
 * @author Siah Yee Long
 * @author Lee Jedidiah
 */
public class BrowseDrinksPage extends BrowseCategoriesPage{
    /**
     * The constructor for the BrowseDrinksPage. It sets the category to MealCategory.DRINK
     * @param s the current session
     */
    public BrowseDrinksPage(Session s){
        super(s);
        this.category = MealCategory.DRINK;
    }
}