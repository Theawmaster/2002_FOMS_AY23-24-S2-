package constants;

public enum Settings {
    ENABLE_DEBUG(true), // enable / disable debugging
    PW_MAX_TRIES(3), // max number of tries for password keying in attempt
    PW_MIN_CHARACTERS(8), // min number of characters for setting of new password
    FORGOTPW_MAX_TRIES(3), // TODO: check again, dk what this does
    MAX_COLLECTION_TIME_SECONDS(60), // max time the customer can take to collect an order before it becomes cancelled
    CANCEL_CHARACTER("c"); // character to cancel option selection
    /**
     * Set the setting object to final
     */
    private final Object value;
    /**
     * Constructor for the Settings class
     * @param value
     */
    Settings(Object value) {
        this.value = value;
    }
    /**
     * @return the settings object. downcast as required
     */
    public Object getValue() {
        return this.value;
    }
}
