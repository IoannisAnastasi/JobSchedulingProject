package hw3.chess;
/**@author Christoforos Kontzias 1134670
 * The knight piece class. Includes methods to calculate the possible moves
 */
public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return "N";
    }

    @Override
    public Square[] movesFrom(Square square) {
        char row = square.getRow();
        char col = square.getCol();
        int[][] moveOffsets = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
        Square[] potentialMoves = new Square[8]; 
        int count = 0;

        for (int[] offset : moveOffsets) {
            char newRow = (char) (row + offset[0]);
            char newCol = (char) (col + offset[1]);
            if (newRow >= 'a' && newRow <= 'h' && newCol >= '1' && newCol <= '8') {
                try {
                    potentialMoves[count++] = new Square(newRow, newCol);
                } catch (InvalidSquareException e) {
                  
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