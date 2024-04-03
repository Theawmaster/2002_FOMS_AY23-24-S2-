package utilities.authenticator;

/**
 * This interface is meant for login services to be implemented. Currently only StaffLoginService has been defined that implements this interface
 */
public interface iLoginService {
    /**
     * The implemented class should have a {@link login} method that verifies successful logins with the username and password
     * @param username 
     * @param password
     * @return true if successful
     */
    boolean login(String username, String password);
    /**
     * The implemented class should have a {@link changePassword} method that facilitates the changing of password. The password should update the CSV records correspondingly
     * @param username 
     * @param oldPassword
     * @param newPassword
     * @return true if successful
     */
    boolean changePassword(String username, String oldPassword, String newPassword);
    /**
     * The implemented class should have a {@link resetPassword} method that facilitates the resetting of password. The password should update the CSV records correspondingly
     * @param username
     * @return true if successful
     */
    boolean resetPassword(String username);
}
