package pages;

/**
 * This interface will be implemented by all pages. Every page should have a method to view the menu options, 
 * and the corresponding switch/case or if/else logic to handle the choices 
 * @author Siah Yee Long
 */
public interface iPage {
    /**
     * To view menu options of the page. Normally have the ASCII art here
     */
    void viewOptions();
    /**
     * To handle the user input
     * @param choice note that this is of type String such that it can handle "b", "quit", "exit" etc.
     */
    void handleInput(String choice);
}
