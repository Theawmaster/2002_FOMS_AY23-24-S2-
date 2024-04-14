package services;

import utilities.LoadStaffs;
import entities.Staff;

import java.util.ArrayList;
import java.io.IOException;

/**
 * This class provides services to manage staff
 * @author @Theawmaster
 */
public class ManageStaffService {
    private LoadStaffs loadStaffs = new LoadStaffs();

    /**
     * Method to display all staff
     */
    public void displayAllStaff() {
        ArrayList<Staff> staffList = loadStaffs.loadDatafromCSV();
        if (staffList.isEmpty()) {
            System.out.println("No staff records available.");
            return;
        }
        for (Staff staff : staffList) {
            System.out.println(staff); // Assuming Staff class has a properly overridden toString() method
        }
    }
}
