package hw3.squarelotron;

public class SquarelotronFactoryUtil {
	  /**
     * factory instance for creating 4x4 Squarelotrons.
     */
    private static final SquarelotronFactory smallFactory = new SmallSquarelotronFactory();
    
    /**
     * factory instance for creating 5x5 Squarelotrons.
     */
    private static final SquarelotronFactory largeFactory = new LargeSquarelotronFactory();

    /**
     * creates a Squarelotron (either 4x4 or 5x5) based on the given configuration.
     * The method determines the appropriate Squarelotron size by the configuration length
     * and delegates the creation to the corresponding factory. It also validates that all
     * numbers in the configuration are within the allowed range (0-99).
     *
     * @param configuration An array of integers representing the initial state of the Squarelotron.
     *                      The length of the array must be either 16 (for a 4x4 Squarelotron) or
     *                      25 (for a 5x5 Squarelotron).
     * @return A Squarelotron object initialized with the given configuration.
     * @throws IllegalArgumentException If the configuration does not meet the requirements:
     *                                  it must have a length of 16 or 25, and all elements must
     *                                  be between 0 and 99 inclusive.
     */
    public static Squarelotron makeSquarelotron(int[] configuration) throws IllegalArgumentException {
        for (int num : configuration) {
            if (num < 0 || num > 99) {
                throw new IllegalArgumentException("All numbers must be non-negative and less than or equal to 99.");
            }
        }

        SquarelotronFactory factory;
        if (configuration.length == 16) {
            factory = smallFactory;
        } else if (configuration.length == 25) {
            factory = largeFactory;
        } else {
            throw new IllegalArgumentException("Configuration array must be of length 16 or 25.");
        }
        
        return factory.makeSquarelotron(configuration);
    } 
}