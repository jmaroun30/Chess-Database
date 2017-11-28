import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Ply class that is one half of a move
 * @author gmaroun3
 *
 * @version 1.0
 */
public class ChessGame {
    private List<Move> moves;

    /**
     * Creates a ChessGame
     * @param m move list
     */
    public ChessGame(List<Move> m) {
        moves = m;
    }

     /**
     * getter method for the moves
     * @return the list of moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * getter method for the move
     * @param n nth move
     * @return the name of the move
     */
    public Move getMove(int n) {
        return moves.get(n);
    }

    /**
     * filter method method for the Move
     * @param filter for the moves
     * @return the list of the moves filtered
     */
    public List<Move> filter(Predicate<Move> filter) {
        List<Move> f = new ArrayList<Move>();
        for (Move m : moves) {
            if (filter.test(m)) {
                f.add(m);
            }
        }
        return f;
    }

    /**
     * getMovesWithComment() method for filter
     * @return the list of moves
     */
    public List<Move> getMovesWithComment() {
        return filter((Move m) -> m.getWhitePly().getComment().
            isPresent() || m.getBlackPly().getComment().isPresent());
    }

    /**
     * getMovesWithoutComment() method for filter
     * @return the list of moves
     */
    public List<Move> getMovesWithoutComment() {
        return filter(new Predicate<Move>() {
            public boolean test(Move m) {
                return !m.getWhitePly().getComment().
                isPresent() && !m.getBlackPly().getComment().isPresent();
            }
        });
    }

    /**
     * getMovesWithPiece() method for filter
     * @param p Piece
     * @return the list of moves
     */
    public List<Move> getMovesWithPiece(Piece p) {
        class PieceMoves implements Predicate<Move> {
            public boolean test(Move m) {
                if (m.getWhitePly().getPiece().algebraicName()
                    .equals(p.algebraicName()) || m.getBlackPly().getPiece()
                    .algebraicName().equals(p.algebraicName())) {
                    return true;
                }
                return false;
            }
        }
        return filter(new PieceMoves());
    }
}