package hw3.squarelotron;
/**
 * defines a factory interface for creating Squarelotron objects. This interface
 * provides a method for creating a Squarelotron based on a specific configuration
 * allowing for flexible implementations that can create different types of Squarelotrons.
 * Implementations of this interface can create Squarelotrons of various sizes
 * such as SmallSquarelotron or LargeSquarelotron based on the provided configuration.
 * 
 * @author Christoforos Kontzias
 * @date 02/04/2024
 */
public interface SquarelotronFactory {
	/**
	 * same explanation
	 * @param configuration
	 * @return
	 */
    Squarelotron makeSquarelotron(int[] configuration);
}
