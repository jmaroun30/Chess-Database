/**
 * Abstract class that defines the superclass of a chess piece
 * @author gmaroun3
 *
 * @version 1.0
 */
public abstract class Piece {
    private Color color;

    /**
     * Consttructor for a piece
     * @param color type of color passed in
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * predefined method to get name of piece in subclasses
     * @return the alg name value in the subclass
     */
    public abstract String algebraicName();

    /**
     * predefined method to get FEN name of piece in subclasses
     * @return the alg name value in the subclass
     */
    public abstract String fenName();

    /**
     * predefined method for retrieving color of the piece
     * @return color type
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * predefined method for creating a square array of possible moves
     * @param square one piece on a board
     * @return Square[]
     */
    public abstract Square[] movesFrom(Square square);
}