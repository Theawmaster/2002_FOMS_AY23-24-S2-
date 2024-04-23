package test;

import constants.Role;
import entities.Staff;
import services.ProcessOrderService;
import utilities.Session;

public class exampleProcessOrder {
    public static void main(String[] args) {
        
        Session s = new Session();
        for(Staff staff : s.getAllStaffs()){
            if(staff.getRole() == Role.ADMIN){
                s.setCurrentActiveStaff(staff);
                s.setCurrentActiveBranch(staff.getBranch());
                break;
            }
        }
        s.updateSession();
        ProcessOrderService.processOrder(s);


    }
}
