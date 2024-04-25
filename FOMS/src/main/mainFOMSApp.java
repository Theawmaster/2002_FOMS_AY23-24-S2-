package main;

import pages.PageViewer;
import utilities.UserInputHelper;

/**
 * The main class for the FOMS application
 * @author Lee Jedidiah
 * @author Koh Huei Shan, Winnie
 * @author Chan Zi Hao
 * @author Alvin Aw Yong
 * @author Siah Yee Long
 */
public class mainFOMSApp {
    /**
     * The main method for the FOMS application
     * @param args the arguments for the main method
     */
    public static void main(String[] args){      
        PageViewer.changePage("init");
        while(true){
            String choice = UserInputHelper.getInput("Select a choice:");
            PageViewer.handleInput(choice);
        }
    }
    /**
     * The constructor for the mainFOMSApp class. It is private to prevent instantiation of the class
     */
    private mainFOMSApp(){}
}
