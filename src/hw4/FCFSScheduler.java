/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.List;
/**
 * implementation of the FCFSScheduler algorithm.
 */
public class FCFSScheduler implements Scheduler {
	/**
	 * schedules and processes orders using a First-Come First-Served (FCFS) approach with resource availability checks
	 * this method processes orders by sequentially scheduling them based on their order in the list, 
	 * but ensures that each order is only executed when there is sufficient capacity on both the grill and the fryer.
	 * it waits for space to become available if necessary.
	 *
	 * @param orders The list of customer orders to process. Each order contains items that may require grilling or frying.
	 * @param grill  The grill is used for cooking items that require grilling. It checks for space and processes items accordingly.
	 * @param fryer  The fryer is for fries
	 */
    @Override
    public void schedule(List<Order> orders, Grill grill, Fryer fryer) {
        int currentTime = 0; //simulation start time
        int[] endTimes = new int[orders.size()]; //temporarily store end times for each order

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            //check and wait until there is enough space for both the grill and fryer items of the order
            while (!grill.canAddToGrill(order) || !fryer.canAddFries(order)) {
                grill.processGrill(currentTime);
                fryer.update(currentTime);
                currentTime++; //increment time as we wait for space
            }

            grill.addToGrill(order, currentTime); //add items to grill
            if (order.getFries() > 0) {
                fryer.addFries(order, currentTime);
            }

            //assuming the cooking time for the most time-consuming item dictates the order completion
            int maxCookingTime = Math.max(grill.getMaxCookTime(order), 20); //20 minutes for fries if they exist
            endTimes[i] = currentTime + maxCookingTime; //calculate end time for this order
        }

        //ensure all items finish cooking
        while (!grill.isEmpty() || !fryer.isEmpty()) {
            grill.processGrill(currentTime);
            fryer.update(currentTime);
            currentTime++;
        }

        //output results or further processing can use endTimes here
        OrderDelivery.writeResults(orders, endTimes, "deliveries.txt"); 
    }
}
