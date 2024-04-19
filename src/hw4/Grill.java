/**
 * @author Christoforos Kontzias 1134670
 */
package hw4;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the grilling area for preparing various types of skewers and pitas.
 * it manages the capacity of the grill and the placement of items on it, including the time for the charcoal to burn.
 */
public class Grill {
    private final int capacity;//total capacity of the grill in centimeters
    private int usedCapacity = 0;
    private List<GrillItem> grillItems = new ArrayList<>();

    private final int charcoalTime;//time for the charcoal to burn
    private final int skewerXSpace;//space for pork skewer
    private final int skewerYSpace;//space for chicken skewer
    private final int pitaZSpace;//space for pita
    private int operationalTime;//the time when the grill will be ready to use

    /**
     * constructs a Grill object with specified dimensions and capacity.
     *
     * @param capacity The total length of the grill in centimeters.
     * @param skewerXSpace The space occupied by each pork skewer.
     * @param skewerYSpace The space occupied by each chicken skewer.
     * @param pitaZSpace The space occupied by each pita.
     * @param charcoalTime The time needed for the charcoal to be ready, starting from 17:30.
     */
    public Grill(int capacity, int skewerXSpace, int skewerYSpace, int pitaZSpace, int charcoalTime) {
        this.capacity = capacity;
        this.skewerXSpace = skewerXSpace;
        this.skewerYSpace = skewerYSpace;
        this.pitaZSpace = pitaZSpace;
        this.charcoalTime = charcoalTime;
        this.operationalTime = calculateOperationalTime();
    }

    /**
     * calculates the operational time of the grill based on the charcoal time and order acceptance start time.
     * charcoal starts at 17:30, if preparation time exceeds 30 minutes, it affects the operational time.
     *
     * @return The operational time in minutes after 17:30.
     */
    private int calculateOperationalTime() {
        int baseTime = 30; //30 minutes from 17:30 to 18:00 when orders start being accepted
        if (charcoalTime > 30) {
            return baseTime + (charcoalTime - 30); //only the extra time beyond 30 minutes adds to the base time
        }
        return baseTime; //grill is operationally ready by 18:00 if charcoal time is <= 30 minutes
    }
    public int getOperationalTime() {
    	return this.operationalTime;
    }
    public boolean addToGrill(Order order, int currentTime) {
        if (currentTime < operationalTime) {
            return false; //grill is not ready yet
        }

        int spaceNeeded = calculateSpaceForOrder(order);
        if (spaceNeeded + usedCapacity <= capacity) {
            GrillItem item = new GrillItem(order, currentTime, spaceNeeded);
            grillItems.add(item);
            usedCapacity += spaceNeeded;
            return true;
        }
        return false;
    }

    private int calculateSpaceForOrder(Order order) {
        int space = order.getNpp() * skewerXSpace +
                    order.getNpc() * skewerYSpace +
                    order.getNps() * pitaZSpace +
                    order.getNpm() * (skewerXSpace + pitaZSpace);
        return space;
    }

    public void processGrill(int currentTime) {
        grillItems.removeIf(item -> item.isDone(currentTime));
        usedCapacity = grillItems.stream().mapToInt(GrillItem::getSpaceUsed).sum();
    }

    public boolean canAddToGrill(Order order) {
        int requiredSpace = calculateSpaceForOrder(order);
        return (usedCapacity + requiredSpace) <= capacity;
    }

    public boolean isEmpty() {
        return grillItems.isEmpty();
    }
/**
 * Inner class which handles GrillItem. I decided to use an inner class because Grill items are solely need in the grill
 */
    /**
     * calculates the maximum cooking time required for the items in the order
     * @param order The order containing the items.
     * @return the maximum cooking time in minutes.
     */
    public int getMaxCookTime(Order order) {
        int maxTime = 0;

        //pork skewer cooking time
        if (order.getNpp() > 0) {
            int porkTime = 20 + (int)(Math.random() * (25 - 20 + 1)); //random time between 20 and 25 minutes
            maxTime = Math.max(maxTime, porkTime);
        }

        //chicken skewer cooking time
        if (order.getNpc() > 0) {
            int chickenTime = 15 + (int)(Math.random() * (20 - 15 + 1)); //random time between 15 and 20 minutes
            maxTime = Math.max(maxTime, chickenTime);
        }

        //sheftalia cooking time
        if (order.getNps() > 0) {
            int seftaliaTime = 25; //constant 25 minutes, as seftalia has no range
            maxTime = Math.max(maxTime, seftaliaTime);
        }

        //mix pitta cooking time considering the longest of the included items
        if (order.getNpm() > 0) {
            int mixTime = Math.max(
                20 + (int)(Math.random() * (25 - 20 + 1)), // Random pork time
                25  //constant seftalia time
            );
            maxTime = Math.max(maxTime, mixTime);
        }

        //pitta bread cooking time
        if (order.getNpp() > 0 || order.getNpc() > 0 || order.getNps() > 0 || order.getNpm() > 0) {
            int pitaTime = 5; // Constant time for pita bread
            maxTime = Math.max(maxTime, pitaTime);
        }

        return maxTime;
    }

    private class GrillItem {
        Order order;
        int startTime;
        int spaceUsed;

        GrillItem(Order order, int startTime, int spaceUsed) {
            this.order = order;
            this.startTime = startTime;
            this.spaceUsed = spaceUsed;
        }

        boolean isDone(int currentTime) {
            int cookTime = getMaxCookTime(order);
            return currentTime >= (startTime + cookTime);
        }

        /**
         * calculates the maximum cooking time required for the items in the order
         * @param order The order containing the items.
         * @return the maximum cooking time in minutes.
         */
        public int getMaxCookTime(Order order) {
            int maxTime = 0;

            //pork skewer cooking time
            if (order.getNpp() > 0) {
                int porkTime = 20 + (int)(Math.random() * (25 - 20 + 1)); // Random time between 20 and 25 minutes
                maxTime = Math.max(maxTime, porkTime);
            }

            //chicken skewer cooking time
            if (order.getNpc() > 0) {
                int chickenTime = 15 + (int)(Math.random() * (20 - 15 + 1)); // Random time between 15 and 20 minutes
                maxTime = Math.max(maxTime, chickenTime);
            }

            //sheftalia cooking time
            if (order.getNps() > 0) {
                int seftaliaTime = 25; // Constant 25 minutes, as seftalia has no range
                maxTime = Math.max(maxTime, seftaliaTime);
            }

            //mix pitta cooking time considering the longest of the included items
            if (order.getNpm() > 0) {
                int mixTime = Math.max(
                    20 + (int)(Math.random() * (25 - 20 + 1)), // Random pork time
                    25  // Constant seftalia time
                );
                maxTime = Math.max(maxTime, mixTime);
            }

            //pitta bread cooking time
            if (order.getNpp() > 0 || order.getNpc() > 0 || order.getNps() > 0 || order.getNpm() > 0) {
                int pitaTime = 5; // Constant time for pita bread
                maxTime = Math.max(maxTime, pitaTime);
            }

            return maxTime;
        }

        public int getSpaceUsed() {
            return spaceUsed;
        }
    }
}
