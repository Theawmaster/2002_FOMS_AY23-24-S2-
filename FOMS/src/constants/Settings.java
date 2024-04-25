package constants;

/**
 * Enum to define the programme-wide settings. Change here as required
 */
public enum Settings {
    /**
     * Enable / disable debugging
     */
    ENABLE_DEBUG(false),
    /**
     * Max number of tries for password keying in attempt
     */
    PW_MAX_TRIES(3),
    /**
     * Min number of characters for setting of new password
     */
    PW_MIN_CHARACTERS(8), 
    /**
     * Max number of times a user can key in the Captcha string wrongly 
     */
    FORGOTPW_MAX_TRIES(3),
    /**
     * Max time the customer can take to collect an order before it becomes cancelled
     */
    MAX_COLLECTION_TIME_SECONDS(60),
    /**
     * Character to cancel option selection
     */
    CANCEL_CHARACTER("c"); 
    /**
     * Set the setting object to final
     */
    private final Object value;
    /**
     * Constructor for the Settings class
     * @param value the settings object
     */
    Settings(Object value) {
        this.value = value;
    }
    /**
     * Getter method to get the settings object
     * @return the settings object. Downcast as required
     */
    public Object getValue() {
        return this.value;
    }
}
