package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

/**
 * This page is responsible for displaying and handling inputs for the drinks category, and inherits from BrowseCategoriesPage
 * @author Siah Yee Long
 * @author Jed
 */
public class BrowseDrinksPage extends BrowseCategoriesPage{
    public BrowseDrinksPage(Session s){
        super(s);
        this.category = MealCategory.DRINK;
    }
}