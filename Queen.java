/**
 * Queen subclass of Piece superclass
 * @author gmaroun3
 *
 * @version 1.0
 */
public class Queen extends Piece {

    /**
     * Creates a Queen piece
     * @param color type of color
     */
    public Queen(Color color) {
        super(color);
    }

    /**
     * @Override the fenName() piece method
     * @return string identifier
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "Q";
        }
        return "q";
    }

    /**
     * @Override the algebraicName() piece method
     * @return string identifier
     */
    public String algebraicName() {
        return "Q";
    }

    /**
     * @Override the movesFrom() piece method
     * @param square one piece on a board
     * @return Square[]
     */
    public Square[] movesFrom(Square square) {
        String poss = "";
        char file = square.getFile();
        int col = (int) (file) - 97;
        char rank = square.getRank();
        int row = (int) (rank) - 49;

        for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
            poss = poss + " " + (char) (file + j - col)
                + (char) (rank + i - row);
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            poss = poss + " " + (char) (file - (col - j))
                + (char) (rank - (row - i));
        }
        for (int i = row + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
            poss = poss + " " + (char) (file - (col - j))
                + (char) (rank + i - row);
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            poss = poss + " " + (char) (file + j - col)
                + (char) (rank - (row - i));
        }
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