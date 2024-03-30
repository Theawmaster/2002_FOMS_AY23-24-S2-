package test;

import java.util.ArrayList;
import entities.Staff;
import entities.MenuItems;
import entities.Branch;
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

        ArrayList<MenuItems> menuitems = new ArrayList<>();
        menuitems = d.loadMenuItems();
        for(MenuItems i : menuitems)                
            i.printMenuItems();
      
        ArrayList<Branch> branches = new ArrayList<>();
        branches = d.loadBranches();
        for(Branch b : branches)
            b.prinBranch();
    }
}
