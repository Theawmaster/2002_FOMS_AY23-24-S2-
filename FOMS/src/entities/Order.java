    package entities;

    import constants.OrderStatus;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.UUID;
    import java.util.concurrent.atomic.AtomicInteger;

    import utilities.Time;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.time.temporal.ChronoUnit;

    /**
     * Represents an order placed by a customer.
     */
    public class Order {
        /** Unique identifier for the order. */
        private int orderId;
        
        /** List of items included in the order. */
        private List<MenuItem> items;
        
        /** Indicates whether the order is for takeaway or dine-in. */
        private boolean isTakeaway;
        
        /** Current status of the order. */
        private OrderStatus status;
        
        /** Total price of all items in the order. */
        private double totalPrice;
        
        /** Date and time when the order was created. */
        private LocalDateTime orderDateTime; 

        /**
         * Constructs a new order.
         * @param isTakeaway True if the order is for takeaway, false if it is for dine-in.
         */
        public Order() {
            this.items = new ArrayList<>();
            this.isTakeaway = false;
            this.status = OrderStatus.NEW;
            this.totalPrice = 0.0;
            this.orderDateTime = LocalDateTime.now();  // Store the order date and time when the order is created
        }

        /**
         * Checks if the order has exceeded the specified timeframe for pickup.
         * @param minutes The specified timeframe in minutes.
         * @return True if the order has exceeded the specified timeframe, false otherwise.
         */
        public boolean hasExceededTimeframeForPickup(int minutes) {
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Calculate the difference in minutes between the current time and the order time
            long minutesDifference = ChronoUnit.MINUTES.between(orderDateTime, currentDateTime);

            // Return true if the difference exceeds the specified timeframe, false otherwise
            return minutesDifference >= minutes;
        }

        /**
         * Adds a menu item to the order.
         * @param item The menu item to add.
         * @param customization Customization details for the menu item.
         */
        public void addItem(MenuItem item, String description, String customization) {
            // Create a new MenuItem with customization and add it to the order
            MenuItem customizedItem = new MenuItem(item.getFood(), item.getPrice(), item.getBranch(),item.getCategory(), description, customization);
            if (customization.isEmpty())
            {
                items.add(item); // Add menu items in order without customization 
                updateTotalPrice();
            }
            else
            {
                items.add(customizedItem); // Add menu items in order with customization
                updateTotalPrice();
            }
        }

        /**
         * Removes a menu item from the order.
         * @param item The menu item to remove.
         * @return True if the item was successfully removed, false otherwise.
         */
        public boolean removeItem(MenuItem item) {
            boolean removed = items.remove(item);
            if (removed) {
                updateTotalPrice();
            }
            return removed;
        }

        /**
         * Updates the total price of the order based on the items.
         */
        private void updateTotalPrice() {
            totalPrice = items.stream().mapToDouble(MenuItem::getPrice).sum();
        }

        /**
         * Set the unique identifier of the order.
         * @return Sets the order ID.
         */
        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
        
        /**
         * Gets the unique identifier of the order.
         * @return Retrieve the order ID.
         */
        public int getOrderId() {
            return orderId;
        }

        /**
         * Gets the list of items in the order.
         * @return The list of items.
         */
        public List<MenuItem> getItems() {
            return new ArrayList<>(items); 
        }

        /**
         * Checks if the order is for takeaway.
         * @return True if the order is for takeaway, false otherwise.
         */
        public boolean isTakeaway() {
            return isTakeaway;
        }

        /**
         * Sets whether the order is for takeaway or dine-in.
         * @param isTakeaway True for takeaway, false for dine-in.
         */
        public void setTakeaway(boolean isTakeaway) {
            this.isTakeaway = isTakeaway;
        }

        /**
         * Gets the status of the order.
         * @param orderId The unique identifier of the order.
         * @return The status of the order.
         */
        public OrderStatus getStatus() {
            // Yet to implement orderId to capture status of the order ...
            return status;
        }

        /**
         * Sets the status of the order.
         * @param status The status of the order.
         */
        public void setStatus(OrderStatus status) {
            this.status = status;
        }

        /**
         * Gets the total price of the order.
         * @return The total price.
         */
        public double getTotalPrice() {
            return totalPrice;
        }

        /**
         * Counts the total number of items in the order.
         * @return The total number of items.
         */
        public int countTotalItems(){
            return items.size();
        }

        /**
         * Prints the details of the order.
         */
        public void printOrderDetails() {
            System.out.println("Order ID: " + orderId);
            System.out.println("Order Type: " + (isTakeaway ? "Takeaway" : "Dine-in"));
            System.out.println("Order Status: " + status);
            System.out.println("Total Items in Order: " + countTotalItems());
            System.out.println("Items in Order:");
            AtomicInteger i = new AtomicInteger(1);
            items.forEach(item -> {
                System.out.println(i.getAndIncrement() + "- " + item.getFood() + ", " + item.getPrice() + ", " + item.getCategory() + ", " + item.getDescription() + ", "+ item.getCustomization());
            });
            System.out.println("Total Price: " + totalPrice);
        }
    }
