/**
 * King subclass of Piece superclass
 * @author gmaroun3
 *
 * @version 1.0
 */
public class King extends Piece {

    /**
     * Creates a King piece
     * @param color type of color
     */
    public King(Color color) {
        super(color);
    }

    /**
     * @Override the fenName() piece method
     * @return string identifier
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "K";
        }
        return "k";
    }

    /**
     * @Override the algebraicName() piece method
     * @return string identifier
     */
    public String algebraicName() {
        return "K";
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
        boolean colBig = col + 1 < 8;
        boolean colSmall = col - 1 >= 0;
        boolean rowBig = row + 1 < 8;
        boolean rowSmall = row - 1 >= 0;
        if (rowBig) {
            poss = poss + " " + (char) (file) + (char) (rank + 1);
            if (colBig) {
                poss = poss + " " + (char) (file + 1) + (char) (rank + 1);
            }
            if (colSmall) {
                poss = poss + " " + (char) (file - 1) + (char) (rank + 1);
            }
        }
        if (rowSmall) {
            poss = poss + " " + (char) (file) + (char) (rank - 1);
            if (colBig) {
                poss = poss + " " + (char) (file + 1) + (char) (rank - 1);
            }
            if (colSmall) {
                poss = poss + " " + (char) (file - 1) + (char) (rank - 1);
            }
        }
        if (colBig) {
            poss = poss + " " + (char) (file + 1) + (char) (rank);
        }
        if (colSmall) {
            poss = poss + " " + (char) (file - 1) + (char) (rank);
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