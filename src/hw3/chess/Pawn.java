package hw3.chess;
/**
 * NOT WORKING. I CANT FIND THE ISSUE.
 * PROXEIRO CLASS , TO KANONIKO STA COMMENTS
 */

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return ""; 
    }

    @Override
    public Square[] movesFrom(Square square) {
        char row = square.getRow();
        char col = square.getCol();
        Square[] potentialMoves = new Square[1]; 
 // determine move direction
        int direction = (getColor() == Color.WHITE) ? 1 : -1;

   // calculate new row  ISSUE HERE
        char newRow = (char) (row + direction);

      
       // if (isValidSquare(newRow, col)) {
         
        potentialMoves[0] = new Square(newRow, col);
     return potentialMoves;    }

       
    

    
    private boolean isValidSquare(char row, char col) {
        return row >= '1' && row <= '8' && col >= 'a' && col <= 'h';
    }
}
/**
 * 
 
public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return ""; 
    }

    @Override
    public Square[] movesFrom(Square square) {
        System.out.println("----- Starting movesFrom -----"); 
        char row = square.getRow();
        char col = square.getCol();
        Square[] potentialMoves = new Square[4]; 
        int moveCount = 0; 

        System.out.println("Starting Position: " + row + col); 
        System.out.println("Color: " + getColor());  

        
        int direction = (getColor() == Color.WHITE) ? 1 : -1;
        System.out.println("Direction: " + direction); 

        
        char newRow = (char) (row + direction);
        System.out.println("Calculated Forward newRow: " + newRow); 
        if (isValidSquare(newRow, col)) {
            potentialMoves[moveCount++] = new Square(newRow, col);
        }

        // initial two-square move
        if ((getColor() == Color.WHITE && row == '2') || (getColor() == Color.BLACK && row == '7')) {
            newRow = (char) (row + (2 * direction));
            if (isValidSquare(newRow, col)) {
                potentialMoves[moveCount++] = new Square(newRow, col);
            }
        }

        // diagonal captures 
        for (int colOffset : new int[] {-1, 1}) {
            char captureCol = (char) (col + colOffset);
            newRow = (char) (row + direction); // Only move one square forward for captures 
            if (isValidSquare(newRow, captureCol)) {
                potentialMoves[moveCount++] = new Square(newRow, captureCol); 
            }
        }

        // promotion moves (if reaching the last rank)
        if ((getColor() == Color.WHITE && row == '7') || (getColor() == Color.BLACK && row == '2')) {
            for (char promotionCol : new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
                potentialMoves[moveCount++] = new Square('8', promotionCol); 
            }
        }

       
        Square[] validMoves = new Square[moveCount];
        System.arraycopy(potentialMoves, 0, validMoves, 0, moveCount);
        return validMoves;
    }

    private boolean isValidSquare(char row, char col) {
        System.out.println("Checking Square: " + row + col);
        return row >= '1' && row <= '8' && col >= 'a' && col <= 'h';
    }
}
*/