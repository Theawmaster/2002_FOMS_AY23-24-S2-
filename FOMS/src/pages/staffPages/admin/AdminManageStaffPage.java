package pages.staffPages.admin;

import services.ManageStaffService;
import constants.Role;

import pages.iPage;
import pages.pageViewer;
import utilities.Session;
import java.util.Scanner;
import entities.Staff;
import java.io.IOException;

/**
 * The AdminManageStaffPage class is a page that allows the admin to manage staff members
 * The admin can view all staff members, fire staff members, hire staff members, and promote staff members
 * The admin can also go back to the AdminAccessPage
 * @author @Theawmaster
 */
public class AdminManageStaffPage implements iPage {
    /**
     * The current active session
     */
    private Session session;
    private ManageStaffService manageStaffService = new ManageStaffService();
    private Scanner scanner = new Scanner(System.in); 

    public AdminManageStaffPage(Session session) {
        this.session = session;
    }

    /**
     * Method to display staff management page
     */
    public void viewOptions() {
        System.out.println("Admin Manage Staff Page");
        System.out.println("[1]. Display all Staff");
        System.out.println("[2]. Fire Staff");
        System.out.println("[3]. Hire Staff");
        System.out.println("[4]. Promote Staff to Manager");
        System.out.println("[5]. Transfer staff to another branch");
        System.out.println("[B]. Back");
    }

    /**
     * Method to handle user input
     * @param option
     */
    public void handleInput(String option) {
        switch(option) {
            case "1":
                manageStaffService.displayAllStaff();
                break;
            case "2":
                manageStaffService.fireStaff();
                break;
            case "3":
                manageStaffService.addStaff();
                break;
            case "4":
                manageStaffService.promoteToManager();
                break;
            case "5":
                manageStaffService.transferStaff();
                break;
            case "B":
                pageViewer.changePage("AdminAccessPage");
                break;
            case "b":
                pageViewer.changePage("AdminAccessPage");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
    
}