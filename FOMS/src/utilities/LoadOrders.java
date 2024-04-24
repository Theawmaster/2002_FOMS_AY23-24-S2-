package utilities;

import java.util.ArrayList;

import constants.FilePaths;
import constants.OrderStatus;
import entities.Branch;
import entities.MenuItem;
import entities.Order;

/**
 * The {@link LoadOrders} class loads Order data from the CSV database. It inherits from the {@link LoadData} class
 * @author Siah Yee Long
 */
public class LoadOrders extends LoadData<Order>{
    /**
     * This constructor creates a LoadOrders object with a list of Branch objects and a list of MenuItem objects
     * @param branches all information about branches
     * @param menu all information about menu items
     */
    protected LoadOrders(ArrayList<Branch> branches, ArrayList<MenuItem> menu) {
        super(branches, menu);
    }
    /**
     * The {@link loadDatafromCSV} method in this class loads in Order data from orderprocess_list.csv
     * @param branches all information about branches
     * @param menu all information about menu items
     * @return a list of Order objects with information loaded in
     */
    @Override
    protected ArrayList<Order> loadDatafromCSV(ArrayList<Branch> branches, ArrayList<MenuItem> menu){
        
        ArrayList<Order> orders = new ArrayList<>(); // the return value
    
        // load data from the menu list csv
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.orderprocessListPath.getPath());
        for(String s:serialisedData){
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("orderID") || s.contains("Status,") || row.length < 4)
                continue;

            // processing orderID column
            int orderID = Integer.parseInt(row[0]);

            // processing Status column
            String strStatus = row[1].trim().toUpperCase();
            OrderStatus status;
            if ("NEW".equals(strStatus)) {
                status = OrderStatus.NEW;
            } else if ("PREPARING".equals(strStatus)) {
                status = OrderStatus.PREPARING;
            } else if ("READY_TO_PICKUP".equals(strStatus)) {
                status = OrderStatus.READY_TO_PICKUP;
            } else if ("COMPLETED".equals(strStatus)) {
                status = OrderStatus.COMPLETED;
            } else if ("CANCELLED".equals(strStatus)) {
                status = OrderStatus.CANCELLED;
            } else{
                status = OrderStatus.UNDEFINED;
            }    

            // processing isTakeaway column
            boolean isTakeaway = "Takeaway".equalsIgnoreCase(row[2]) ? true : false;

            // processing Branch column
            Branch orderBranch=null;
            for(Branch b : branches){
                if(b.getBranchName().equalsIgnoreCase(row[3].trim()))
                    orderBranch = b;
            }

            // processing Items column
            ArrayList<MenuItem> orderItems = new ArrayList<>(); // create a new list of orderItems
            String[] itemNames = row[4].split("\\|");
            for(String itemN : itemNames){ // for each item name, match the MenuItem object and Branch object to clone
                if(itemN.equals("")) continue;
                for(MenuItem m : menu){
                    if(m.getFood().equalsIgnoreCase(itemN) && m.getBranch()==orderBranch){
                        MenuItem newItem;
                        try {
                            newItem = (MenuItem)m.clone();
                            orderItems.add(newItem);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                            continue;
                        } // clone the menu item as this is just another "cheeseburger" for example
                    }
                }
            }

            // processing ItemCustomisation and total price column at the same time
            Double totalPrice = 0.0;
            String[] itemCustomise = row[5].split("\\|");
            for(int i=0; i<itemCustomise.length-1; i++){ // for each customisation, set it in the corresponding menu items
                orderItems.get(i).setCustomization(itemCustomise[i]);
                totalPrice += orderItems.get(i).getPrice();
            }

            // processing LastModified
            long lastModified = Long.parseLong(row[6]);


            // done processing. now load the data all into an order object
            Order newOrder = new Order(orderID, orderItems, isTakeaway, status, totalPrice, orderBranch, lastModified);
            orders.add(newOrder);
        }
        return orders;
    }
    /**
     * This method adds an Order object to the CSV file
     * @param order the Order object to be added
     * @return true if the Order is successfully added to the CSV file
     */
    public static boolean addOrderToCSV(Order order) {
        // remove existing record
        SerialiseCSV.deleteToCSV(Integer.toString(order.getOrderId()), 0, FilePaths.orderprocessListPath.getPath());
        String items = "";
        String itemCust = "";
        for(MenuItem m : order.getItems()){
            items = items.concat(m.getFood()+"|");
            itemCust = itemCust.concat(m.getCustomization()+"|");
        }
        String orderRecord =  order.getOrderId() +","
                            + order.getStatus().toString() +","
                            + (order.isTakeaway() ? "Takeaway" : "Dine-in") +","
                            + order.getBranchName() +","
                            + items +","
                            + itemCust +","
                            + Long.toString(order.getLastModified());

        return SerialiseCSV.appendToCSV(orderRecord, FilePaths.orderprocessListPath.getPath());
    }
    /**
     * This method updates an Order object from the CSV file
     * @param order the Order object to be removed
     * @param status the status of the order
     * @return true if the Order is successfully removed from the CSV file
     */
    public static boolean updateOrderStatus(Order order, OrderStatus status){
        return SerialiseCSV.replaceColumnValue(Integer.toString(order.getOrderId()), 1, status.toString(), FilePaths.orderprocessListPath.getPath())
                && SerialiseCSV.replaceColumnValue(Integer.toString(order.getOrderId()), 6, Long.toString(order.getLastModified()), FilePaths.orderprocessListPath.getPath());
    }
    /**
     * This method removes an Order object from the CSV file
     */
    public static void destroyOrders(){
        SerialiseCSV.resetCSVData(FilePaths.orderprocessListPath.getPath(), "orderID,Status,isTakeaway,Branch,Items,ItemCustomisation,LastModified\n");
    }

}
