/**
 * Bishop subclass of Piece superclass
 * @author gmaroun3
 *
 * @version 1.0
 */
public class Bishop extends Piece {

    /**
     * Creates a Bishop piece
     * @param color type of color
     */
    public Bishop(Color color) {
        super(color);
    }

    /**
     * @Override the fenName() piece method
     * @return string identifier
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "B";
        }
        return "b";
    }

    /**
     * @Override the algebraicName() piece method
     * @return string identifier
     */
    public String algebraicName() {
        return "B";
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
        poss = poss.substring(1, poss.length());
        String[] arr = poss.split(" ");
        Square[] result = new Square[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = new Square(arr[i]);
        }
        return result;
    }
}