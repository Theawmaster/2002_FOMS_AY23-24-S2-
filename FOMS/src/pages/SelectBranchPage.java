package pages;

import entities.Branch;
import entities.MenuItem;
import entities.Staff;
import utilities.Logger;
import utilities.Session;

/**
 * This is the SelectBranchPage that the user will see. This will be the first page users get to see as everything from 
 * here onwards requires the program to know which branch we are referring to.
 * This page lists the branches available to choose from.
 * @author Siah Yee Long
 */
public class SelectBranchPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    /**
     * A local variable to count number of branches available to choose from
     */
    private int numOptions;
    /**
     * Initialising this page sets the session provided from pageViewer
     * @param s
     */
    public SelectBranchPage(Session s){
        this.session = s;
    }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        numOptions = 0;
        System.out.println( " __      _           _       ___                      _     \n" +
                            "/ _\\ ___| | ___  ___| |_    / __\\_ __ __ _ _ __   ___| |__  \n" +
                            "\\ \\ / _ \\ |/ _ \\/ __| __|  /__\\// '__/ _` | '_ \\ / __| '_ \\ \n" +
                            "_\\ \\  __/ |  __/ (__| |_  / \\/  \\ | | (_| | | | | (__| | | |\n" +
                            "\\__/\\___|_|\\___|\\___|\\__| \\_____/_|  \\__,_|_| |_|\\___|_| |_|\n" +
                            "                                                             ");
        System.out.println("Select the branch you want to dine at:");
        // Iterate through all branches for users to select. Index starts at 1. Indexing is adjusted in the handleInput method
        for(Branch b : this.session.getAllBranches()){
            numOptions++;
            System.out.println("[" + numOptions + "] " + b.getBranchName());
        }
        System.out.println("[Q] Quit FOMS");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        if(choice.equalsIgnoreCase("q") || choice.equalsIgnoreCase("quit")){
            System.out.println("If you have a complaint I suggest you submit it through our email system. You are done.");
            // Terminate entire programme. Only this class can do this
            System.exit(0);
        }
        try{
            int intChoice = Integer.parseInt(choice) -1; // -1 to offset indexing with user's response

            Branch activeBranch = this.session.getAllBranches().get(intChoice);
            Logger.debug("You chose option: " + (intChoice+1) + " which corresponds to: " + activeBranch.getBranchName());
            // customer is now dining here. all staff and managers will belong to this branch.
            this.session.setCurrentActiveBranch(activeBranch);
            //change view to the main page to start ordering and stuff
            pageViewer.changePage("MainPage");
        }
        catch (NumberFormatException e){
            System.out.println("Please enter only integer values, or 'q' to quit! ");
            Logger.error(e.getMessage());
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Please select a branch within the range only! ");
            Logger.error(e.getMessage());
        }
    }
}
