package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

/**
 * This page is responsible for displaying and handling inputs for the burger category, and inherits from BrowseCategoriesPage
 * @author Siah Yee Long
 * @author Lee Jedidiah
 */
public class BrowseBurgerPage extends BrowseCategoriesPage{
    /**
     * The constructor for the BrowseBurgerPage. It sets the category to MealCategory.BURGER
     * @param s the current session
     */
    public BrowseBurgerPage(Session s){
        super(s);
        this.category = MealCategory.BURGER;
    }
}