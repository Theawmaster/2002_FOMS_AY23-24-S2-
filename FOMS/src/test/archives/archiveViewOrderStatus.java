// package test.archives;

// import constants.OrderStatus;
// import pages.iPage;
// import pages.PageViewer;
// import utilities.Session;
// import entities.Order;
// public class archiveViewOrderStatus implements iPage{
//     private Session session;
    
//     public archiveViewOrderStatus(Session s){
//         this.session = s;
//     }

//     public void viewOptions() {
//         System.out.println("Please enter your order ID");

//         // Attempt to retrieve the order using the orderId
//         Order currentOrder = session.getOrderById(orderID);

//         if (currentOrder == null) {
//             System.out.println("No active order found.");
//             PageViewer.changePage("back");
//         } else {
//             // Display order details
//             currentOrder.printOrderDetails();

//             // Provide options based on order status
//             switch (currentOrder.getStatus()) {
//                 case NEW:
//                 case PREPARING:
//                     System.out.println("Your order is not ready yet. It's currently in the kitchen being prepared.");
//                     PageViewer.changePage("back");
//                     break;
//                 case READY_TO_PICKUP:
//                     System.out.println("Your order is ready for pickup! Would you like to pick it up now?");
//                     System.out.println("[Y] Yes, pick up the order.");
//                     System.out.println("[N] No, not yet.");
//                     break;
//                 case CANCELLED:
//                     System.out.println("Your order has been cancelled. Please contact support for more information.");
//                     PageViewer.changePage("back");
//                     break;
//                 default:
//                     System.out.println("Your order status is unknown. Please contact support.");
//                     PageViewer.changePage("back");
//                     break;
//             }
//         }
//     }
//     public void handleInput(String input) {
//         int orderID;

//         // Get the current active order
//         Order currentOrder = session.getCurrentActiveOrder();
//         if (currentOrder == null) {
//             System.out.println("No active orders found.");
//             PageViewer.changePage("current");
//         }
//         try{
//             orderID = Integer.parseInt(input);
//         }
//         catch (NumberFormatException e){
//             System.out.println("Please enter a valid order number!");
//             PageViewer.changePage("current");
//         }

//         for(Order o : this.session.getAllOrders()){
//             if(o.getOrderId() == orderID){
//                 String status = "";
//                 switch (o.getStatus()) {
//                     case OrderStatus.READY_TO_PICKUP:
//                         status = "ready for pickup!";
//                         break;
//                     case OrderStatus.PREPARING:
//                         status = "still preparing! Please have a seat first";
//                         break;
//                     case OrderStatus.CANCELLED:
//                         status = "cancelled! Please approach our staff for more help as you missed your order";
//                         break;
//                     case OrderStatus.COMPLETED:
//                         status = "completed! If you would like to order more, please do!";
//                         break;
//                     default:
//                         status = "unknown... hm mysterious...";
//                         break;
//                 }
//                 System.out.println("Your current order '"+orderID+"'"+" is "+status);
//                 break;
//             }
//         }
        




//         // Handle user input
//         switch (input.trim().toLowerCase()) {
//             case "y":
//                 if (currentOrder.getStatus() == OrderStatus.READY_TO_PICKUP) {
//                     // Change the order status to COMPLETED
//                     currentOrder.setStatus(OrderStatus.COMPLETED);
//                     archiveProcessOrderService.updateOrderCompletedStatus(currentOrder.getOrderId());
//                     System.out.println("Thank you for picking up your order. Have a great day!");

//                     // Go back to the main customer page
//                     PageViewer.changePage("back");
//                 } else {
//                     System.out.println("Your order is not ready to pick up yet.");
//                 }
//                 break;
//             case "n":
//                 // If the user selects 'No', simply go back to the main customer page
//                 System.out.println("You can come back to pick up your order later.");
//                 PageViewer.changePage("back");
//                 break;
//             default:
//                 System.out.println("Invalid input. Please choose a valid option.");
//                 break;
            
//         }
//     }
// }
