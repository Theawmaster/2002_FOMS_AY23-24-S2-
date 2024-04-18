package constants;

public enum Role {
    ADMIN,
    STAFF,
    MANAGER,
    UNDEFINED;

    public boolean equalsIgnoreCase(String string) {
        if (string == null) {
            return false;
        }
        return this.name().equalsIgnoreCase(string.trim());
    }
}
