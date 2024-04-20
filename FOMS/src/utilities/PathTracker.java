package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import constants.Role;
import entities.Branch;
import utilities.Session;

import pages.iPage;

public class PathTracker {

    private Session session;

    /**
     * Sets the session for this PathTracker instance.
     * @param session The new session object containing user and other session data.
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Create a LinkedHashMap to act as a stack for pushing and popping the pages navigated
     */
    private LinkedHashMap<String, iPage> path;
    /**
     * Constructor for this object
     * @param defaultLocation the default location name
     * @param defaultPage the default page
     */
    public PathTracker(String defaultLocation, iPage defaultPage) {
        this.path = new LinkedHashMap<>();
        this.path.put(defaultLocation, defaultPage);
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
    public void goBack() {
        if (path.size() > 1) {
            // pop the last page
            this.path.remove((this.path.lastEntry().getKey()));
        }
    }
    /**
     * @return the previous page to change the view to
     */
    public iPage getPrevPage(){
        this.goBack();
        return this.path.lastEntry().getValue();
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
        while (iterator.hasNext()) {
            lastFiveItems.add(iterator.next());
        }
    
        String strPath = String.join(" > ", lastFiveItems);
        String spaces = String.format("%" + (97 - strPath.length()) + "s", "");
    
        // Display
        System.out.println();
        System.out.println();
        System.out.println("\u250F" + "\u2501".repeat(98) + "\u2513");
        System.out.println("\u2503 " + strPath + spaces + "\u2503");
        System.out.println("\u2517" + "\u2501".repeat(98) + "\u251B");
    }

    public void printCurrentUser() {
    
        if (this.session == null) {
            System.out.println("| --- Customer section --- |");
            return;
        }
    
        if (this.session.getCurrentActiveStaff() == null && this.session.getCurrentActiveBranch() == null) {
            System.out.println("| --- Customer section --- |");
            return;
        }
    
        // Check for user session
        if (this.session.getCurrentActiveStaff() != null) {
            String username = this.session.getCurrentActiveStaff().getLoginID();
            String role = this.session.getCurrentActiveStaff().getRole().name();
            String branch = this.session.getCurrentActiveStaff().getBranch().getBranchName();
            System.out.println("| --- Staff Section, Current User: " + username + ", Role: " + role + ", Branch: " + branch + " --- |");
        } else {
            // For customer branch session
            String customerBranch = this.session.getCurrentActiveBranch().getBranchName();
            System.out.println("| --- Customer Section, Branch: " + customerBranch + " --- |");
        }
    }
}
