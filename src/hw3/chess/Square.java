package hw3.chess;

/**@author Christoforos Kontzias 1134670
 * Represents a square on a chessboard with a specific position identified by row and column.
 */
public class Square {
    private final char row;
    private final char col;

    /**
     * constructs a Square at the specified row and column.
     * throws InvalidSquareException if the row or column is out of bounds ('a' to 'h' for rows and '1' to '8' for columns).
     *
     * @param row The row of the square ('a' to 'h').
     * @param col The column of the square ('1' to '8').
     * @throws InvalidSquareException If the row or column is invalid.
     */
    public Square(char row, char col) {
        if (row < 'a' || row > 'h' || col < '1' || col > '8') {
            throw new InvalidSquareException("Invalid square: " + row + col);
        }
        this.row = row;
        this.col = col;
    }

    /**
     * constructs a Square using a string representation, such as "a1".
     * throws InvalidSquareException if the string does not represent a valid square.
     *
     * @param name The name of the square, in the format of one letter (a-h) followed by one digit (1-8).
     * @throws InvalidSquareException If the name does not represent a valid square.
     */
    public Square(String name) {
        if (name == null || name.length() != 2) {
            throw new InvalidSquareException("Invalid square name: " + name);
        }
        this.row = name.charAt(0);
        this.col = name.charAt(1);

        if (row < 'a' || row > 'h' || col < '1' || col > '8') {
            throw new InvalidSquareException("Invalid square: " + name);
        }

    }

    /**
     * returns the string representation of this square.
     *
     * @return The name of the square.
     */
    @Override
    public String toString() {
        return "" + row + col;
    }

    /**
     * indicates whether some other object is equal to this Square.
     *
     * @param object the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Square)) return false;
        
        Square other = (Square) obj;
        return this.row == other.row && this.col == other.col;
    }
    public char getRow() {
    	return this.row;
    }
    public char getCol() {
    	return this.col;
    }
}