package pages;

import java.util.Map;
import java.util.HashMap;
import entities.MenuItem;

import utilities.PathTracker;
import utilities.Session;
import pages.customerPages.AddMenuItemPage;
import pages.customerPages.BrowseCategoriesPage;
import pages.customerPages.BrowseDrinksPage;
import pages.customerPages.BrowseSetMealPage;
import pages.customerPages.BrowseSidesPage;
import pages.customerPages.CustomerPage;
import pages.customerPages.EditOrderPage;
import pages.customerPages.ViewOrderPage;
import pages.customerPages.ViewOrderStatus;
import pages.staffPages.StaffLoginPage;
import pages.staffPages.*;
import pages.staffPages.admin.AdminAccessPage;
import pages.staffPages.admin.AdminManagePaymentPage;
import pages.staffPages.admin.AdminManageBranchPage;
import pages.staffPages.admin.AdminManageStaffPage;
import pages.staffPages.manager.ManagerAccessPage;
import pages.staffPages.manager.ManagerEditMenuItemsPage;
import pages.staffPages.manager.ManagerViewStaffDetailsPage;

/**
 * This class serves to manage the different pages. Call this class to change to the active page required. 
 * This class depends on iPage, which means every page will be dependant on this class.
 * @author Siah Yee Long
 */
public class pageViewer {
    /**
     * Map to store every page in existence
     */
    private static Map<String, iPage> pages = new HashMap<>();
    /**
     * The current active page to be displayed
     */
    private static iPage currentPage;
    /**
     * The current session (to hold the current active Branch / Staff / Order)
     */
    private static Session session;
    private static PathTracker pathTracker;
    /**
     * Initialises this class by loading the pages in existence and linking them to a meaningful name
     * Convention: key = name of page but in String format
     */
    static{
        session = new Session();
        // initialise all views
        pages.put("SelectBranchPage", new SelectBranchPage(session));
        pages.put("MainPage", new MainPage());
        pages.put("CustomerPage", new CustomerPage(session));
        pages.put("ViewOrderPage", new ViewOrderPage(session));
        pages.put("EditOrderPage", new EditOrderPage(session));
        pages.put("StaffLoginPage", new StaffLoginPage(session));
        pages.put("StaffAccessPage", new StaffAccessPage(session));
        pages.put("StaffProcessOrderPage", new StaffProcessOrderPage(session));
        pages.put("StaffViewOrderDetailsPage", new StaffViewOrderDetailsPage(session));
        pages.put("ManagerAccessPage", new ManagerAccessPage(session));
        pages.put("ManagerEditMenuItemsPage", new ManagerEditMenuItemsPage(session));
        pages.put("ManagerViewStaffDetailsPage", new ManagerViewStaffDetailsPage(session));
        pages.put("AdminAccessPage", new AdminAccessPage(session));
        pages.put("AdminManagePaymentPage", new AdminManagePaymentPage(session));
        pages.put("AdminManageBranchPage", new AdminManageBranchPage(session));
        pages.put("AdminManageStaffPage", new AdminManageStaffPage(session));
        pages.put("BrowseCategoriesPage", new BrowseCategoriesPage(session));
        pages.put("BrowseDrinksPage", new BrowseDrinksPage(session));
        pages.put("BrowseSidesPage", new BrowseSidesPage(session));
        pages.put("BrowseSetMealPage", new BrowseSetMealPage(session));
        pages.put("AddMenuItemPage", new AddMenuItemPage(session));
        pages.put("ViewOrderStatus", new ViewOrderStatus(session));
        // add more views here as required
        
        pathTracker = new PathTracker("SelectBranchPage", pages.get("SelectBranchPage"));
    }
    /**
     * This static method switches the current active page
     * @param pageName the next page you want to go to
     */
    public static void changePage(String pageName){
        if("back".equalsIgnoreCase(pageName)){
            currentPage = pathTracker.getPrevPage();
            pathTracker.printCurrentPath();
            currentPage.viewOptions();
        }
        else if("current".equalsIgnoreCase(pageName)){
            pathTracker.printCurrentPath();
            currentPage.viewOptions();
        }
        else if(pages.containsKey(pageName)){
            currentPage = pages.get(pageName);
            pathTracker.goTo(pageName, currentPage);
            pathTracker.printCurrentPath();
            currentPage.viewOptions();
        }
        else{
            System.out.println("Error: View not found!");
        }
    }
    /**
     * This static method handles the input of the current active page
     * Called from mainFOMSApp.java
     * @param choice the choice provided from user's input
     */
    public static void handleInput(String choice){
        if(currentPage != null){
            currentPage.handleInput(choice);
        }
        else
            System.out.println("No active view to handle input!");
    }

    // no, just call changePage(<the page you wanna go to>) within whatever function youre in
    public static void navigateToAddMenuItemPage(MenuItem selectedItem) {
        AddMenuItemPage addMenuItemPage = new AddMenuItemPage(session, selectedItem);
        currentPage = addMenuItemPage;
        currentPage.viewOptions(); // Display the AddMenuItemPage options.
    }
}
