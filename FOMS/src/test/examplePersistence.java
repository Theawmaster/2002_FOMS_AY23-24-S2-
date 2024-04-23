package test;

import java.util.Scanner;

import utilities.PersistenceHandler;

public class examplePersistence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println(PersistenceHandler.hasBeenUpdated("FOMS/src/utilities/data"));
            sc.nextLine();
            sc.close();
        }
    }
}
