package hw4;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class generates orders and writes them to a text file.
 * It takes the number of orders as a command-line argument and generates that many random orders.
 * Each order is then written to a text file named "Orders.txt".
 * 
 * @author Ioannis Anastasi ID: 1131712
 * @since 14/04/2024
 */
public class OrderGenerator {

    /**
     * Main method that generates orders and writes them to a text file.
     * 
     * @param args Command-line arguments. Expects a single argument specifying the number of orders to generate.
     */
    public static void main(String[] args) {
        
        // Check if the correct number of command-line arguments is provided
        if (args.length != 1) {
            System.err.println("Usage: java OrderGenerator source-file");
            System.exit(0);
        }
        
        List<Order> orders = new ArrayList<>(); // List to store generated orders
        
        try { 
              
            // Attach a file to FileWriter 
            FileWriter fw  = new FileWriter("Orders.txt");
            fw.write(args[0] + "\n"); // Write the number of orders to the file
            
            // Generate the specified number of orders
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                Generator order = new Generator(); // Create a new random order
                order.generateEachPortion(); // Generate each portion of the order randomly
                order.generateFries(); // Generate fries for the order randomly
                order.generateTimeOfOrder(); // Generate the time of order randomly
                order.generateRequestedTime(); // Generate the requested time for delivery randomly
                orders.add(i, order); // Add the generated order to the list
                fw.write(orders.get(i) + "\n"); // Write the order details to the file
            }
            
            // Close the file 
            fw.close(); 
        } 
        catch (Exception e) { 
            System.err.println("Error. " + e.getMessage()); 
        } 
     
    }

}
