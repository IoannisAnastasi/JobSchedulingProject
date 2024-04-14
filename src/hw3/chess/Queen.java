package hw3.chess;
/**@author Christoforos Kontzias 1134670
 * The queen piece class. Includes methods to calculate the possible moves
 */
public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return "Q";
    }

    @Override
    public Square[] movesFrom(Square square) {
        char row = square.getRow();
        char col = square.getCol();
        Square[] potentialMoves = new Square[64]; 
       int count = 0;

        
        int[] rowOffsets = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] colOffsets = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int direction = 0; direction < rowOffsets.length; direction++) {
            for (int distance = 1; distance < 8; distance++) { // a queen can move up to 7 squares in any direction
                char newRow = (char) (row + rowOffsets[direction] * distance);
                char newCol = (char) (col + colOffsets[direction] * distance);
                if (newRow >= 'a' && newRow <= 'h' && newCol >= '1' && newCol <= '8') {
                    try {
                        potentialMoves[count++] = new Square(newRow, newCol);
                    } catch (InvalidSquareException e) {
                    //kanonika den prepei na erthei kati edo
                    }
                }
            }
        }

        Square[] validMoves = new Square[count];
        for (int i = 0; i < count; i++) {
            validMoves[i] = potentialMoves[i];
        }

        return validMoves;
    }
}
