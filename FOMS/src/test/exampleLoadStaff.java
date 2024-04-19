package test;

import java.util.ArrayList;
import java.util.HashMap;

import entities.Staff;
import entities.MenuItem;
import entities.Branch;
import utilities.LoadBranches;
import utilities.LoadMenuItems;
import utilities.LoadStaffs;
/**
 * This is a test class for you to see how a list of Staffs can be loaded from the csv files. 
 * Look at LoadData.java, Staff.java, SerialiseCSV.java to see how it was done
 */
public class exampleLoadStaff {
    public static void main(String[] args) {
        LoadMenuItems m = new LoadMenuItems(null);
        ArrayList<MenuItem> menuitems = m.getLoadedData();
        for(MenuItem i : menuitems)                
            i.printMenuItems();

        LoadStaffs l = new LoadStaffs();
        ArrayList<Staff> staffs = l.getLoadedData();
        for(Staff s : staffs)
            s.printStaff();

        LoadBranches b = new LoadBranches();
        ArrayList<Branch> branches = b.getLoadedData();
        for(Branch x : branches)
            x.printBranch();
    }
}
