package main;

import pages.pageViewer;
import services.ProcessOrderService;
import utilities.Session;
import utilities.UserInputHelper;

/*
This is the main application for the FOMS. Run this to begin the ordering system
 */
public class mainFOMSApp {
    public static void main(String[] args){
        Session session = new Session(); 
        ProcessOrderService processOrderService = new ProcessOrderService(session);
        // Start the order status updater
        ProcessOrderService.startOrderStatusUpdater();
        
        pageViewer.changePage("SelectBranchPage");
        while(true){
            String choice = UserInputHelper.getInput("Select a choice:");
            pageViewer.handleInput(choice);
        }
    }
}
