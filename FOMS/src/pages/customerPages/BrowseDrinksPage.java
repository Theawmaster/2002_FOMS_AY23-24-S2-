package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

public class BrowseDrinksPage extends BrowseCategoriesPage{
    public BrowseDrinksPage(Session s){
        super(s);
        this.category = MealCategory.DRINK;
    }
}