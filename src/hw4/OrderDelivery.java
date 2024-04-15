package hw4;
import java.util.List;
import java.util.ArrayList;

/**
 * the OrderDelivery system manages the processing of food orders using different scheduling algorithms.
 * it handles the operation based on the configuration of grills and fryers using specified dimensions
 * and capacities for cooking products
 */
public class OrderDelivery {
    private Scheduler scheduler;
    private Grill grill;
    private List<Fryer> fryers;

    /**
     * constructs an OrderDelivery system with specified scheduler, grill, and fryers.
     * 
     * @param scheduler The scheduling strategy to be used for order processing.
     * @param grill The grill to be used including its preparation specifications.
     * @param fryers The list of fryers available for use.
     */
    public OrderDelivery(Scheduler scheduler, Grill grill, List<Fryer> fryers) {
        this.scheduler = scheduler;
        this.grill = grill;
        this.fryers = fryers;
    }

    /**
     * processes a list of orders using the initialized scheduler and resources.
     * 
     * @param orders The list of orders to be processed.
     */
    public void processOrders(List<Order> orders) {
        scheduler.schedule(orders, grill, fryers);
    }

    /**
     * the main method to set up and start the OrderDelivery system.
     * 
     * @param args command line arguments providing the grill fryer specifications and scheduler choice.
     */
    public static void main(String[] args) {
        if (args.length != 8) {
            System.out.println("Usage: java OrderDelivery <grillLength> <grillPrepTime> <numFryers> <fryerCapacity> " +
                "<skewerLengthSouvlaki> <skewerLengthSeftalia> <pittaSize> <algorithmChoice>");
            return;
        }

        try {
            int grillLength = Integer.parseInt(args[0]);
            int grillPrepTime = Integer.parseInt(args[1]);
            int numFryers = Integer.parseInt(args[2]);
            int fryerCapacity = Integer.parseInt(args[3]);
            int skewerLengthSouvlaki = Integer.parseInt(args[4]);
            int skewerLengthSeftalia = Integer.parseInt(args[5]);
            int pittaSize = Integer.parseInt(args[6]);
            int algorithmChoice = Integer.parseInt(args[7]);

            Scheduler scheduler = chooseScheduler(algorithmChoice);
            Grill grill = new Grill(grillLength, grillPrepTime);
            List<Fryer> fryers = new ArrayList<>();
            for (int i = 0; i < numFryers; i++) {
                fryers.add(new Fryer(fryerCapacity));
            }

            List<Order> orders = loadOrders();  // GIANNIS

            OrderDelivery delivery = new OrderDelivery(scheduler, grill, fryers);
            delivery.processOrders(orders);

            System.out.println("Simulation complete. Results processed.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in arguments.");
        }
    }

    /**
     * selects the appropriate scheduler based on the command line
     * 
     * @param algorithmChoice The integer representing the scheduler choice.
     * @return The scheduler instance according to the choice.
     */
    private static Scheduler chooseScheduler(int algorithmChoice) {
        switch (algorithmChoice) {
            case 1: return new FCFSScheduler();
            case 2: return new SJFScheduler();
            case 3: return new WeightedScheduler();
            default: throw new IllegalArgumentException("Invalid algorithm choice. Must be 1, 2, or 3.");
        }
    }

    /**
     *  ANAGAIA METHOD STIN KLASI TOU GIANNI POU EXEI TO FILE HANDLING.
     * 
     * @return A list of orders pou prepei na kameis sosta format.
     */
    private static List<Order> loadOrders() {

        return new ArrayList<>();
    }
}
