package hw3.squarelotron;
/**
 * Represents a 4x4 Squarelotron puzzle. A Squarelotron consists of a matrix of numbers
 * which can be flipped in various ways. This class provides methods to perform different
 * flips according to the rules.
 * 
 * @author Christoforos Kontzias 1134670
 * @date 02/04/2024
 */
  
public class SmallSquarelotron extends Squarelotron {
	/**
	 * constructs a SmallSquarelotron with the specified configuration
	 *
	 * @param configuration An array of integers representing the initial state of the Squarelotron
	 */
    public SmallSquarelotron(int[] configuration) {
        super(4); // initializes the squarelotron with a 4x4 matrix
        fillSquarelotron(configuration);
    }
    
    @Override
    public Squarelotron upsideDownFlip(String ring) {
        SmallSquarelotron flipped = new SmallSquarelotron(this.numbers());
        if ("outer".equals(ring)) {
            for (int j = 0; j < flipped.squarelotron.length; j++) {
                int temp = flipped.squarelotron[0][j];
                flipped.squarelotron[0][j] = flipped.squarelotron[flipped.squarelotron.length - 1][j];
                flipped.squarelotron[flipped.squarelotron.length - 1][j] = temp;
            }
            int temp = flipped.squarelotron[1][0];
            flipped.squarelotron[1][0] = flipped.squarelotron[2][0];
            flipped.squarelotron[2][0] = temp;
            
            temp = flipped.squarelotron[1][flipped.squarelotron.length - 1];
            flipped.squarelotron[1][flipped.squarelotron.length - 1] = flipped.squarelotron[2][flipped.squarelotron.length - 1];
            flipped.squarelotron[2][flipped.squarelotron.length - 1] = temp;
        }
        else if ("inner".equals(ring)) {
            int temp = flipped.squarelotron[2][2];
            flipped.squarelotron[2][2] = flipped.squarelotron[1][2];
            flipped.squarelotron[1][2] = temp;
            
            temp = flipped.squarelotron[2][1];
            flipped.squarelotron[2][1] = flipped.squarelotron[1][1];
            flipped.squarelotron[1][1] = temp;
        }
        return flipped;
    }
    /**
     * Performs a left to right flip of the specified ring
     *
     * @param ring Specifies which ring to flip can be "outer" for the outermost ring or "inner"
     *             for the second ring
     * @return a new Squarelotron instance with the specified ring flipped left to right
     */
    @Override
    public Squarelotron leftRightFlip(String ring) {
        SmallSquarelotron result = (SmallSquarelotron) this.deepCopy();
        if ("outer".equals(ring)) {
            for (int i = 0; i < result.squarelotron.length; i++) {
                int temp = result.squarelotron[i][0];
                result.squarelotron[i][0] = result.squarelotron[i][3];
                result.squarelotron[i][3] = temp;

                temp = result.squarelotron[i][1];
                result.squarelotron[i][1] = result.squarelotron[i][2];
                result.squarelotron[i][2] = temp;
            }
        } else if ("inner".equals(ring)) {
            for (int i = 1; i <= 2; i++) {
                int temp = result.squarelotron[i][1];
                result.squarelotron[i][1] = result.squarelotron[i][2];
                result.squarelotron[i][2] = temp;
            }
        }
        return result;
    }
    /**
     * Performs an inverse diagonal flip of the specified ring.
     *
     * @param ring Specifies which ring to flip; can be "outer" for the outermost ring or "inner"
     *             for the second ring.
     * @return a new Squarelotron instance with the specified ring flipped along its inverse diagonal.
     */
    @Override
    public Squarelotron inverseDiagonalFlip(String ring) {
        SmallSquarelotron flipped = new SmallSquarelotron(this.numbers());
        if ("inner".equals(ring)) {
            int temp = flipped.squarelotron[1][1];
            flipped.squarelotron[1][1] = flipped.squarelotron[2][2];
            flipped.squarelotron[2][2] = temp;
        }
        return flipped;
    }
    /**
     * Performs a main diagonal flip of the specified ring
     *
     * @param ring Specifies which ring to flip; can be "outer" for the outermost ring or "inner"
     *             for the second ring
     * @return A new Squarelotron instance with the specified ring flipped through its main diagonal
     */

    @Override
    public Squarelotron mainDiagonalFlip(String ring) {
        SmallSquarelotron result = (SmallSquarelotron) this.deepCopy();

        if ("outer".equals(ring)) {
            for (int i = 0; i < result.squarelotron.length - 1; i++) {
                for (int j = i + 1; j < result.squarelotron.length; j++) {
                    if (i == 1 && j == 2) continue;
                    int temp = result.squarelotron[i][j];
                    result.squarelotron[i][j] = result.squarelotron[j][i];
                    result.squarelotron[j][i] = temp;
                }
            }
        } else if ("inner".equals(ring)) {
      //edo den katalaveno an xreiazete
        }

        return result;
    }
    /**
     * fills the Squarelotron with the given configuration
     * Gives IllegalArgumentException if the configuration does not match the expected size
     *
     * @param array An array of integers representing the configuration to fill the Squarelotron.
     */
    private void fillSquarelotron(int[] array) {
    	 if (array.length != squarelotron.length * squarelotron[0].length) {
    	        throw new IllegalArgumentException("The configuration array length must be "   + squarelotron.length * squarelotron[0].length);
    	        }
    	 
        for (int i = 0; i < squarelotron.length; i++) {
            for (int j = 0; j < squarelotron[i].length; j++) {
            	squarelotron[i][j] = array[i * squarelotron[i].length + j];
            }
        }
    }
    /**
     * creates and returns a deep copy of this SmallSquarelotron instance. The copy
     * contains the same configuration of numbers as this instance but is a completely
     * separate object. Any changes to this will not affect the original
     * LargeSquarelotron
     *
     * @return A new SmallSquarelotron instance that is a deep copy of this LargeSquarelotron.
     */
    protected Squarelotron copy() {
        int[] configCopy = new int[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                configCopy[i * 4 + j] = this.squarelotron[i][j];
            }
        }
        return new SmallSquarelotron(configCopy);
    }
    
}