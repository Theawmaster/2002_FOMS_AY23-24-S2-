package test;

import java.util.ArrayList;

import utilities.Logger;

public class exampleCSVmain {
    public static void main(String[] args) {
        exampleCSVrw d = new exampleCSVrw();
        ArrayList<String> r = d.readFromFile();
        for(String s : r){
            Logger.info(s);
        }

        d.writeToFile("HI FROM WOODLANDS");
    }
}
