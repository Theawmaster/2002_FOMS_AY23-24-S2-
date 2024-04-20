// package test.archives;

// import constants.FilePaths;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.StandardOpenOption;
// import java.util.List;
// import java.util.Scanner;
// import java.util.stream.Collectors;

// public class archiveOrderManager {
//     public static void main(String[] args) {
//         archiveOrderManager manager = new archiveOrderManager();
//         manager.runOrderManagement();
//     }

//     public void runOrderManagement() {
//         Scanner scanner = new Scanner(System.in);
//         boolean running = true;

//         while (running) {
//             System.out.println("________            .___                _____                                                             __   ");
//             System.out.println("\\_____  \\_______  __| _/___________    /     \\ _____    ____ _____     ____   ____   _____   ____   _____/  |_ ");
//             System.out.println(" /   |   \\_  __ \\/ __ |/ __ \\_  __ \\  /  \\ /  \\\\__  \\  /    \\\\__  \\   / ___\\_/ __ \\ /     \\_/ __ \\ /    \\   __");
//             System.out.println("/    |    \\  | \\/ /_/ \\  ___/|  | \\/ /    Y    \\/ __ \\|   |  \\/ __ \\_/ /_/  >  ___/|  Y Y  \\  ___/|   |  \\  |  ");
//             System.out.println("\\_______  /__|  \\____ |\\___  >__|    \\____|__  (____  /___|  (____  /\\___  / \\___  >__|_|  /\\___  >___|  /__|  ");
//             System.out.println("        \\/           \\/    \\/                \\/     \\/     \\/     \\/     \\/      \\/      \\/     \\/     \\/      ");            
//             System.out.println("1. Process Order");
//             System.out.println("2. View All Orders");
//             System.out.println("3. View Order ID's order details & status");    
//             System.out.println("4. Exiting Order Management...");
//             System.out.print("Enter your choice: ");

//             String choice = scanner.nextLine();

//             switch (choice) {
//                 case "1":
//                     try {
//                         processOrder(scanner);
//                     } catch (IOException e) {
//                         System.err.println("Error processing order: " + e.getMessage());
//                     }
//                     break;
//                 case "2":
//                     try {
//                         viewAllOrders();
//                     } catch (IOException e) {
//                         System.err.println("Error viewing Orders: " + e.getMessage());
//                     }
//                     break;
//                 case "3":
//                     // try {
//                     //     // haven't implement yet
//                     // } catch (IOException e) {
//                     //     System.err.println("Error viewing orderID details and status: " + e.getMessage());
//                     // }
//                     break;
//                 case "4":
//                     running = false;
//                     System.out.println("Exiting Order Management...");
//                     new archiveManagerPage().showManagerMenu();
//                     break;
//                 default:
//                     System.out.println("Invalid choice, please try again.");
//                     break;
//             }
//         }

//         scanner.close();
//     }

//     public void processOrder(Scanner scanner) throws IOException {
//         System.out.println("orderID,Status,isittakeaway,Name,Price,Branch,Category,Customization");
//         List<String> orderMenuItems = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
//         for (int i = 1; i < orderMenuItems.size(); i++) {
//             System.out.println((i) + ". " + orderMenuItems.get(i));
//         }

//         System.out.print("Enter the number of the orderID to process: ");
//         int orderitemIndex = Integer.parseInt(scanner.nextLine());

//         if (orderitemIndex >= 1 && orderitemIndex < orderMenuItems.size()) {
//             String[] itemParts = orderMenuItems.get(orderitemIndex).split(",");
//             String orderID = itemParts[0]; // Extract orderID
//             String status = itemParts[1];
//             String isittakeaway = itemParts[2];

//             String newStatus = "READY FOR PICKUP";
//             status = newStatus;
            
//             // Construct the edited order status string
//             String editedOrderStatus = String.join(",", orderID, status, isittakeaway);
    
//             // Update the orderMenuItems list with the modified line
//             orderMenuItems.set(orderitemIndex, editedOrderStatus);

//             // Write the updated menu items back to the file
//             Files.write(Paths.get(FilePaths.orderprocessListPath.getPath()), orderMenuItems);
//             System.out.println("OrderID now ready for pickup!");
//         } else {
//             System.out.println("Invalid orderID number.");
//         }
//     }
    
//     public void viewAllOrders() throws IOException {
//         List<String> ordermenuItems = Files.readAllLines(Paths.get(FilePaths.orderprocessListPath.getPath()));
//         if (ordermenuItems.isEmpty()) {
//             System.out.println("The order menu list is currently empty.");
//             return;
//         }
//         else {
//             System.out.println("orderID,Status,isittakeaway,Name,Price,Branch,Category,Customization");
//             for (int i = 1; i < ordermenuItems.size(); i++) {
//                 System.out.println((i) + ". " + ordermenuItems.get(i));
//             }
//         }
//     }

// }
