package main;
import java.util.Scanner;

import pages.pageViewer;

/*
This is the main application for the FOMS. Run this to begin the ordering system
 */
public class mainFOMSApp {
    public static void main(String[] args){
        pageViewer.changePage("SelectBranchPage");

        Scanner sc = new Scanner(System.in);
        while(true){
            String choice = sc.nextLine();
            pageViewer.handleInput(choice);
        }
    }
}
