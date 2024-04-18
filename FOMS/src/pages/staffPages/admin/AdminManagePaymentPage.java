package pages.staffPages.admin;

import pages.iPage;
import pages.pageViewer;
import services.ManagePaymentsService;
import utilities.Session;

/**
 * This page allows the admin to manage payment methods
 * The admin can view all payment methods, enable a payment method, and disable a payment method
 * @author @Theawmaster
 */
public class AdminManagePaymentPage implements iPage {
    /**
     * The current active session
     */
    private Session session;
    public AdminManagePaymentPage(Session session) {
        this.session = session;
    }

    /**
     * Method to view Admin Manage Payment Methods Page
     */
    public void viewOptions() {
        System.out.println("== FOMS | Admin Manage Payment Methods ==");
        System.out.println("[1] View all payment methods");
        System.out.println("[2] Enable a payment method");
        System.out.println("[3] Disable a payment method");
        System.out.println("[B] Go back");
    }

    /**
     * Method to handle user input. Calls ManagePaymentsService to do the job instead
     * @param option: user input
     */
    public void handleInput(String option) {
        switch (option) {
            case "1":
                ManagePaymentsService.viewAllPaymentMethods(this.session);
                pageViewer.changePage("AdminManagePaymentPage");
                break;
            case "2":
                ManagePaymentsService.enablePaymentsService(this.session);
                pageViewer.changePage("AdminManagePaymentPage");
                break;
            case "3":
                ManagePaymentsService.disablePaymentsService(this.session);
                pageViewer.changePage("AdminManagePaymentPage");
                break;
            case "B":
            case "b":
                pageViewer.changePage("AdminAccessPage");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}