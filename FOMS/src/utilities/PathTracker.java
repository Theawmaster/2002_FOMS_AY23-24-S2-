package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import pages.iPage;

/**
 * The {@link PathTracker} class is used to keep track of the pages navigated by the user
 * @author Siah Yee Long
 */
public class PathTracker {
    /**
     * The {@link session} variable stores the current session of the user
     */
    private Session session;
    /**
     * Create a LinkedHashMap to act as a stack for pushing and popping the pages navigated
     */
    private LinkedHashMap<String, iPage> path;
    /**
     * Constructor for this object
     * @param defaultLocation the default location name
     * @param defaultPage the default page
     * @param session the current session
     */
    public PathTracker(String defaultLocation, iPage defaultPage, Session session) {
        this.path = new LinkedHashMap<>();
        this.path.put(defaultLocation, defaultPage);
        this.session = session;
    }
    /**
     * Method to call when navigating to a new page
     * @param pageName inserts this page name into the LinkedHashMap
     * @param page the actual page to switch into
     */
    public void goTo(String pageName, iPage page) {
        path.put(pageName, page);
    }
    /**
     * Method to pop out the last page navigated
     */
    private void goBack() {
        if (path.size() > 1) {
            // Get the last key manually
            String lastKey = null;
            for (String key : path.keySet()) {
                lastKey = key;
            }
            if (lastKey != null) {
                path.remove(lastKey);
                return;
            }
        }
    }
    /**
     * Method to get the previous page navigated
     * @return the previous page to change the view to
     */
    public iPage getPrevPage(){
        goBack();
        iPage lastPage = null;
        String lastKey = null;
        for (String key : path.keySet()) {
            lastKey = key;
        }
        if (lastKey != null) {
            lastPage = path.get(lastKey);
        }
        return lastPage;
    }
    /**
     * Method to get the page navigated before the specified page. Method runs recursively if the previous page was not the specified page
     * @param pageName the page name to navigate back to
     * @return the page to change the view to
     */
    public iPage getBackTo(String pageName){
        if(this.path.get(pageName)!=getPrevPage()) return getBackTo(pageName);
        return this.path.get(pageName);
    }
    /**
     * Method to check if the specified page is behind the current page (either directly behind or a few pages behind)
     * @param pageName the page name to check
     * @return true if the page is not behind the current page, false otherwise
     */
    public boolean isNotBehind(String pageName){
        return this.path.get(pageName)==null;
    }
    /**
     * Prints the current path, but if there are more than 5 pages navigated, it will only show the last 5
     */
    public void printCurrentPath() {
        // Assuming 'path' is a LinkedHashMap that maintains the order
        int size = path.size();
        int itemsToSkip = Math.max(0, size - 5);  // Calculate how many items to skip to keep only the last 5
        Iterator<String> iterator = path.keySet().iterator();
    
        // Skip the initial items to get to the last 5
        for (int i = 0; i < itemsToSkip; i++) {
            iterator.next();
        }
    
        // Collect the last 5 items
        List<String> lastFiveItems = new ArrayList<>();
        if(itemsToSkip>0) lastFiveItems.add("...");
        while (iterator.hasNext()) {
            String x = iterator.next();
            x = x.substring(0, x.length() - 4); // ignore the word "Page"
            lastFiveItems.add(x);
        }
        String strPath = String.join(" > ", lastFiveItems);
        String spaces = String.format("%" + (84 - strPath.length()) + "s", "");
    
        // Display
        System.out.println();
        System.out.println();
        System.out.println("\u250F" + "\u2501".repeat(85) + "\u2513");
        System.out.println("\u2503 " + strPath + spaces + "\u2503");
        System.out.println("\u2517" + "\u2501".repeat(85) + "\u251B");
    }
    /**
     * Prints the current user logged in
     */
    public void printCurrentUser() {
       
        if (this.session.getCurrentActiveStaff() == null && this.session.getCurrentActiveBranch() == null) {
            System.out.println(Logger.ANSI_YELLOW+"| --- Welcome to Los Pollos Hermanos --- |"+Logger.ANSI_RESET);
            return;
        }
        // Check for user session
        else if (this.session.getCurrentActiveStaff() != null) {
            String username = this.session.getCurrentActiveStaff().getLoginID();
            String role = this.session.getCurrentActiveStaff().getRole().name();
            String branch = this.session.getCurrentActiveStaff().getBranch().getBranchName();
            System.out.println( Logger.ANSI_CYAN+"| --- Staff Section, Current User: " +
                                Logger.ANSI_YELLOW+ username + Logger.ANSI_CYAN+", Role: " +
                                Logger.ANSI_YELLOW+ role + Logger.ANSI_CYAN+", Branch: " +
                                Logger.ANSI_YELLOW+ branch + Logger.ANSI_CYAN+" --- |"+Logger.ANSI_RESET);
        } else {
            // For customer branch session
            String customerBranch = this.session.getCurrentActiveBranch().getBranchName();
            System.out.println( Logger.ANSI_CYAN+"| --- Customer Section, Branch: " + 
                                Logger.ANSI_YELLOW+customerBranch + Logger.ANSI_CYAN+" --- |"+Logger.ANSI_RESET);
        }
    }
}
