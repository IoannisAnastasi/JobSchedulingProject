package hw3.chess;

/**@author Christoforos Kontzias 1134670
 * The rook piece class. Includes methods to calculate the possible moves
 */


public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return "R";
    }

   
    @Override
    public Square[] movesFrom(Square square) {
        char row = square.getRow();
        char col = square.getCol();
        Square[] potentialMoves = new Square[64];
        int count = 0;

        // directions: vertical and horizontal
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] offset : offsets) {
            for (int distance = 1; distance < 8; distance++) {
                char newRow = (char) (row + offset[0] * distance);
                char newCol = (char) (col + offset[1] * distance);
                if (newRow >= 'a' && newRow <= 'h' && newCol >= '1' && newCol <= '8') {
                    potentialMoves[count++] = new Square(newRow, newCol);
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

