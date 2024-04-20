package main;

import pages.pageViewer;
import utilities.UserInputHelper;

/*
This is the main application for the FOMS. Run this to begin the ordering system
 */
public class mainFOMSApp {
    public static void main(String[] args){
        pageViewer.changePage("SelectBranchPage");
        while(true){
            String choice = UserInputHelper.getInput("Select a choice:");
            pageViewer.handleInput(choice);
        }
    }
}
