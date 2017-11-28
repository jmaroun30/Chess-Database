/**
 * Rook subclass of Piece superclass
 * @author gmaroun3
 *
 * @version 1.0
 */
public class Rook extends Piece {

    /**
     * Creates a Rook piece
     * @param color type of color
     */
    public Rook(Color color) {
        super(color);
    }

    /**
     * @Override the fenName() piece method
     * @return string identifier
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "R";
        }
        return "r";
    }

    /**
     * @Override the algebraicName() piece method
     * @return string identifier
     */
    public String algebraicName() {
        return "R";
    }

    /**
     * @Override the movesFrom() piece method
     * @param square one piece on a board
     * @return Square
     */
    public Square[] movesFrom(Square square) {
        String poss = "";
        char file = square.getFile();
        int col = (int) (file) - 97;
        char rank = square.getRank();
        int row = (int) (rank) - 49;

        for (int i = row + 1; i < 8; i++) {
            poss = poss + " " + (char) (file) + (char) (rank + i - row);
        }
        for (int i = row - 1; i >= 0; i--) {
            poss = poss + " " + (char) (file) + (char) (rank - (row - i));
        }
        for (int i = col + 1; i < 8; i++) {
            poss = poss + " " + (char) (file + i - col) + (char) (rank);
        }
        for (int i = col - 1; i >= 0; i--) {
            poss = poss + " " + (char) (file - (col - i)) + (char) (rank);
        }
        poss = poss.substring(1, poss.length());
        String[] arr = poss.split(" ");
        Square[] result = new Square[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = new Square(arr[i]);
        }
        return result;
    }
}