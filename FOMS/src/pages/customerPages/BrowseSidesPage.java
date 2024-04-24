package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

/**
 * This page is responsible for displaying and handling inputs for the sides category, and inherits from BrowseCategoriesPage
 * @author Siah Yee Long
 * @author Jed
 */
public class BrowseSidesPage extends BrowseCategoriesPage{
    public BrowseSidesPage(Session s){
        super(s);
        this.category = MealCategory.SIDE;
    }
}