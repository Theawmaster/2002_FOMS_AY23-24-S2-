package utilities.authenticator;

import entities.Staff;
import java.util.ArrayList;
import java.util.HashMap;

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

    @Override
    public boolean resetPassword(String userID) {
        // Implement the logic to reset a user's password
        throw new UnsupportedOperationException("Reset password not implemented.");
    }

    @Override
    public boolean changePassword(String userID, String oldPassword, String newPassword) {
        // Implement the logic to change a user's password
        throw new UnsupportedOperationException("Change password not implemented.");
    }
}
