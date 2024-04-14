package hw3.chess;

import java.util.Scanner;

/**@author Christoforos Kontzias 1134670
 * The main class. How the user interacts with the game
 */
public class ChessTest {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuePlaying = true;

        while (continuePlaying) {
            System.out.println("Enter the type of piece (Pawn, Rook, Knight, Bishop, Queen, King):");
            String pieceType = scanner.next();

            System.out.println("Enter the color of the piece (WHITE, BLACK):");
            String colorInput = scanner.next();
            Color color = Color.valueOf(colorInput.toUpperCase());

            System.out.println("Enter the starting position (e.g., d4):");
            String position = scanner.next();

            Piece piece = createPiece(pieceType, color);
            Square startingPosition;
            try {
                startingPosition = new Square(position);
            } catch (InvalidSquareException e) {
                System.out.println("Invalid starting position. " + e.getMessage());
                continue; 
            }

            System.out.println("Possible moves:");
            try {
                Square[] moves = piece.movesFrom(startingPosition);

                if (moves.length == 0) {
                    System.out.println("No valid moves from this position.");
                } else {
                    for (Square move : moves) {
                        if (move != null) { // ensure the square is valid before attempting to print
                            System.out.println(move);
                        }
                    }
                }
            } catch (InvalidSquareException e) {
                System.out.println("Error calculating moves: " + e.getMessage());
            }

            System.out.println("Do you want to continue? (yes/no):");
            String answer = scanner.next();
            continuePlaying = answer.equalsIgnoreCase("yes");
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    private static Piece createPiece(String pieceType, Color color) {
        switch (pieceType.toLowerCase()) {
            case "pawn":
                return new Pawn(color);
            case "rook":
                return new Rook(color);
            case "knight":
                return new Knight(color);
            case "bishop":
                return new Bishop(color);
            case "queen":
                return new Queen(color);
            case "king":
                return new King(color);
            default:
                throw new IllegalArgumentException("Invalid piece type.");
        }
    }
}
