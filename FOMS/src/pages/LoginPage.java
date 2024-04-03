package pages;

import java.util.Random;
import java.util.Scanner;
import constants.Settings;
import utilities.authenticator.StaffLoginService;
import utilities.authenticator.iLoginService;

/**
 * This page will be displayed on CLI to facilitate login-related matters
 */
public class LoginPage {
    /**
     * The constructor simply calls the showLoginPage method
     */
    public LoginPage(){
        showLoginPage();
    }
    /**
     * The {@link showLoginPage} method displays the menu and facilitates the options shown on screen
     */
    public void showLoginPage(){
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

        StaffLoginService staffLoginService = new StaffLoginService();

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                if(tryLogin(staffLoginService)) System.out.println("LOGIN SUCCESS YAYYY"); // TODO: do something like go to the next page or something
                break;
            case "2":
                if(tryForgotPassword(staffLoginService)) System.out.println("OI STOP FUCKING FORGETTING. RESET SUCCESS ANYWAY");
                else System.out.println("failed reset pw");
                break;
            case "3": 
                if(tryChangePassword(staffLoginService)) System.out.println(("CHANGE PASSWORD SUCCESS YAYYY")); // TODO: do something like go to the next page or something
                break;
            case "b":
            case "B":
                // TODO: go back to prev page or something
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
    /**
     * The {@link tryLogin} method will allow the user a maximum of {@link Settings.PW_MAX_TRIES} tries for logging in
     * @param loginService is the service used to do the login
     * @return true if successful
     */
    public boolean tryLogin(iLoginService loginService){
        Scanner sc = new Scanner(System.in);
        String userID, password;

        for(int i=0; i<Settings.PW_MAX_TRIES.getValue(); i++){
            System.out.println("Enter your user ID:");
            userID = sc.nextLine().trim();
            System.out.println("Enter your password:");
            password = sc.nextLine().trim();

            if(loginService.login(userID, password)) return true;
            System.out.println("XXX WRONG. WHAT A FAILURE");
        }
        System.out.println("FAILED TOO MANY TIMES. BOO.");
        return false;
    }
    /**
     * The {@link tryForgotPassword} method resets the user's password by verifying that they are 
     * a human (simulated only) a maximum of {@link Settings.FORGOTPW_MAX_TRIES} times, and resets 
     * the user's password accordingly
     * @param loginService 
     * @return true if successful
     */
    public boolean tryForgotPassword(iLoginService loginService){
        String generatedValue;
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<Settings.FORGOTPW_MAX_TRIES.getValue(); i++){
            System.out.println("Enter your username:");
            String userID = sc.nextLine();
            generatedValue = generateRandomString();
            System.out.println("Verify you're human. Type what you see on the screen: "+generatedValue);
            if(generatedValue.equals(sc.nextLine())){
                return loginService.resetPassword(userID);
            }
            System.out.println("WRONG! TRY AGAIN");
        }
        return false;
    }
    /**
     * The {@link tryChangePassword} allows the user to change their password with a maximum of {@link Settings.PW_MAX_TRIES} tries
     * @param loginService
     * @return true if successful
     */
    public boolean tryChangePassword(iLoginService loginService){
        Scanner sc = new Scanner(System.in);
        String userID = "", oldPassword = "";

        for(int i=0; i<Settings.PW_MAX_TRIES.getValue(); i++){
            System.out.println("Enter your user ID:");
            userID = sc.nextLine();
            System.out.println("Enter your old password:");
            oldPassword = sc.nextLine();

            if(loginService.login(userID, oldPassword)) break; // login success. break from for loop
            System.out.println("WRONG. THINK HARDER. THINKKKKK");
            if(i+1 == Settings.PW_MAX_TRIES.getValue()) return false; // tried max times and still fail login
        }

        System.out.println("Enter the new password:");
        String newPassword = sc.nextLine();
        // check if password meets minimum length criteria
        while (newPassword.length() < Settings.PW_MIN_CHARACTERS.getValue()){
            System.out.println("Password length too short! Passwords must be at least " + Settings.PW_MIN_CHARACTERS.getValue() + " long");
            System.out.println("Enter the new password:");
            newPassword = sc.nextLine();
        }

        return loginService.changePassword(userID, oldPassword, newPassword);
    }
    /**
     * Just a helper function to generate a random string for human verification
     * @return an alphanumeric combination string
     */
    public static String generateRandomString() {
        int length = 6; // Length of the alphanumeric string
        StringBuilder sb = new StringBuilder(length);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = characters.charAt(random.nextInt(characters.length()));
            sb.append(c);
        }
        return sb.toString();
    }
}
