package hw3.squarelotron;
/**
 * an abstract representation of a Squarelotron puzzle. This class provides the
 * foundational functionality common to all types of Squarelotrons, equality checks and string
 * representation. Also implements common methods.
 * 
 * @author Christoforos Kontzias
 * @date 02/04/2024
 */
public abstract class Squarelotron implements SquarelotronMethods {
	 /**
     * this matrix is the Squarelotron
     */
	protected int[][] squarelotron;
	/**
     * constructs a Squarelotron of the specified size
     * 
     * @param size the size of the Squarelotron 
     */
	protected Squarelotron(int size) {  //gia na to vlepoun mono ta subclasses gia encapsulation
		
        this.squarelotron = new int[size][size]; 
    }
	 /**
     * returns the numbers in the Squarelotron in a 1D array format.
     * 
     * @return A 1D array representing the numbers in the Squarelotron.
     */
	@Override
    public int[] numbers() {
        int size = this.squarelotron.length;
        int[] numbers = new int[size * size];
        for (int i = 0, k = 0; i < size; i++) {
            for (int j = 0; j < size; j++, k++) {
                numbers[k] = this.squarelotron[i][j];
            }
        }
        return numbers;
    }   
	 /**
     * provides a string representation of the Squarelotron, including horizontal
     * borders between rows for clearer visualization.
     * 
     * @return A string representation of the Squarelotron.
     */
    @Override
	public String toString() {
	    String horizontalBorder = "+" + "---+".repeat(squarelotron.length) + "\n";
	    String result = "";

	    for (int i = 0; i < squarelotron.length; i++) {
	        result += horizontalBorder; // Top border of each row
	        for (int j = 0; j < squarelotron[i].length; j++) {
	            result += String.format("|%3d", squarelotron[i][j]); // Cell content with left border
	        }
	        result += "|\n"; // Rightmost border of the row
	    }
	    result += horizontalBorder; // Bottom border of the last row

	    return result;
	}
    /**
     * Rotates the Squarelotron clockwise by a specified number of turns.
     * 
     * @param numberOfTurns The number of 90 degree clockwise rotations to perform.
     */
	  @Override
	  public void rotateRight(int numberOfTurns) {
	        int turns = numberOfTurns % 4;
	        if (turns < 0) {
	            turns += 4; 
	        }
	        for (int i = 0; i < turns; i++) {
	            this.squarelotron = rotate90DegreesClockwise();
	        }
	    }
/*
 * helper method
 */
	    private int[][] rotate90DegreesClockwise() {  //deksiostrofa
	        int size = this.squarelotron.length;
	        int[][] rotated = new int[size][size];

	        for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {
	                rotated[j][size - 1 - i] = this.squarelotron[i][j];
	            }
	        }
	        return rotated;
	    }
	    /**
	     * performs a side flip on the Squarelotron based on the specified side.
	     * creates a deep copy of the Squarelotron and flips either the top, bottom,
	     * left, or right side, as specified.
	     * 
	     * @param side The side to flip ("left", "right", "top", or "bottom").
	     * @return A new Squarelotron instance with the specified side flipped.
	     * @throws IllegalArgumentException if an invalid side is specified.
	     */
	    @Override
	    public Squarelotron sideFlip(String side) {
	        Squarelotron flipped = this.deepCopy();
	        
	        switch (side) {
	            case "left":
	                for (int i = 0; i < flipped.squarelotron.length; i++) {
	                    int temp = flipped.squarelotron[i][0];
	                    flipped.squarelotron[i][0] = flipped.squarelotron[i][1];
	                    flipped.squarelotron[i][1] = temp;
	                }
	                break;
	            case "right":
	                int lastIndex = flipped.squarelotron[0].length - 1;
	                for (int i = 0; i < flipped.squarelotron.length; i++) {
	                    int temp = flipped.squarelotron[i][lastIndex];
	                    flipped.squarelotron[i][lastIndex] = flipped.squarelotron[i][lastIndex - 1];
	                    flipped.squarelotron[i][lastIndex - 1] = temp;
	                }
	                break;
	            case "top":
	                for (int j = 0; j < flipped.squarelotron[0].length; j++) {
	                    int temp = flipped.squarelotron[0][j];
	                    flipped.squarelotron[0][j] = flipped.squarelotron[1][j];
	                    flipped.squarelotron[1][j] = temp;
	                }
	                break;
	            case "bottom":
	                int lastRowIndex = flipped.squarelotron.length - 1;
	                for (int j = 0; j < flipped.squarelotron[0].length; j++) {
	                    int temp = flipped.squarelotron[lastRowIndex][j];
	                    flipped.squarelotron[lastRowIndex][j] = flipped.squarelotron[lastRowIndex - 1][j];
	                    flipped.squarelotron[lastRowIndex - 1][j] = temp;
	                }
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid side: " + side);
	        }
	        
	        return flipped;
	    }
	    /**
	     * compares this Squarelotron with another object for equality. Squarelotrons
	     * are considered equal if they are of the same size and all their corresponding
	     * cells match in one of their rotational states.
	     * 
	     * @param object The object to compare with this Squarelotron.
	     * @return true if the objects are equal and false otherwise.
	     */
	    @Override
	    public boolean equals(Object object) {
	        if (this == object) return true;
	        if (!(object instanceof Squarelotron)) return false;

	        Squarelotron that = (Squarelotron) object;
	        if (this.squarelotron.length != that.squarelotron.length) return false;

	        Squarelotron thatCopy = that.deepCopy(); 

	        for (int rotation = 0; rotation < 4; rotation++) {
	            boolean areEqual = true;
	            for (int i = 0; i < this.squarelotron.length && areEqual; i++) {
	                for (int j = 0; j < this.squarelotron[i].length; j++) {
	                    if (this.squarelotron[i][j] != thatCopy.squarelotron[i][j]) {
	                        areEqual = false;       //
	                        break;
	                    }
	                }
	            }
	            if (areEqual) {
	                return true; //the squarelotrons are equal in the current rotation state
	            }
	            thatCopy.rotateRight(1); //prepare for the next comparison by rotating
	        }

	        return false; //if the squarelotrons are not equal in any rotation state
	    }
	    /**
	     * creates a deep copy of this Squarelotron. This method is used internally
	     * for operations that require a new instance of a Squarelotron without
	     * modifying the original.
	     * 
	     * @return A new Squarelotron instance that is a deep copy of this Squarelotron.
	     */
	    protected Squarelotron deepCopy() {
	        Squarelotron copy = this.copy();
	        for (int i = 0; i < this.squarelotron.length; i++) {
	            for (int j = 0; j < this.squarelotron[i].length; j++) {
	                copy.squarelotron[i][j] = this.squarelotron[i][j];
	            }
	        }
	        return copy;
	    }
	    /**
	     * FACTORY method for creating a copy of this Squarelotron. This method is
	     * meant to be implemented by subclasses to return a new instance of the
	     * same type and state.
	     * 
	     * @return A new instance of a Squarelotron of the same type and configuration.
	     */
	    protected abstract Squarelotron copy(); 
	}

