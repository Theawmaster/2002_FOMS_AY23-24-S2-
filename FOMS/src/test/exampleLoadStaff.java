package test;

import java.util.ArrayList;
import entities.Staff;
import utilities.LoadData;
/**
 * This is a test class for you to see how a list of Staffs can be loaded from the csv files. 
 * Look at LoadData.java, Staff.java, SerialiseCSV.java to see how it was done
 */
public class exampleLoadStaff {
    public static void main(String[] args) {
        LoadData d = new LoadData();
        ArrayList<Staff> staffs = new ArrayList<>();
        staffs = d.loadStaffs();
        for(Staff s : staffs)
            s.prinStaff();
    }
}
