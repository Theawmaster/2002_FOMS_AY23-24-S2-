package main;

import pages.PageViewer;
import utilities.UserInputHelper;

/*
This is the main application for the FOMS. Run this to begin the ordering system
 */
public class mainFOMSApp {
    public static void main(String[] args){      
        PageViewer.changePage("init");
        while(true){
            String choice = UserInputHelper.getInput("Select a choice:");
            PageViewer.handleInput(choice);
        }
    }
}
