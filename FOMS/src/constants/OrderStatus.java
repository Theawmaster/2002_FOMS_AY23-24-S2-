package constants;

/**
 * Enum representing all the types of order statuses
 */
public enum OrderStatus {
    /** A new order */
    NEW, 
    /** Once payment has been made */
    PREPARING, 
    /** Once order has been processed by a staff */
    READY_TO_PICKUP, 
    /** Once customer has collected */
    COMPLETED,
    /** If customer has not picked up the order after x minutes (see settings) */
    CANCELLED,
    /** Unknown status */
    UNDEFINED;
}