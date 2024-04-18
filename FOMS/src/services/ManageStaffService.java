package services;

import utilities.LoadBranches;
import utilities.LoadStaffs;
import utilities.Logger;
import utilities.Session;
import utilities.UserInputHelper;
import entities.Branch;
import entities.Staff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import constants.Role;

/**
 * This class provides services to manage staff
 * @author Siah Yee Long
 */
public class ManageStaffService {

    private static void displayOutput(ArrayList<Staff> staffsToDisplay){
        System.out.println("First name\tLast name\tRole\t\tGender\tAge\tBranch\t\tID");
        for (Staff s : staffsToDisplay){
            String output = String.format("%-12s\t%-12s\t%-8s\t%-6s\t%-3d\t%-10s\t%-10s",
                            s.getFirstName(),
                            s.getLastName(),
                            s.getRole(),
                            (s.getGender() ? "F" : "M"),
                            s.getAge(),
                            s.getBranch().getBranchName(),
                            s.getLoginID());
            System.out.println(output);
        }  
    }
    /**
     * Method to display all staff
     */
    public static void displayStaff_sortName(Session session, boolean byFirstName) {
        if(session.getCurrentActiveStaff().getRole() == Role.ADMIN){
            // current staff is admin, show everyone
            //sort by name first
            Collections.sort(session.getAllStaffs(), new Comparator<Staff>() {
                @Override
                public int compare(Staff s1, Staff s2){
                    if(byFirstName)
                        return s1.getFirstName().compareTo(s2.getFirstName());
                    else
                        return s1.getLastName().compareTo(s2.getLastName());
                }   
            });
            //display output formatted nicely
            displayOutput(session.getAllStaffs());
        }
        else{
            // if current staff is only a manager, show those within the branch only
            ArrayList<Staff> managersStaff = filterBranch(session, session.getCurrentActiveStaff().getBranch());
            //sort by name first
            Collections.sort(managersStaff, new Comparator<Staff>() {
                @Override
                public int compare(Staff s1, Staff s2){
                    if(byFirstName)
                        return s1.getFirstName().compareTo(s2.getFirstName());
                    else
                        return s1.getLastName().compareTo(s2.getLastName());
                }   
            });
            //display output formatted nicely
            displayOutput(managersStaff);
        }
    }
    public static void displayStaff_sortAge(Session session){
        ArrayList<Staff> filteredStaff = session.getAllStaffs();
        //sort staff by age
        if(session.getCurrentActiveStaff().getRole() == Role.MANAGER){
            filteredStaff = filterBranch(session, session.getCurrentActiveStaff().getBranch());
        }

        Collections.sort(filteredStaff, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2){
                    return Integer.compare(s1.getAge(), s2.getAge());
            }   
        });
        //display output formatted nicely
        displayOutput(filteredStaff);
        return;
    }
    public static void displayStaff_filterAge(Session session){
        // ask admin to select age range
        ArrayList<Staff> filteredStaff = new ArrayList<>();
        System.out.println("What is the minimum age you need:");
        int minAge = UserInputHelper.chooseAge();
        System.out.println("What is the maximum age you need:");
        int maxAge = UserInputHelper.chooseAge();
        
        for(Staff s : session.getAllStaffs()){
            // display staff only within age range
            if(minAge < s.getAge() && s.getAge() <= maxAge){
                filteredStaff.add(s);
            }
        }
        displayOutput(filteredStaff);
        return;
    }
    public static void displayStaff_filterGender(Session session){
        //ask admin to select gender
        ArrayList<Staff> filteredStaff = new ArrayList<>();
        System.out.println("Select a gender you want to filter by:");
        System.out.println("F. Female");
        System.out.println("M. Male");
        String choice = UserInputHelper.chooseGender();
        for(Staff s : session.getAllStaffs()){
            //display staff only of the gender
            if(choice.equals("F") && s.getGender()){
                filteredStaff.add(s);
            }
            if(choice.equals("M") && !s.getGender()){
                filteredStaff.add(s);
            }
        }
        displayOutput(filteredStaff);
        return;
    }
    public static void displayStaff_filterRole(Session session){
        //ask admin to select role
        ArrayList<Staff> filteredStaff = new ArrayList<>();
        System.out.println("Select a role you want to filter by:");
        System.out.println("S. Staff");
        System.out.println("M. Manager");
        System.out.println("A. Admin");
        String choice = UserInputHelper.chooseRole();
        for(Staff s : session.getAllStaffs()){
            //display staff only of the role
            if(choice.equals("S") && s.getRole() == Role.STAFF){
                filteredStaff.add(s);
            }
            else if(choice.equals("M") && s.getRole() == Role.MANAGER){
                filteredStaff.add(s);
            }
            else if(choice.equals("A") && s.getRole() == Role.ADMIN){
                filteredStaff.add(s);
            }
        }
        displayOutput(filteredStaff);
        return;
    }
    public static void displayStaff_filterBranch(Session session){
        //ask admin to select branch
        System.out.println("Select the branch you want to filter by: ");
        Branch chosenBranch = UserInputHelper.chooseBranch(session.getAllBranches());
        ArrayList<Staff> filteredStaff = filterBranch(session, chosenBranch);
        displayOutput(filteredStaff);
        return;
    }
    private static ArrayList<Staff> filterBranch(Session session, Branch whichBranch){
        ArrayList<Staff> ret = new ArrayList<>();
        for(Staff s : session.getAllStaffs()){
            if(s.getBranch().getBranchName().equals(whichBranch.getBranchName()))
                ret.add(s);
        }
        return ret;
    }
    public static void fireStaff(Session session){
        //ask admin to type out staffID (cause you dw the admin anyhow fire people, if it was be i would be very sad :( )
        String badStaff = UserInputHelper.getInput("Enter the staff you want to fire: ");
        for(Staff s : session.getAllStaffs()){
            if(s.getLoginID().equalsIgnoreCase(badStaff)){
                if(s.getRole()==Role.MANAGER && s.getBranch().getManagerCount()-1 < s.getBranch().getMinAllowedManagers()){
                    // if this guy is a manager and removing him would make the branch fall short of managers, disallow firing
                    System.out.println("Can't fire manager! Does not fit requirements. Fire a staff first hehe...");
                    return;
                }
                else{
                    if(LoadStaffs.updateFiredStaff(s)){
                        s.setRole(Role.MANAGER);
                        System.out.println("Fired "+s.getFirstName()+" successfully. Sucks to be them!");
                        return;
                    }
                    else{
                        System.out.println("Failed to fire "+s.getFirstName()+". PHEW...");
                        return;
                    }
                }
            }
        }

    }
    public static void hireStaff(Session session){
        //Gather necessary input for new staff member
        String fname = UserInputHelper.getInput("Enter new staff's first name: ");
        String lname = UserInputHelper.getInput("Enter new staff's last name: ");
        String id = UserInputHelper.getInput("Enter Staff ID: ");
        String roleStr = UserInputHelper.chooseRole();
        Role role = Role.UNDEFINED;
        switch (roleStr) {
            case "S": role = Role.STAFF; break;
            case "M": role = Role.MANAGER; break;
            case "A": role = Role.ADMIN; break;
        }
        String gender = UserInputHelper.chooseGender();
        boolean isFemale = "F".equals(gender);
        int age = UserInputHelper.chooseAge();
        Branch branch = UserInputHelper.chooseBranch(session.getAllBranches()); // Assumes branches are fetched from session
    
        // check to see the current allowed counts
        Logger.debug("Branch name:" + branch.getBranchName());
        Logger.debug("Current Managers: " + branch.getManagerCount());
        Logger.debug("Minimum Allowed Managers: " + branch.getMinAllowedManagers());
        Logger.debug("Current Staff: " + branch.getStaffCount());
        Logger.debug("Staff Quota: " + branch.getbranchQuota());

    
        // Check manager quota constraints
        if (role == Role.MANAGER && branch.getManagerCount() >= branch.getMinAllowedManagers()) {
            System.out.println("XXXX Sian.... Cannot add more managers as there are too many in this branch! XXXX");
            return;
        }
    
        // Check staff quota constraints
        if (role == Role.STAFF && branch.getStaffCount() >= branch.getbranchQuota()) {
            System.out.println("XXXX Sian.... Cannot add more staff to this branch as the branch's quota has already been met! XXXX");
            return;
        }

        Staff newStaff = new Staff(fname, lname, id, role, isFemale, age, gender);
        newStaff.setBranch(branch);
    
        // new staff is now in the session
        if(LoadStaffs.addStaffToCSV(newStaff)){
            session.getAllStaffs().add(newStaff);
            System.out.println("Added "+newStaff.getFirstName()+" successfully. Welcome aboard!");
        }
        else{
            System.out.println("Failed to add "+newStaff.getFirstName()+". GET THE FUCK OUT");
        }
    }
    public static void promoteStaff(Session session){
        //ask admin to type out staffID (you dw the admin to anyhow promote ppl bc no nepotism in singapore! no corruption!)
        String goodStaff = UserInputHelper.getInput("Enter the staff ID to promote");
        for(Staff s : session.getAllStaffs()){
            if(s.getLoginID().equalsIgnoreCase(goodStaff) && s.getRole()==Role.STAFF){
                // if(s.getBranch().getManagerCount() >= s.getBranch().getMinAllowedManagers()){
                //     System.out.println("XXXX Sian.... Cannot add more managers as there are too many in this branch! XXXX");
                //     return;
                // }
                if(s.getRole() != Role.STAFF) System.out.println("oi what you want sia, promote you to president lah happy?");
                else{
                    if(LoadStaffs.updatePromotedStaff(s)){
                        s.setRole(Role.MANAGER);
                        System.out.println("Promoted "+s.getFirstName()+" successfully. DINNER IS ON THEM!");
                        return;
                    }
                    else{
                        System.out.println("Failed to promote "+s.getFirstName()+". Too bad, sucks to be you i guess");
                        return;
                    }
                }
            }
        }
    }
    public static void transferStaff(Session session){
        //ask admin to type out staffID (you dw the admin to anyhow promote ppl bc no nepotism in singapore! no corruption!)
        String trfStaff = UserInputHelper.getInput("Enter the staff ID to transfer");
        Branch trfBranch = UserInputHelper.chooseBranch(session.getAllBranches());
        for(Staff s : session.getAllStaffs()){
            if(s.getLoginID().equalsIgnoreCase(trfStaff)){
                if(s.getRole()==Role.MANAGER && s.getBranch().getManagerCount()-1 < s.getBranch().getMinAllowedManagers()){
                    // if this guy is a manager and transferring him would make the original branch fall short of managers, disallow transfer
                    System.out.println("Cannot transfer this manager! Otherwise branch will be short of managers!");
                    return;
                }
                else if(s.getRole() == Role.STAFF && trfBranch.getStaffCount() >= trfBranch.getbranchQuota()){
                    System.out.println("XXXX Sian.... Cannot add more staff as there are too many in this branch! XXXX");
                    return;
                }
                else{
                    if(LoadStaffs.updateTransferredStaff(s, trfBranch)){
                        s.setBranch(trfBranch);
                        System.out.println("Transfered "+s.getFirstName()+" successfully. Have fun there!");
                    }
                    else{
                        System.out.println("Failed to transfer "+s.getFirstName()+". Too bad, sucks to be you i guess");
                    }
                }
            }
        }
    }
}
