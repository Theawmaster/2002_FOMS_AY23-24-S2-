package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeHandler {
    public static String getCurrentDateTimeAsString() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Define the format for the date and time string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Format the date and time using the defined format
        String formattedDateTime = currentDateTime.format(formatter);
        
        return formattedDateTime;
    }
    public static long getCurrentTime(){
        return System.currentTimeMillis() / 1000;
    }
    public static boolean hasElapsed(int seconds, long lastTime){
        Logger.debug("Time elapsed: "+(getCurrentTime()-lastTime));
        return (getCurrentTime()-lastTime) >= seconds;
    }
}
