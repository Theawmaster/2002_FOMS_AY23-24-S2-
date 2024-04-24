package services.authenticator;

/**
 * This class is used for storing the staff's log in details (SRP)
 * @author Siah Yee Long
 */
public class LoginDetail {
    /**
     * The user's log in details
     */
    private String userID; // user will never be able to change their userID once it has been set
    /**
     * The user's password
     */
    private String userPassword;
    /**
     * The last time the user logged in
     */
    private String lastLogin; // records last login using Time 
    /**
     * Boolean to check if the user is still using the default password
     */
    private boolean defaultPassword;
    /**
     * Constructor for log in details. When called, sets the user's corresponding userID and password for the first time
     * @param userID is the user's unique ID
     * @param initialPassword is the user's first password loaded
     */
    public LoginDetail(String userID, String initialPassword){
        this.userID = userID;
        this.userPassword = initialPassword; // assume that initial passwords have no requirement i.e. it does not need to be > 8 characters; since it is prob the default password anyway
        if("password" == this.userPassword) this.defaultPassword = true;
        else this.defaultPassword = false;
        this.lastLogin = "Never";
    }
    /**
     * Protected setter method to set the last login time. Called by a LoginService
     * @param when was the last login set
     */
    protected void setLastLogin(String when){
        this.lastLogin = when;
    }
    /**
     * Protected getter method to get last login time. Called by a LoginService
     * @return
     */
    protected String getLastLogin(){
        return this.lastLogin;
    }
    /**
     * Protected getter method to get userID. Called by a LoginService
     * @return the userID
     */
    protected String getUserID(){
        return this.userID;
    }
    /**
     * Protected getter method to get userPassword. Called by a LoginService
     * @return the userPassword
     */
    protected String getUserPassword(){
        return this.userPassword;
    }
    /**
     * Protected method to change user's password. Called by a LoginService
     * @param newPassword will replace the old password
     */
    protected void changePassword(String newPassword){
        this.userPassword = newPassword;
        if(this.userID == this.userPassword) this.defaultPassword = true;
        else this.defaultPassword = false;
    }
    /**
     * Method to check if the user is still using their default password
     * @return true if is default (userID == password)
     */
    protected boolean isDefaultPassword(){
        return defaultPassword;
    }
}
