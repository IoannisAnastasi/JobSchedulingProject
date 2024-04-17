/**
 * @author Christoforos Kontzias 1134670 (team 40)
 */
package hw4;
import java.util.List;

/**
 * the Scheduler interface defines the structure for scheduling strategies.
 * implementations of this interface will handle the scheduling of orders based on different info.
 */
public interface Scheduler {
    /**
     * schedules the provided list of orders using the given resources.
     * implementations will define the scheduling logic according to specific algorithms.
     * 
     * @param orders the list of orders to be processed.
     * @param grill the grill to be used for processing orders that require grilling.
     * @param fryers the list of fryers available for frying operations.
     */
    public void schedule(List<Order> orders, Grill grill, Fryer fryers);
}