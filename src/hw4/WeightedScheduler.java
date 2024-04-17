/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.List;

/**
 * Scheduler implementation that processes orders based on a calculated weight,
 * prioritizing orders that can be immediately executed given current resource availability.
 */
public class WeightedScheduler implements Scheduler {

    /**
     * Schedules and processes orders using a weighted approach, focusing on urgency and execution capability.
     * @param orders the list of orders to process.
     * @param grill the grill to cook skewers and pitas.
     * @param fryer the fryer to cook fries.
     */
    @Override
    public void schedule(List<Order> orders, Grill grill, Fryer fryer) {
        int currentTime = 0; // Simulation start time, typically the opening time of the shop.

        while (!orders.isEmpty()) {
            boolean orderProcessed = false;

            for (Order order : findOrdersByWeight(orders, currentTime)) {
                if (grill.canAddToGrill(order) && fryer.canAddFries(order)) {
                    //execute the order immediately if both resources can accommodate it
                    grill.addToGrill(order, currentTime);
                    if (order.getFries() > 0) {
                        fryer.addFries(order, currentTime);
                    }
                    orders.remove(order); //remove the order from the list after processing
                    orderProcessed = true;
                    break; //exit the loop after processing one order
                }
            }

            if (!orderProcessed) {  // if no order could be processed, increment time and update resources
                currentTime++; 
                grill.processGrill(currentTime);
                fryer.update(currentTime);
            }
        }


        while (!grill.isEmpty() || !fryer.isEmpty()) {  // continue processing until both the grill and fryer are empty
            grill.processGrill(currentTime);
            fryer.update(currentTime);
            currentTime++;
        }
    }

    /**
     * returns a sorted list of orders based on their weight from highest to lowest.
     * this uses a simple sorting mechanism; for a large number of orders, consider more efficient sorting algorithms.
     * @param orders the list of orders.
     * @param currentTime the current simulation time.
     * @return the sorted list of orders by weight.
     */
    private List<Order> findOrdersByWeight(List<Order> orders, int currentTime) {
        orders.sort((o1, o2) -> Double.compare(calculateWeight(o2, currentTime), calculateWeight(o1, currentTime)));
        return orders;
    }

    /**
     * calculates the weight of an order based on its urgency and the remaining time until delivery.
     * @param order The order to evaluate.
     * @param currentTime The current simulation time.
     * @return The weight of the order.
     */
    private double calculateWeight(Order order, int currentTime) {
        int tExec = estimateExecutionTime(order);
        int tDelReq = order.getTdelReq();
        return (double) (tExec - (tDelReq - currentTime)) / tExec;
    }

    /**
     * estimates the execution time for an order based on its contents.
     * @param order The order to evaluate.
     * @return Estimated execution time in minutes.
     */
    private int estimateExecutionTime(Order order) {
        int time = 0;
        time += order.getNpp() * 25; // assume 25 minutes for pork skewers
        time += order.getNpc() * 20; // 20 minutes for chicken skewers
        time += order.getNps() * 25; // 25 minutes for another type
        time += order.getFries() * 20; // 20 minutes per portion of fries
        return time;
    }
}
