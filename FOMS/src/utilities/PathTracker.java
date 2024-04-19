package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


import pages.iPage;

public class PathTracker {
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
    private void goBack() {
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
}
