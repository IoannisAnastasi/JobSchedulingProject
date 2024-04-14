package hw3.chess;
/**@author Christoforos Kontzias 1134670
 * The abstract class Piece which provides the common methods that have to implemented
 */
public abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public abstract String algebraicName();

    public abstract Square[] movesFrom(Square square);
}
