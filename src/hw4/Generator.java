package hw4;

/**
 * This class generates random orders for a restaurant.
 * It inherits from the Order class and generates random quantities of food items and random order times.
 * 
 * @author Ioannis Anastasi ID: 1131712
 * @since 14/04/2024
 */
import java.util.Random;

public class Generator extends Order {
    private int portions; // Number of portions in the order

    /**
     * Default constructor for Generator class.
     * Generates a random number of portions for the order.
     */
    public Generator() {
        super(); // Call the constructor of the superclass (Order)
        portions = generateNumOfPortions();
    }

    /**
     * Generates each portion of the order randomly based on probabilities.
     */
    public void generateEachPortion() {
        for (int i = 0; i < portions; i++) {
            double randomNum = Math.random() * 100;
            if (randomNum >= 0 && randomNum <= 25)
                setNpp((getNpp() + 1)); // Increment pork skewers pittas count
            else if (randomNum > 25 && randomNum <= 50)
                setNpc((getNpc() + 1)); // Increment pitta chicken skewers count
            else if (randomNum > 50 && randomNum <= 75)
                setNps((getNps() + 1)); // Increment sheftalia pittas count
            else
                setNpm((getNpm() + 1)); // Increment mixed pitta count
        }
    }

    /**
     * Generates the number of portions of fries in the order randomly based on probabilities.
     */
    public void generateFries() {
        for (int i = 0; i < portions; i++) {
            double randomNum = Math.random() * 100;
            if (randomNum >= 0 && randomNum <= 60)
                setFries((getFries() + 1)); // Increment fries count
            else if (randomNum > 60 && randomNum <= 95)
                {} // Do nothing, representing a probability of not ordering fries
            else
                setFries((getFries() + 2)); // Increment fries count by 2
        }
    }

    /**
     * Generates a random time of order using Gaussian distribution.
     */
    public void generateTimeOfOrder() {
        Random random = new Random();
        setTorder((int) (60 * random.nextGaussian() + 180)); // Mean 180 minutes, standard deviation 60 minutes
    }

    /**
     * Generates a random requested time for delivery based on the number of portions.
     * If the number of portions is less than or equal to 10, the requested time is between 30 and 60 minutes.
     * Otherwise, it's between 60 and 90 minutes.
     */
    public void generateRequestedTime() {
        if (portions <= 10)
            setTdelReq((int) (Math.random() * 30) + 30 + getTorder()); // Requested time between 30 and 60 minutes
        else
            setTdelReq((int) (Math.random() * 30) + 60 + getTorder()); // Requested time between 60 and 90 minutes
    }

    // Generate the number of portions based on probabilities
    private int generateNumOfPortions() {
        // Define the probabilities for different portions
        double[] probabilities = {20, 35, 10, 20, 15};

        // Total probability sum
        double totalProbability = 100;

        // Choose a random number between 0 and totalProbability
        double randomNum = Math.random() * totalProbability;

        // Find which portion count corresponds to the random number
        double cumulativeProbability = 0;
        int portionCount = 1; // Default to 1 portion
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomNum < cumulativeProbability) {
                portionCount = i + 1; // i + 1 corresponds to portion count
                break;
            }
        }

        // If the selected portion count is in the last category, choose a random count between 5 and 20
        if (portionCount == probabilities.length) {
            portionCount = (int) (Math.random() * 16) + 5; // Random number between 5 and 20
        }

        return portionCount;
    }

    /**
     * Returns a string representation of the Generator object.
     * 
     * @return A string representation of the Generator object.
     */
    public String toString() {
        return super.toString(); // Call the toString() method of the superclass (Order)
    }
}
