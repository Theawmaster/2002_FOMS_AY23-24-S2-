package utilities.authenticator;

import entities.Staff;
import utilities.LoadStaffs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This service implements the iLoginService, and facilitates the staff login experience
 */
public class StaffLoginService implements iLoginService{
    /**
     * The corresponding username and password is loaded into a HashMap to facilitate searching and comparing of password records
     */
    private HashMap<String, String> loadedUsersAndPasswords = new HashMap<>();

    /**
     * The public constructor loads in the staff data from CSV, and loads the {@link loadedUsersAndPasswords} variable with the username and password pair
     */
    public StaffLoginService(){
        LoadStaffs l = new LoadStaffs();
        ArrayList<Staff> loadedStaffData = l.getLoadedData();
        for(Staff s : loadedStaffData){
            LoginDetail tempLoginDetail = s.getLoginDetail();
            this.loadedUsersAndPasswords.put(tempLoginDetail.getUserID(), tempLoginDetail.getUserPassword());
        }
    }
    /** 
     * The {@link login} method facilitates the comparison of the trying password with the username
     * @param username
     * @param password
     * @return true if password matches record
     * TODO: maybe return the Staff object instead of just a boolean so that the guy logging in can now do something with their access privileges
     */
    public boolean login(String username, String password){
        if(this.loadedUsersAndPasswords.containsKey(username)){
            return password.equals(this.loadedUsersAndPasswords.get(username));
        }
        return false;
    }
    /** 
     * The {@link changePassword} method facilitates the changing of password. It calls of the {@link LoadStaffs.updatePassword} function to update the record in the CSV accordingly 
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return true if successful
     */
    public boolean changePassword(String username, String oldPassword, String newPassword){
        LoadStaffs.updatePassword(username, newPassword);
        return this.loadedUsersAndPasswords.replace(newPassword, oldPassword, newPassword);
    }
    /**
     * The {@link resetPassword} method facilitates the resetting of password. It simply resets the password to the username of the staff
     * @param username 
     * @return true if successful
     */
    public boolean resetPassword(String username){
        //TODO: update this in the staff login details also
        return changePassword(username, this.loadedUsersAndPasswords.get(username), username);
    }
}
