package utilities.authenticator;

import entities.Staff;

import java.util.ArrayList;
import java.util.HashMap;

public class StaffLoginService implements iLoginService{
    private HashMap<String, String> loadedUsersAndPasswords;

    public StaffLoginService(ArrayList<Staff> loadedStaffData){
        for(Staff s : loadedStaffData){
            LoginDetail l = s.getLoginDetail();
            this.loadedUsersAndPasswords.put(l.getUserID(), l.getUserPassword());
        }
    }
    public boolean Login(String username, String password){
        if(this.loadedUsersAndPasswords.containsKey(username)){
            return password == this.loadedUsersAndPasswords.get(username);
        }
        return false;
    }
}
