package utilities.authenticator;

import entities.Staff;
import java.util.ArrayList;
import java.util.HashMap;
import utilities.LoadStaffs;
import constants.FilePaths;

/**
 * Service class for authenticating staff logins and managing their passwords.
 */
public class StaffLoginService implements iLoginService {
    private HashMap<String, String> loadedUsersAndPasswords = new HashMap<>();
    private ArrayList<Staff> staffList;

    /**
     * Constructs a new StaffLoginService instance with the provided list of loaded staff data.
     *
     * @param loadedStaffData The list of loaded staff data.
     */
    public StaffLoginService(ArrayList<Staff> loadedStaffData) {
        this.staffList = new ArrayList<>(loadedStaffData);
        for (Staff s : loadedStaffData) {
            LoginDetail l = s.getLoginDetail();
            this.loadedUsersAndPasswords.put(l.getUserID(), l.getUserPassword());
        }
    }

    /**
     * Authenticates a staff login attempt using the provided username and password.
     *
     * @param username The username entered by the staff.
     * @param password The password entered by the staff.
     * @return true if the login attempt is successful, false otherwise.
     */
    @Override
    public boolean login(String username, String password) {
        String storedPassword = this.loadedUsersAndPasswords.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    /**
     * Retrieves a staff object by their user ID.
     *
     * @param userID The user ID of the staff.
     * @return The staff object with the specified user ID, or null if not found.
     */
    public Staff getStaffByID(String userID) {
        for (Staff staff : staffList) {
            if (staff.getLoginID().equals(userID)) {
                return staff;
            }
        }
        return null;
    }

    /**
     * Facilitates the changing of a staff's password.
     *
     * @param userID The user ID of the staff.
     * @param oldPassword The old password of the staff.
     * @param newPassword The new password to be set.
     * @return true if the password change is successful, false otherwise.
     */
    @Override
    public boolean changePassword(String userID, String oldPassword, String newPassword){
        LoadStaffs.updatePassword(userID, newPassword);
        return this.loadedUsersAndPasswords.replace(newPassword, oldPassword, newPassword);
    }

    /**
     * Facilitates the resetting of a staff's password.
     *
     * @param userID The user ID of the staff.
     * @return true if the password reset is successful, false otherwise.
     */
    @Override
    public boolean resetPassword(String userID){
        return changePassword(userID, this.loadedUsersAndPasswords.get(userID), userID);
    }
}
