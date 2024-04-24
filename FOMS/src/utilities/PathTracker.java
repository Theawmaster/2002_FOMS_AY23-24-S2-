package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import pages.iPage;

public class PathTracker {

    private Session session;

    /**
     * Create a LinkedHashMap to act as a stack for pushing and popping the pages navigated
     */
    private LinkedHashMap<String, iPage> path;
    /**
     * Constructor for this object
     * @param defaultLocation the default location name
     * @param defaultPage the default page
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
    public iPage getBackTo(String pageName){
        if(this.path.get(pageName)!=getPrevPage()) return getBackTo(pageName);
        return this.path.get(pageName);
    }

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
            x = x.substring(0, x.length() - 4);
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
