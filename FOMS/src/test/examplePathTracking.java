package test;

import pages.MainPage;
import pages.SelectBranchPage;
import pages.customerPages.CustomerPage;
import pages.staffPages.admin.AdminAccessPage;
import pages.staffPages.admin.AdminManageBranchPage;
import pages.staffPages.manager.ManagerAccessPage;
import utilities.PathTracker;
import utilities.Session;

public class examplePathTracking {
    public static void main(String[] args) {
        Session s = new Session();
        PathTracker pathTracker = new PathTracker("default", new SelectBranchPage(s), s);
        pathTracker.printCurrentPath();

        pathTracker.goTo("MainMenu", new MainPage(s));
        pathTracker.printCurrentPath();
        pathTracker.goTo("page A", new ManagerAccessPage(s));
        pathTracker.printCurrentPath();
        pathTracker.goTo("page B", new AdminAccessPage(s));
        pathTracker.printCurrentPath();
        pathTracker.goTo("Page c", new CustomerPage(s));
        pathTracker.printCurrentPath();
        pathTracker.goTo("pAge d", new AdminManageBranchPage(s));
        pathTracker.printCurrentPath();
        pathTracker.goBack();
    }
}
