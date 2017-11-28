import java.util.Optional;

/**
 * Ply class that is one half of a move
 * @author gmaroun3
 *
 * @version 1.0
 */
public class Ply {
    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;

    /**
     * Creates a Ply
     * @param p piece
     * @param s square
     * @param t square
     * @param c Optional value
     */
    public Ply(Piece p, Square s, Square t, Optional<String> c) {
        this.piece = p;
        this.from = s;
        this.to = t;
        this.comment = c;
    }

    /**
     * getter method for the piece
     * @return the name of the file
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * getter method for the from square
     * @return the name of the file
     */
    public Square getFrom() {
        return from;
    }

    /**
     * getter method for the to square
     * @return the name of the file
     */
    public Square getTo() {
        return to;
    }

    /**
     * getter method for the comment val
     * @return the name of the file
     */
    public Optional<String> getComment() {
        return comment;
    }
}