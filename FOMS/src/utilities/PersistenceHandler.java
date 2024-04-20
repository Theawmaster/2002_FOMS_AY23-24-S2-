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
    private static long lastKnownModTime;

    static {
        lastKnownModTime = getLastModifiedTime("data"); // Adjusted to point to a directory
    }

    private static long getLastModifiedTime(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return getMaxModifiedTime(file);
        }
        return file.lastModified();
    }

    // Method to get the most recent modification time of the directory or any file within it
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

    public static void setLastModifiedTime(String path) {
        lastKnownModTime = getLastModifiedTime(path);
    }

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