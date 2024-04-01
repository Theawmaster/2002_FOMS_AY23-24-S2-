package pages;

import java.util.Scanner;
import constants.Settings;
import entities.Staff;

/**
 * This page will be displayed on CLI to facilitate login-related matters
 */
public class LoginPage {
    public LoginPage(){}
    public static void showLoginPage(){
        System.out.println( "                   _____ _         __  __   _             _                       \n" + //
                            "                  / ____| |       / _|/ _| | |           (_)                      \n" + //
                            "  ______ ______  | (___ | |_ __ _| |_| |_  | | ___   __ _ _ _ __    ______ ______ \n" + //
                            " |______|______|  \\___ \\| __/ _` |  _|  _| | |/ _ \\ / _` | | '_ \\  |______|______|\n" + //
                            "                  ____) | || (_| | | | |   | | (_) | (_| | | | | |                \n" + //
                            "                 |_____/ \\__\\__,_|_| |_|   |_|\\___/ \\__, |_|_| |_|                \n" + //
                            "                                                     __/ |                        \n" + //
                            "                                                    |___/                         ");
        System.out.println("[1]: Log in");
        System.out.println("[2]: Forgot password");
        System.out.println("[3]: Change password");
        System.out.println("[b]: Go back");
    }
    public boolean tryLogin(Staff staff){
        Scanner sc = new Scanner(System.in);
        String userID, password;

        for(int i=0; i<Settings.PW_MAX_TRIES.getValue(); i++){
            System.out.println("Enter your user ID:");
            userID = sc.nextLine();
            System.out.println("Enter your password:");
            password = sc.nextLine();


        }
        return false;
    }
}
