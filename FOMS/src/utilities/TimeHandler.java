package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains methods that handle time-related operations
 * This class is used to check if the customer has collected their order within the time limit
 */
public class TimeHandler {
    /**
     * This method returns the current date and time as a string in the format "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getCurrentDateTimeAsString() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Define the format for the date and time string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Format the date and time using the defined format
        String formattedDateTime = currentDateTime.format(formatter);
        
        return formattedDateTime;
    }
    /**
     * This method returns the current time in seconds
     * @return The current time in seconds
     */
    public static long getCurrentTime(){
        return System.currentTimeMillis() / 1000;
    }
    /**
     * This method checks if the time elapsed since the last time is greater than or equal to the specified number of seconds
     * @param seconds The number of seconds to check against
     * @param lastTime The last time that was recorded
     * @return true if the time elapsed is greater than or equal to the specified number of seconds, false otherwise
     */
    public static boolean hasElapsed(int seconds, long lastTime){
        Logger.debug("Time elapsed: "+(getCurrentTime()-lastTime));
        return (getCurrentTime()-lastTime) >= seconds;
    }
}
