package hw3.squarelotron;
/**
 * defines the core functionalities that all Squarelotron instances must implement.
 * This interface provides methods for performing various flips and rotations on a
 * Squarelotron as well as standard methods for retrieving the Squarelotrons
 * numeric configuration, comparing Squarelotrons and obtaining a string representation.
 * 
 * @author Christoforos Kontzias
 * @date 02/04/2024
 */
public interface SquarelotronMethods {
	//the javadoc for each method is in the abstract class and the subclasses
    public int[] numbers();
    public Squarelotron upsideDownFlip(String ring);
    public Squarelotron leftRightFlip(String ring);
    public Squarelotron inverseDiagonalFlip(String ring);
    public Squarelotron mainDiagonalFlip(String ring);
    public Squarelotron sideFlip(String side);
    public void rotateRight(int numberOfTurns);
    @Override public boolean equals(Object object);
    @Override public String toString();
    
}