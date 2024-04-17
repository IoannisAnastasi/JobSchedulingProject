/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.List;

/**
 * scheduler implementation that processes orders using the Shortest Job First approach,
 * selecting orders based on the shortest cooking time necessary.
 */
public class SJFScheduler implements Scheduler {

    /**
     * schedules and processes orders using a Shortest Job First approach.
     * this method finds the shortest cooking order manually and ensures that all items
     * in an order are placed at the same time if sufficient space is available.
     * @param orders the list of orders to process.
     * @param grill the grill to cook smiles and pittes.
     * @param fryer the fryer to cook fries.
     */
    @Override
    public void schedule(List<Order> orders, Grill grill, Fryer fryer) {
        int currentTime = 0; //simulation start time pou einai typically the opening time of the shop.

        while (!orders.isEmpty()) {
            Order shortestOrder = findShortestOrder(orders);

          
            while (!grill.canAddToGrill(shortestOrder) || !fryer.canAddFries(shortestOrder)) {  // wait until there is enough space for both the grill and fryer items of the order
                grill.processGrill(currentTime);
                fryer.update(currentTime);
                currentTime++; // increment time as we wait for space
            }

            
            grill.addToGrill(shortestOrder, currentTime);// place items on grill and in fryer
            if (shortestOrder.getFries() > 0) {
                fryer.addFries(shortestOrder, currentTime);
            }

          
            orders.remove(shortestOrder);  // remove the order from the list after processing
        }

       
        while (!grill.isEmpty() || !fryer.isEmpty()) { // continue processing until both the grill and fryer are empty
            grill.processGrill(currentTime);
            fryer.update(currentTime);
            currentTime++;
        }
    }

    /**
     * finds and returns the order with the shortest cooking time from the list of orders.
     * @param orders the list of orders.
     * @return the order with the shortest cooking time.
     */
    private Order findShortestOrder(List<Order> orders) {
        if (orders.isEmpty()) {
            return null;
        }
        
        Order shortestOrder = orders.get(0); //start with the first order as the shortest
        int shortestTime = calculateMinimumCookingTime(shortestOrder);

        for (int i = 1; i < orders.size(); i++) {
            Order order = orders.get(i);
            int cookingTime = calculateMinimumCookingTime(order);
            if (cookingTime < shortestTime || (cookingTime == shortestTime && order.getNum() < shortestOrder.getNum())) {
                shortestOrder = order;
                shortestTime = cookingTime;
            }
        }
        return shortestOrder;
    }

    /**
     * calculates the minimum cooking time required for the given order.
     * this is a hypothetical method and needs to be implemented based on actual cooking times.
     * @param order The order to evaluate.
     * @return The cooking time in minutes.
     */
    private int calculateMinimumCookingTime(Order order) {
        int minTime = Integer.MAX_VALUE;
        if (order.getNpp() > 0) minTime = Math.min(minTime, 25);
        if (order.getNpc() > 0) minTime = Math.min(minTime, 20);
        if (order.getNps() > 0) minTime = Math.min(minTime, 25);
        return minTime;
    }
}
