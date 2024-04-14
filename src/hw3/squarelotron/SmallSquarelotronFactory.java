package hw3.squarelotron;
/**
 * FACTORY class for creating instances of SmallSquarelotron. This class implements the
 * SquarelotronFactory interface and specializes in producing 4x4 Squarelotron instances.
 * It serves as part of the Factory Method pattern allowing for polymorphic creation of
 * Squarelotron objects.
 * 
 * @author Christoforos Kontzias
 * @date 02/04/2024
 */
public class SmallSquarelotronFactory implements SquarelotronFactory {
	  /**
     * creates a new SmallSquarelotron (4x4) with the specified configuration. The configuration
     * array is expected to contain exactly 16 elements representing the initial state of the
     * SmallSquarelotron's matrix row by row.
     *
     * @param configuration An array of integers representing the initial layout of the
     *                      SmallSquarelotron. Must contain exactly 16 elements.
     * @return A new SmallSquarelotron instance initialized with the given configuration.
     * @throws IllegalArgumentException If the provided configuration array does not meet the
     *                                  expected size or content requirements.
     */
    @Override
    public Squarelotron makeSquarelotron(int[] configuration) {
        return new SmallSquarelotron(configuration);
    }
}