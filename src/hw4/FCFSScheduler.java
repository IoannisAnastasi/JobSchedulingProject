/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.List;


/**
 * scheduler implementation that processes orders in the order they are received.
 */
public class FCFSScheduler implements Scheduler {

    /**
     * schedules and processes orders using a first-come, first-served approach.
     * ensures that all items in an order are placed at the same time if sufficient space is available.
     * @param orders the list of orders to process.
     * @param grill the grill to cook skewers and pitas.
     * @param fryer the fryer to cook fries.
     */
    @Override
    public void schedule(List<Order> orders, Grill grill, Fryer fryer) {
        int currentTime = 0;//simulation start time

        for (Order order : orders) {
            //check and wait until there is enough space for both the grill and fryer items of the order
            while (!grill.canAddToGrill(order) || !fryer.canAddFries(order)) {
                grill.processGrill(currentTime);
                fryer.update(currentTime);
                currentTime++;//increment time as we wait for space
            }

          
            grill.addToGrill(order, currentTime);//add items to grill and fryer
            if (order.getFries() > 0) {
                fryer.addFries(order, currentTime);
            }
        }

      
        while (!grill.isEmpty() || !fryer.isEmpty()) {//ensure all items finish cooking
            grill.processGrill(currentTime);
            fryer.update(currentTime);
            currentTime++;
        }
    }
}