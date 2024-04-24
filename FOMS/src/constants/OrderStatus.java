package constants;

/**
 * Enum representing all the types of order statuses
 */
public enum OrderStatus {
    NEW, 
    PREPARING, 
    READY_TO_PICKUP, 
    COMPLETED,
    CANCELLED, // If customer has not picked up the order after x minutes (see settings)
    UNDEFINED;
}