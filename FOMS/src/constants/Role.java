package constants;

/**
 * Enum to represent staff roles. Where Admin > Manager > Staff
 */
public enum Role {
    /** Admin role. Highest rank */
    ADMIN,
    /** Manager role. Middle rank */
    MANAGER,
    /** Staff role. lowest rank */
    STAFF,
    /** Undefined for initialisation only */
    UNDEFINED;
}
