package test;

import java.util.ArrayList;

import utilities.Logger;

public class testData {
    public static void main(String[] args) {
        Data d = new Data();
        ArrayList<String> r = d.readFromFile();
        for(String s : r){
            Logger.info(s);
        }

        d.writeToFile("HI FROM WOODLANDS");
    }
}
