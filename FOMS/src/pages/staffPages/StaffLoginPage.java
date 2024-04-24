package pages.staffPages;

import java.util.Random;

import utilities.Session;
import utilities.UserInputHelper;
import constants.Role;
import constants.Settings;
import pages.iPage;
import pages.PageViewer;
import services.authenticator.iLoginService;
import services.authenticator.StaffLoginService;

/**
 * This is the page that the staff will first see
 * This page facilitates the logging in of staff. When the staff has successfully logged in, the session's currentActiveStaff will be updated to that guy
 * Which implies that any manager-only-accessible page or admin-only-accessible pages can simply check the session's active staff to determine privileges
 * @author Siah Yee Long
 */
public class StaffLoginPage implements iPage {
    /**
     * The current active session 
     */
    private Session session;
    /**
     * The LoginService used for this page
     */
    private StaffLoginService staffLoginService;
    /**
     * Initialising this page sets the session provided from PageViewer
     * @param s
     */
    public StaffLoginPage(Session s){
        this.session = s;
    }
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        System.out.println( "                   _____ _         __  __   _             _                       \n" + //
                            "                  / ____| |       / _|/ _| | |           (_)                      \n" + //
                            "  ______ ______  | (___ | |_ __ _| |_| |_  | | ___   __ _ _ _ __    ______ ______ \n" + //
                            " |______|______|  \\___ \\| __/ _` |  _|  _| | |/ _ \\ / _` | | '_ \\  |______|______|\n" + //
                            "                  ____) | || (_| | | | |   | | (_) | (_| | | | | |                \n" + //
                            "                 |_____/ \\__\\__,_|_| |_|   |_|\\___/ \\__, |_|_| |_|                \n" + //
                            "                                                     __/ |                        \n" + //
                            "                                                    |___/                         ");
        System.out.println("[1] Log in");
        System.out.println("[2] Forgot password");
        System.out.println("[3] Change password");
        System.out.println("[B] Go back");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        this.session.updateSession();
        this.staffLoginService = new StaffLoginService(this.session.getAllStaffs());
        switch (choice) {
            case "1":
                boolean loginSuccess = tryLogin(staffLoginService);
                if (loginSuccess) {
                    System.out.println("LOGIN SUCCESS");
                    System.out.println("Welcome, " + this.session.getCurrentActiveStaff().getFirstName());
                    
                    // log in success. go to staff access page
                    PageViewer.changePage("StaffAccessPage");
                } else {
                    System.out.println("Login failed.");
                    PageViewer.changePage("back");
                }
                break;
            case "2":
                if(tryForgotPassword(staffLoginService)) System.out.println("OI STOP FUCKING FORGETTING. RESET SUCCESS ANYWAY");
                else System.out.println("failed reset pw");
                PageViewer.changePage("current");
                break;
            case "3": 
                if(tryChangePassword(staffLoginService)) System.out.println(("CHANGE PASSWORD SUCCESS YAYYY"));
                PageViewer.changePage("current");
                break;
            case "b":
            case "B":
                // goes back to MainPage
                this.session.setCurrentActiveStaff(null);
                PageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
    /**
     * The {@link tryLogin} private method will allow the user a maximum of {@link Settings.PW_MAX_TRIES} tries for logging in
     * @param loginService is the service used to do the login
     * @return true if successful
     */
    private boolean tryLogin(iLoginService loginService){
        String userID, password;

        for(int i=0; i<(int)Settings.PW_MAX_TRIES.getValue(); i++){
            userID = UserInputHelper.getInput("Enter your username:");
            password = UserInputHelper.getPasswordInput("Enter your password:");

            if (loginService.login(userID, password)) {
                this.session.setCurrentActiveStaff(staffLoginService.getStaffByID(userID));
                if (session.getCurrentActiveStaff().getBranch().getBranchName().equals(session.getCurrentActiveBranch().getBranchName()) || session.getCurrentActiveStaff().getRole()==Role.ADMIN) {
                    // if staff / manager is from this branch OR the guy is an admin, just let them pass
                    return true;
                } else {
                    this.session.setCurrentActiveStaff(null);
                    System.out.println("XXXX Imposter ALERT XXXXX Please log in from the correct branch!!!");
                    return false;
                }
            } else {
                this.session.setCurrentActiveStaff(null);
                System.out.println("XXX WRONG. WHAT A FAILURE");
            }
        }
        this.session.setCurrentActiveStaff(null);
        System.out.println("FAILED TOO MANY TIMES. BOO.");
        return false;
    }
    /**
     * The {@link tryForgotPassword} private method resets the user's password by verifying that they are 
     * a human (simulated only) a maximum of {@link Settings.FORGOTPW_MAX_TRIES} times, and resets 
     * the user's password accordingly
     * @param loginService 
     * @return true if successful
     */
    private boolean tryForgotPassword(iLoginService loginService){
        String generatedValue;

        for(int i=0; i<(int)Settings.FORGOTPW_MAX_TRIES.getValue(); i++){
            String userID = UserInputHelper.getInput("Enter your username:");
            generatedValue = generateRandomString();
            if(generatedValue.equals(UserInputHelper.getInput("Verify you're human. Type what you see on the screen: "+generatedValue))){
                return loginService.resetPassword(userID);
            }
            this.session.setCurrentActiveStaff(null);
            System.out.println("WRONG! TRY AGAIN");
        }
        return false;
    }
    /**
     * The {@link tryChangePassword} private method allows the user to change their password with a maximum of {@link Settings.PW_MAX_TRIES} tries
     * @param loginService
     * @return true if successful
     */
    private boolean tryChangePassword(iLoginService loginService){
        String userID="", oldPassword="";
        for(int i=0; i<(int)Settings.PW_MAX_TRIES.getValue(); i++){
            userID = UserInputHelper.getInput("Enter your username:");
            oldPassword = UserInputHelper.getPasswordInput("Enter your old password:");

            if(loginService.login(userID, oldPassword)) break; // login success. break from for loop
            System.out.println("WRONG. THINK HARDER. THINKKKKK");
            if(i+1 == (int)Settings.PW_MAX_TRIES.getValue()) return false; // tried max times and still fail login
        }

        String newPassword = UserInputHelper.getInput("Enter the new password:");
        // check if password meets minimum length criteria
        while (newPassword.length() < (int)Settings.PW_MIN_CHARACTERS.getValue()){
            System.out.println("Password length too short! Passwords must be at least " + Settings.PW_MIN_CHARACTERS.getValue() + " long");
            newPassword = UserInputHelper.getInput("Enter the new password:");
        }

        return loginService.changePassword(userID, oldPassword, newPassword);
    }
    /**
     * Just a helper function to generate a random string for human verification. Simulates Captcha
     * @return an alphanumeric combination string
     */
    private static String generateRandomString() {
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
