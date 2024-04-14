package hw3.chess;
/**@author Christoforos Kontzias 1134670
 * The king piece class. Includes methods to calculate the possible moves
 */
public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return "K";
    }

    @Override
    public Square[] movesFrom(Square square) {
        char row = square.getRow();
        char col = square.getCol();
        Square[] potentialMoves = new Square[8]; 
        int count = 0;


        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rowOffsets.length; i++) {
            char newRow = (char) (row + rowOffsets[i]);
            char newCol = (char) (col + colOffsets[i]);
            if (newRow >= 'a' && newRow <= 'h' && newCol >= '1' && newCol <= '8') {
                try {
                    potentialMoves[count++] = new Square(newRow, newCol);
                } catch (InvalidSquareException e) {
                    //kanonika den tha erthei pote kati edo
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