package utilities.authenticator;

import entities.Staff;
import java.util.ArrayList;
import java.util.HashMap;
import utilities.LoadStaffs;

public class StaffLoginService implements iLoginService {
    private HashMap<String, String> loadedUsersAndPasswords = new HashMap<>();

    public StaffLoginService(ArrayList<Staff> loadedStaffData) {
        for (Staff s : loadedStaffData) {
            LoginDetail l = s.getLoginDetail();
            this.loadedUsersAndPasswords.put(l.getUserID(), l.getUserPassword());
        }
    }

    @Override
    public boolean login(String username, String password) {
        String storedPassword = this.loadedUsersAndPasswords.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    /** 
     * The {@link changePassword} method facilitates the changing of password. It calls of the {@link LoadStaffs.updatePassword} function to update the record in the CSV accordingly 
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return true if successful
     */
    @Override
    public boolean changePassword(String userID, String oldPassword, String newPassword){
        LoadStaffs.updatePassword(userID, newPassword);
        return this.loadedUsersAndPasswords.replace(newPassword, oldPassword, newPassword);
    }

    /**
     * The {@link resetPassword} method facilitates the resetting of password. It simply resets the password to the username of the staff
     * @param username 
     * @return true if successful
     */
    @Override
    public boolean resetPassword(String userID){
        //TODO: update this in the staff login details also
        return changePassword(userID, this.loadedUsersAndPasswords.get(userID), userID);
    }
}

