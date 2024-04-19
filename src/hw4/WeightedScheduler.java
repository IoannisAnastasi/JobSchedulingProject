package hw4;


import java.util.List;
import java.util.Comparator;
/**
 * this class implements a scheduling system that prioritizes orders based on a calculated weight,
 * which considers both the execution time of the order and its urgency based on the delivery requirement time.
 */
public class WeightedScheduler implements Scheduler {
	/**
     * Schedules and processes orders by sorting them according to their calculated weight and executing them
     * as resources become available.
     *
     * @param orders the list of orders to be processed.
     * @param grill  the grill to cook certain items that require grilling.
     * @param fryer  the fryer to cook items that require frying.
     */
    @Override
    public void schedule(List<Order> orders, Grill grill, Fryer fryer) {
        int currentTime = 0;  //start from time 0
        int[] endTimes = new int[orders.size()];  //store end times for each order

        for (int i = 0; i < orders.size() - 1; i++) {
            for (int j = 0; j < orders.size() - i - 1; j++) {
                if (calculateWeight(orders.get(j), currentTime) < calculateWeight(orders.get(j + 1), currentTime)) {
                    Order temp = orders.get(j);
                    orders.set(j, orders.get(j + 1));
                    orders.set(j + 1, temp);
                }
            }
        }

        for (Order order : orders) {
            if (currentTime < order.getTorder()) {
                currentTime = order.getTorder(); 
            }
            if (grill.canAddToGrill(order) && fryer.canAddFries(order)) {
                grill.addToGrill(order, currentTime);
                fryer.addFries(order, currentTime);
                int executionTime = estimateExecutionTime(order);
                endTimes[orders.indexOf(order)] = currentTime + executionTime;  
                currentTime += executionTime; 
            }
        }

        ///sort orders by order number before outputting to the file
        orders.sort(Comparator.comparingInt(Order::getNum));

       
        OrderDelivery.writeResults(orders, endTimes, "deliveries.txt");
    }
    /**
     * calculates the weight of an order based on its urgency and the current time.
     * orders closer to their delivery time or past it get a lower weight to prioritize more urgent orders.
     *
     * @param order       the order to calculate the weight for.
     * @param currentTime the current time in the scheduling simulation.
     * @return the calculated weight of the order.
     */
    private double calculateWeight(Order order, int currentTime) {
        int executionTime = estimateExecutionTime(order);
        if (currentTime >= order.getTdelReq()) {
            return 0; 
        }
        return executionTime / (double) (executionTime - (order.getTdelReq() - currentTime));
    }

    private int estimateExecutionTime(Order order) {
        int time = 0;
        if (order.getNpp() > 0) {
            int porkTime = 20 + (int)(Math.random() * (25 - 20 + 1));
            porkTime *= 2;
            time = Math.max(time, porkTime);
        }
        if (order.getNpc() > 0) {
            int chickenTime = 15 + (int)(Math.random() * (20 - 15 + 1));
            chickenTime *= 2;
            time = Math.max(time, chickenTime);
        }
        if (order.getNps() > 0) {
            int seftaliaTime = 25 * 2;
            time = Math.max(time, seftaliaTime);
        }
        if (order.getNpm() > 0) {
            int mixTime = Math.max(20 + (int)(Math.random() * (25 - 20 + 1)), 25);
            mixTime *= 2;
            time = Math.max(time, mixTime);
        }
        if (order.getFries() > 0) {
            int friesTime = 20 * order.getFries();
            time = Math.max(time, friesTime);
        }
        return time;
    }

}

    
    