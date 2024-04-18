package utilities.authenticator;

public interface iLoginService {
    boolean login(String userID, String password);
    boolean resetPassword(String userID);
    boolean changePassword(String userID, String oldPassword, String newPassword);
}
