package pages.staffPages.manager;

import constants.Role;
import pages.iPage;
import pages.pageViewer;
import services.ManageStaffService;
import utilities.Session;

public class ManagerViewStaffDetailsPage implements iPage{
    /**
     * The current active session 
     */
    private Session session;
    public ManagerViewStaffDetailsPage(Session s){
        this.session = s;
    }
    /**
     * Method to view staff details options
     */
    public void viewOptions(){
        System.out.println("Displaying " + this.session.getCurrentActiveStaff().getFirstName() + "'s staff from " + this.session.getCurrentActiveStaff().getBranch().getBranchName() + ":"); // e.g. Displaying Justin's staff from JP:
        System.out.println("[1] Sort my staff by first name"); // this will be the default view
        System.out.println("[2] Sort my staff by last name");
        System.out.println("[3] Sort my staff by age");
        if(this.session.getCurrentActiveStaff().getRole() == Role.ADMIN){
            // if current staff is an admin, allow them to filter staff by age, gender, role and branch
            System.out.println("-- Admin privileges -- ");
            System.out.println("[4] Filter all staff by age range");
            System.out.println("[5] Filter all staff by gender");
            System.out.println("[6] Filter all staff by role");
            System.out.println("[7] Filter all staff by branch");
        }
        System.out.println("[B] Return to Manager Access Page");
    }
    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                ManageStaffService.displayStaff_sortName(this.session, true);
                pageViewer.changePage("current");
                break;
            case "2":
                ManageStaffService.displayStaff_sortName(this.session, false);
                pageViewer.changePage("current");
                break;
            case "3":
                ManageStaffService.displayStaff_sortAge(this.session);
                pageViewer.changePage("current");
                break;
            case "4":
                if(session.getCurrentActiveStaff().getRole()!=Role.ADMIN) System.out.println("Invalid input!");
                else{
                    ManageStaffService.displayStaff_filterAge(this.session);
                    pageViewer.changePage("current");
                }
                break;
            case "5":
                if(session.getCurrentActiveStaff().getRole()!=Role.ADMIN) System.out.println("Invalid input!");
                else{
                    ManageStaffService.displayStaff_filterGender(this.session);
                    pageViewer.changePage("current");
                }
                break;
            case "6":
                if(session.getCurrentActiveStaff().getRole()!=Role.ADMIN) System.out.println("Invalid input!");
                else{
                    ManageStaffService.displayStaff_filterRole(this.session);
                    pageViewer.changePage("current");
                }
                break;
            case "7":
                if(session.getCurrentActiveStaff().getRole()!=Role.ADMIN) System.out.println("Invalid input!");
                else{
                    ManageStaffService.displayStaff_filterBranch(this.session);
                    pageViewer.changePage("current");
                }
                break;
            case "b":
            case "B":
                pageViewer.changePage("back");
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }
}

