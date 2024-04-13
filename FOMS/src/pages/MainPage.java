package pages;

/**
 * This is the Main Page that the user will see. While it is called the Main Page, the user actually sees the SelectBranchPage before seeing this page.
 * This page facilitates the segregation between a customer and a staff.
 * @author Siah Yee Long
 */
public class MainPage implements iPage{
    /**
     * Method to view menu options
     */
    public void viewOptions(){
        System.out.println(" __          __  _                          \n" +
                           " \\ \\        / / | |                         \n" +
                           "  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___ \n" +
                           "   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" +
                           "    \\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" +
                           "     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|\n" +
                           "                                            \n" +
                           "                                            ");
        System.out.println("[1] Start Ordering");
        System.out.println("[2] Log in as staff");
        System.out.println("[B] Back to select branch");
    }

    /**
     * Method to handle user input 
     * @param choice branches the pages
     */
    public void handleInput(String choice){
        switch (choice) {
            case "1":
                pageViewer.changePage("CustomerPage");
                break;
            case "2":
                pageViewer.changePage("StaffLoginPage");
                break;
            case "b":
            case "B":
                pageViewer.changePage("SelectBranchPage");
                break;
            default:
            // TODO: handle an exception here
                break;
        }
    }
}
