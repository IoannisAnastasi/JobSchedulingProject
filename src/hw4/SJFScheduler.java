/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.ArrayList;
import java.util.List;

/**
 * scheduler implementation that processes orders using the Shortest Job First approach,
 * selecting orders based on the shortest cooking time necessary.
 */
public class SJFScheduler implements Scheduler {

    private static final int CUTOFF_TIME = 720; 
    /**
     * Schedules and processes orders using a Shortest Job First approach.
     * Ensures that all items in an order are placed at the same time if sufficient space is available,
     * and no orders are processed for delivery past 12:00 PM.
     *
     * @param orders the list of orders to process.
     * @param grill the grill to cook skewers and pitas.
     * @param fryer the fryer to cook fries.
     */
    @Override
    public void schedule(List<Order> orders, Grill grill, Fryer fryer) {
        int currentTime = 0; //simulation starts at the opening time of the shop.
        int[] endTimes = new int[orders.size()];///array for storing the end time of each order.
        List<Order> ordersCopy = new ArrayList<>(orders);//create a copy of orders to keep the original list intact for output.

        while (!ordersCopy.isEmpty()) {
            Order shortestOrder = findShortestOrder(ordersCopy);
            int index = orders.indexOf(shortestOrder); // use original list to keep the correct index.

            while (!grill.canAddToGrill(shortestOrder) || !fryer.canAddFries(shortestOrder)) {
                grill.processGrill(currentTime);
                fryer.update(currentTime);
                currentTime++; //increment time as we wait for space.

                if (currentTime >= CUTOFF_TIME) {
                    break; //stop processing as we reached the cut-off time.
                }
            }

            if (currentTime >= CUTOFF_TIME) {
                break; //if we reach or exceed cut-off time during waiting, skip further processing.
            }

            //process the order if there is still time before the cut-off.
            grill.addToGrill(shortestOrder, currentTime);
            if (shortestOrder.getFries() > 0) {
                fryer.addFries(shortestOrder, currentTime);
            }
            int cookingTime = calculateMinimumCookingTime(shortestOrder);
            currentTime += cookingTime;  //increase currentTime by the execution time of this order.

            if (currentTime < CUTOFF_TIME) {
                endTimes[index] = currentTime;
            } else {
                endTimes[index] = -1; //mark as not delivered if the completion time is past the cut-off.
            }

            ordersCopy.remove(shortestOrder); //remove the processed order from the copy list.
        }

       
        while (currentTime < CUTOFF_TIME && (!grill.isEmpty() || !fryer.isEmpty())) {
            grill.processGrill(currentTime);
            fryer.update(currentTime);
            currentTime++;
        }

        OrderDelivery.writeResults(orders, endTimes, "deliveries.txt"); //write results using the original orders list.
    }

    private Order findShortestOrder(List<Order> orders) {
        Order shortestOrder = orders.get(0);
        int shortestTime = calculateMinimumCookingTime(shortestOrder);

        for (Order order : orders) {
            int cookingTime = calculateMinimumCookingTime(order);
            if (cookingTime < shortestTime) {
                shortestOrder = order;
                shortestTime = cookingTime;
            }
        }
        return shortestOrder;
    }
    
      
    
    /**
     * calculates the minimum cooking time required for the given order.
     * @param order The order to evaluate.
     * @return The cooking time in minutes.
     */
    private int calculateMinimumCookingTime(Order order) {
        //initialize minimum cooking time with a high value for comparison purposes.
        int minTime = Integer.MAX_VALUE;

        //calculate cooking times for pork skewer pittes. Each pitta includes two skewers.
        if (order.getNpp() > 0) {
            int porkTime = 20 + (int)(Math.random() * (25 - 20 + 1)); 
            porkTime *= 2; //there are two smiles per pitta
            minTime = Math.min(minTime, porkTime);
        }

        //calculate cooking times for chicken skewer pittas. Each pitta includes two skewers.
        if (order.getNpc() > 0) {
            int chickenTime = 15 + (int)(Math.random() * (20 - 15 + 1)); 
            chickenTime *= 2; // There are two skewers per pita
            minTime = Math.min(minTime, chickenTime);
        }

     
        if (order.getNps() > 0) {
            int seftaliaTime = 25 * 2; 
            minTime = Math.min(minTime, seftaliaTime);
        }

       
        if (order.getNpm() > 0) {
            int mixTime = Math.max(20 + (int)(Math.random() * (25 - 20 + 1)), 25);
            mixTime *= 2; 
            minTime = Math.min(minTime, mixTime);
        }

        if (order.getFries() > 0) {
            int friesTime = 20; 
            minTime = Math.min(minTime, friesTime);
        }

        return minTime;
    }

}
