package test;


import constants.Role;
import entities.Staff;
import services.ManageStaffService;
import utilities.Session;

public class exampleShowStaff {
    public static void main(String[] args) {
        
        Session s = new Session();
        for(Staff staff : s.getAllStaffs()){
            if(staff.getRole() == Role.ADMIN){
                s.setCurrentActiveStaff(staff);
                break;
            }
        }
        ManageStaffService.hireStaff(s);
        ManageStaffService.displayStaff_filterBranch(s);
        ManageStaffService.displayStaff_sortName(s, false);
        ManageStaffService.displayStaff_filterRole(s);
        ManageStaffService.displayStaff_filterAge(s);
        ManageStaffService.displayStaff_filterGender(s);
    }
}
