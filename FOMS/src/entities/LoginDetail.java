package entities;

import java.util.HashMap;

import constants.Settings;
import utilities.LoadData;
import utilities.Time;

/**
 * This class is used for storing the staff's log in details
 */
public class LoginDetail {
    private Staff user;
    private String password;
    private String lastLogin;
    private boolean isDefaultPassword;
    private int failedAttempts;

    /**
     * Constructor for log in details. When called, sets the user's corresponding password for the first time
     * @param user
     * @param password
     */
    public LoginDetail(Staff user, String initialPassword){
        this.user = user;
        this.password = initialPassword; // assume that initial passwords have no requirement i.e. it does not need to be > 8 characters; since it is prob the default password anyway
        if(user.getLoginID() == password) this.isDefaultPassword = true;
        else this.isDefaultPassword = false;
        this.failedAttempts = 0;
    }
    public boolean isDefaultPassword(){
        return isDefaultPassword;
    }
    /**
     * Method to verify login details
     * @param staffID checks if the caller knows what's this guy's staffID
     * @param tryPassword checks if caller knows what's this guy's password
     * @return false if wrong, true if correct
     */
    public boolean verifyLogin(String staffID, String tryPassword){
        if (staffID == this.user.getLoginID() && tryPassword == this.password){
            this.failedAttempts = 0;
            this.lastLogin = Time.getCurrentDateTimeAsString();
            return true;
        }
        else {
            this.failedAttempts++;
            return false;
        }
    }
    /**
     * Method to change password according to requirement (currently set to > 8 characters)
     * @param staffID used to verify login
     * @param tryOldPassword used to verify login
     * @param newPassword to be changed
     * @return false if unsuccessful, true if successful 
     */
    public boolean changePassword(String staffID, String tryOldPassword, String newPassword){
        if(!verifyLogin(staffID, tryOldPassword)) return false;
        if(newPassword.length() > Settings.PW_MIN_CHARACTERS.getValue()){ // currently the requirement for a password set is > 8 characters. feel free to change
            this.password = newPassword;
            this.isDefaultPassword = false;
            return true;
        }
        return false;
    }
}
