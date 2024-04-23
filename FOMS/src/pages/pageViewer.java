package pages;

import java.util.Map;

import constants.FilePaths;

import java.io.IOException;
import java.util.HashMap;

import utilities.PathTracker;
import utilities.PersistenceHandler;
import utilities.Session;
import pages.customerPages.*;
import pages.staffPages.*;
import pages.staffPages.admin.*;
import pages.staffPages.manager.*;

/**
 * This class serves to manage the different pages. Call this class to change to the active page required. 
 * This class depends on iPage, which means every page will be dependant on this class.
 * @author Siah Yee Long
 */
public class PageViewer {
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
        pages.put("MainPage", new MainPage(session));
        pages.put("CustomerPage", new CustomerPage(session));
        pages.put("ViewOrderPage", new ViewOrderPage(session));
        pages.put("EditOrderPage", new archiveEditOrderPage(session));////////
        pages.put("StaffLoginPage", new StaffLoginPage(session));
        pages.put("StaffAccessPage", new StaffAccessPage(session));
        pages.put("StaffProcessOrderPage", new StaffProcessOrderPage(session));
        pages.put("ManagerAccessPage", new ManagerAccessPage(session));
        pages.put("EditMenuItemsPage", new EditMenuItemsPage(session));
        pages.put("ViewStaffDetailsPage", new ViewStaffDetailsPage(session));
        pages.put("AdminAccessPage", new AdminAccessPage(session));
        pages.put("AdminManagePaymentPage", new AdminManagePaymentPage(session));
        pages.put("AdminManageBranchPage", new AdminManageBranchPage(session));
        pages.put("AdminManageStaffPage", new AdminManageStaffPage(session));
        pages.put("BrowseCategoriesPage", new BrowseCategoriesPage());
        pages.put("BrowseDrinksPage", new BrowseDrinksPage(session));
        pages.put("BrowseSidesPage", new BrowseSidesPage(session));
        pages.put("BrowseSetMealPage", new BrowseSetMealPage(session));
        pages.put("AddMenuItemPage", new AddMenuItemPage(session));
        // add more views here as required
        
        pathTracker = new PathTracker("SelectBranchPage", pages.get("SelectBranchPage"), session);
    }
    /**
     * This static method switches the current active page
     * @param pageName the next page you want to go to
     */
    public static void changePage(String pageName){
        if("back".equalsIgnoreCase(pageName)){
            currentPage = pathTracker.getPrevPage();
        }
        else if("current".equalsIgnoreCase(pageName)){
            // no action required
        }
        else if(pages.containsKey(pageName)){
            if(pathTracker.isNotBehind(pageName)){
                currentPage = pages.get(pageName);
                pathTracker.goTo(pageName, currentPage);
            }
            else{
                // if the page requested is a few pages behind the current, navigate there appropriately
                currentPage = pathTracker.getBackTo(pageName);
            }
        }
        else if("init".equalsIgnoreCase(pageName)){
            currentPage = pages.get("SelectBranchPage");
        }
        else{
            System.out.println("Error: View not found!");
        }
        // if data has been modified by another instance of the FOMS app, update it into the session
        if(PersistenceHandler.hasBeenUpdated(FilePaths.dataFolderPath.getPath())){ session.updateSession(); }
        
        pathTracker.printCurrentPath();
        pathTracker.printCurrentUser();
        currentPage.viewOptions();
    }
    /**
     * This static method handles the input of the current active page
     * Called from mainFOMSApp.java
     * @param choice the choice provided from user's input
     */
    public static void handleInput(String choice){
        if(currentPage != null){
            try {
                currentPage.handleInput(choice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("No active view to handle input!");
    }
}
