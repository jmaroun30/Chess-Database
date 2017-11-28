/**
 * Move class that is two Ply's
 * @author gmaroun3
 *
 * @version 1.0
 */
public class Move {
    private Ply whitePly;
    private Ply blackPly;

    /**
     * Creates a Move
     * @param w white Ply
     * @param b black Ply
     */
    public Move(Ply w, Ply b) {
        this.whitePly = w;
        this.blackPly = b;
    }

    /**
     * getter method for the white ply
     * @return the name of the ply
     */
    public Ply getWhitePly() {
        return whitePly;
    }

    /**
     * getter method for the black ply
     * @return the name of the ply
     */
    public Ply getBlackPly() {
        return blackPly;
    }
}