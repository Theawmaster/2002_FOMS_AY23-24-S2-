package constants;

public enum Settings {
    PW_MAX_TRIES(3),
    PW_MIN_CHARACTERS(8),
    FORGOTPW_MAX_TRIES(3);

    private final int value;

    Settings(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
