package utilities;

import java.io.File;

/**
 * This class is used to handle data persistence between multiple instances of the FOMS application.
 * General idea:
 *  - instance I0 and instance I1 are running
 *  - I0 makes a change to the "data" directory
 *  - before I1 continues, it has to check if "data" directory has been modified
 *  - if "data" directory has been modified (presumably by another instance), it will load the data again into the session so that the session is updated
 * @author Siah Yee Long
 */
public class PersistenceHandler {
    /**
     * The {@link lastKnownModTime} variable stores the last known modification time of the "data" directory
     */
    private static long lastKnownModTime;
    /**
     * Static block to initialise the {@link lastKnownModTime} variable
     */
    static {
        lastKnownModTime = getLastModifiedTime("data"); // adjusted to point to a directory
    }
    /**
     * Private constructor to prevent instantiation of the class
     */
    private PersistenceHandler() {}
    /**
     * Method to get the last modified time of a file or directory
     * @param path the path to the file or directory
     * @return the last modified time of the file or directory
     */
    private static long getLastModifiedTime(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return getMaxModifiedTime(file);
        }
        return file.lastModified();
    }
    /**
     * Method to get the maximum last modified time of a directory and its subdirectories
     * @param directory the directory to check
     * @return the maximum last modified time of the directory and its subdirectories
     */
    private static long getMaxModifiedTime(File directory) {
        long maxTime = directory.lastModified();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                long time = file.lastModified();
                if (file.isDirectory()) { // Recursive call to handle nested directories
                    time = getMaxModifiedTime(file);
                }
                if (time > maxTime) {
                    maxTime = time;
                }
            }
        }
        return maxTime;
    }
    /**
     * Method to set the last known modification time of the "data" directory
     * @param path the path to the file or directory
     */
    private static void setLastModifiedTime(String path) {
        lastKnownModTime = getLastModifiedTime(path);
    }
    /**
     * Method to check if the "data" directory has been updated since the last known modification time
     * @param path the path to the file or directory
     * @return true if the "data" directory has been updated since the last known modification time, false otherwise
     */
    public static boolean hasBeenUpdated(String path) {
        long newModTime = getLastModifiedTime(path);
        if (newModTime > lastKnownModTime) {
            setLastModifiedTime(path); // Update the known modification time
            // Directory has been updated since last load
            return true;
        }
        // Directory has not been updated since last load
        return false;
    }
}