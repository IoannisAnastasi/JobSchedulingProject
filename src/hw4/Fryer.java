/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.ArrayList;
import java.util.List;

/**
 * manages the fryer operations for preparing fries. It tracks the fryer's capacity and 
 * the status of each portion of fries being fried.
 */
public class Fryer {
    private final int capacity; // Total capacity in terms of number of fries portions
    private List<FryerItem> fryerItems; // List to manage frying portions
    private int usedCapacity = 0;
    /**
     * constructs a Fryer with specified capacity for fries portions.
     * @param capacity the maximum number of fries portions the fryer can handle simultaneously.
     */
    public Fryer(int capacity) {
        this.capacity = capacity;
        this.fryerItems = new ArrayList<>();
    }

    /**
     * attempts to add fries from an order to the fryer if there is sufficient capacity.
     * @param order the order containing fries portions to be added.
     * @param currentTime the current simulation time in minutes.
     * @return true if the fries fit and are added, false otherwise.
     */
    public boolean addFries(Order order, int currentTime) {
        if (canAddFries(order)) {
            int friesCount = order.getFries();
            for (int i = 0; i < friesCount; i++) {
                fryerItems.add(new FryerItem(currentTime)); // Adding each portion as a separate item
            }
            usedCapacity += friesCount;
            return true;
        }
        return false;
    }
    /**
     * checks if the fryer is currently empty (no fries being cooked).
     * @return true if there are no fries in the fryer, false otherwise.
     */
    public boolean isEmpty() {
        return fryerItems.isEmpty();
    }
    /**
     * processes the fryer by updating its state, removing any fries that have finished frying.
     * @param currentTime the current simulation time in minutes to determine if fries are done.
     */
    public void update(int currentTime) {
        List<FryerItem> completedItems = new ArrayList<>();
        for (FryerItem item : fryerItems) {
            if (item.isDone(currentTime)) {
                completedItems.add(item);
            }
        }
        fryerItems.removeAll(completedItems);
        usedCapacity = fryerItems.size(); // update the used capacity after removing done items
    }

    /**
     * calculates the current number of fries portions in the fryer.
     * @return the number of currently frying fries portions.
     */
    private int getCurrentFriesCount() {
        return fryerItems.size();
    }
 public boolean canAddFries(Order order) {
            int friesNeeded = order.getFries();
            return (usedCapacity + friesNeeded) <= capacity;
        }
    /**
     * represents a single portion of fries being fried.
     */
    private class FryerItem {
        private int startTime;

        /**
         * constructs a FryerItem with a start time.
         * @param startTime the time the fries portion started frying.
         */
        public FryerItem(int startTime) {
            this.startTime = startTime;
        }
       
        /**
         * determines if the fries portion is done based on the frying time.
         * @param currentTime the current time to check against the start time.
         * @return true if the fries are done frying, false otherwise.
         */
        public boolean isDone(int currentTime) {
            return currentTime >= startTime + 20; 
        }
    }
}
