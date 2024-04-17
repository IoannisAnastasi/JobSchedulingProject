/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the grilling area for preparing various types of skewers and pitas.
 * it manages the capacity of the grill and the placement of items on it.
 */
public class Grill {
    private final int capacity; //total capacity of the grill in centimeters
    private int usedCapacity = 0;
    private List<GrillItem> grillItems = new ArrayList<>();

 
    private final int skewerXSpace; //space for pork skewer
    private final int skewerYSpace; //space for chicken skewer
    private final int pitaZSpace;   //space for pita

    /**
     * constructs a Grill object with specified dimensions and capacity.
     *
     * @param capacity The total length of the grill in centimeters.
     * @param skewerXSpace The space occupied by each pork skewer.
     * @param skewerYSpace The space occupied by each chicken skewer.
     * @param pitaZSpace The space occupied by each pita.
     */
    public Grill(int capacity, int skewerXSpace, int skewerYSpace, int pitaZSpace) {
        this.capacity = capacity;
        this.skewerXSpace = skewerXSpace;
        this.skewerYSpace = skewerYSpace;
        this.pitaZSpace = pitaZSpace;
    }

    /**
     * attempts to add an order to the grill based on available capacity.
     *
     * @param order The order to add to the grill.
     * @param currentTime The current simulation time in minutes.
     * @return true if the order fits and is added, false otherwise.
     */
    public boolean addToGrill(Order order, int currentTime) {
        int spaceNeeded = calculateSpaceForOrder(order);
        if (spaceNeeded + usedCapacity <= capacity) {
            GrillItem item = new GrillItem(order, currentTime, spaceNeeded);
            grillItems.add(item);
            usedCapacity += spaceNeeded;
            return true;
        }
        return false;
    }

    /**
     * calculates the space needed for the given order based on its contents.
     *
     * @param order The order to calculate space for.
     * @return The total space needed on the grill for the order.
     */
    private int calculateSpaceForOrder(Order order) {
        int space = order.getNpp() * skewerXSpace +
                    order.getNpc() * skewerYSpace +
                    order.getNps() * pitaZSpace +
                    order.getNpm() * (skewerXSpace + pitaZSpace);
        return space;
    }

    /**
     * processes the grill by updating its state and removing any items that have finished cooking.
     *
     * @param currentTime The current simulation time in minutes used to check if items are done.
     */
    public void processGrill(int currentTime) {
        grillItems.removeIf(item -> item.isDone(currentTime));
        usedCapacity = grillItems.stream().mapToInt(GrillItem::getSpaceUsed).sum();
    }
    public boolean canAddToGrill(Order order) {
        int requiredSpace = calculateSpaceForOrder(order);
        return (usedCapacity + requiredSpace) <= capacity;
    }
    /**
     * inner class to handle grill items. Each item corresponds to an order and tracks its own cooking time and space usage.
     */
    private class GrillItem {
        Order order;
        int startTime;
        int spaceUsed;

        GrillItem(Order order, int startTime, int spaceUsed) {
            this.order = order;
            this.startTime = startTime;
            this.spaceUsed = spaceUsed;
        }

        /**
         * checks if the item is done cooking based on the current time.
         *
         * @param currentTime The current time in minutes.
         * @return true if the item is done cooking, false otherwise.
         */
        boolean isDone(int currentTime) {
            int cookTime = getMaxCookTime(order);
            return currentTime >= (startTime + cookTime);
        }

        /**
         * calculates the maximum cooking time required for the items in the order.
         *
         * @param order The order containing the items.
         * @return The maximum cooking time in minutes.
         */
        private int getMaxCookTime(Order order) {
            int maxTime = 0;
            if (order.getNpp() > 0) {
                maxTime = Math.max(maxTime, 25); // 20-25 minutes for pork
            }
            if (order.getNpc() > 0) {
                maxTime = Math.max(maxTime, 20); // 15-20 minutes for chicken
            }
            if (order.getNps() > 0 || order.getNpm() > 0) {
                maxTime = Math.max(maxTime, 25); // 25 minutes for pita (both seftalia and mix)
            }
            return maxTime;
        }

        /**
         * gets the space used by this grill item on the grill.
         *
         * @return The space used in centimeters.
         */
        public int getSpaceUsed() {
            return spaceUsed;
        }
    }
    /**
     * checks if the grill is currently empty (no items being cooked).
     * @return true if there are no items on the grill, false otherwise.
     */
    public boolean isEmpty() {
        return grillItems.isEmpty();
    }
}

