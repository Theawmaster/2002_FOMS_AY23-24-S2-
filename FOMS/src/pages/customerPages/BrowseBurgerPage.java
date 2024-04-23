package pages.customerPages;

import utilities.Session;
import constants.MealCategory;

public class BrowseBurgerPage extends BrowseCategoriesPage{
    public BrowseBurgerPage(Session s){
        super(s);
        this.category = MealCategory.BURGER;
    }
}