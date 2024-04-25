package utilities;

import constants.Settings;

/**
 * This class should be used for logging messages in the project
 */
public class Logger {
    /**
     * Set to false to disable logging. Set to true to enable logging of info, debug, or error messages
     */
    public static boolean loggerIsEnabled = (boolean)Settings.ENABLE_DEBUG.getValue();
    /**
     * color string constant for ANSI_RESET
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * color string constant for ANSI_RED
     */
    public static final String ANSI_RED = "\u001B[31m";
    /**
     * color string constant for ANSI_YELLOW
     */
    public static final String ANSI_YELLOW = "\u001B[33m";
    /**
     * color string constant for ANSI_GREEN
     */
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * color string constant for ANSI_BLUE
     */
    public static final String ANSI_BLUE = "\u001B[34m";
    /**
     * color string constant for ANSI_PURPLE
     */
    public static final String ANSI_PURPLE = "\u001B[35m";
    /**
     * color string constant for ANSI_CYAN
     */
    public static final String ANSI_CYAN = "\u001B[36m";
    /**
     * color string constant for ANSI_WHITE
     */
    public static final String ANSI_WHITE = "\u001B[37m";
    /**
     * color string constant for ANSI_BLACK
     */
    public static final String ANSI_BLACK = "\u001B[30m";
    /**
     * Private constructor to prevent instantiation
     */
    private Logger(){}
    /**
     * Prints message in colour
     * @param msg message to print
     * @param color color to print in
     */
    public static void printColor(String msg, String color) {
        System.out.print(color + msg + ANSI_RESET);
    }
    /**
     * Prints info message in green if logger is enabled
     * @param msg msg to print
     */
    public static void info(String msg) {
        if (loggerIsEnabled)
            System.out.println(ANSI_GREEN + "[INFO]: " + msg + ANSI_RESET);
    }
    /**
     * Prints debug message in yellow if logger is enabled 
     * @param msg msg to print
     */
    public static void debug(String msg) {
        if (loggerIsEnabled)
            System.out.println(ANSI_YELLOW + "[DEBUG]: " + msg + ANSI_RESET);
    }
    /**
     * Prints error message in red if logger is enabled
     * @param msg msg to print
     */
    public static void error(String msg) {
        if (loggerIsEnabled)
            System.out.println(ANSI_RED + "[ERROR]: " + msg + ANSI_RESET);
    }
}
