/**
 * Pawn subclass of Piece superclass
 * @author gmaroun3
 *
 * @version 1.0
 */
public class Pawn extends Piece {

    /**
     * Creates a Pawn piece
     * @param color type of color
     */
    public Pawn(Color color) {
        super(color);
    }

    /**
     * @Override the fenName() piece method
     * @return string identifier
     */
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "P";
        }
        return "p";
    }

    /**
     * @Override the algebraicName() piece method
     * @return string identifier
     */
    public String algebraicName() {
        return "";
    }

    /**
     * @Override the movesFrom() piece method
     * @param square one piece on a board
     * @return Square[]
     */
    public Square[] movesFrom(Square square) {
        String type;
        String poss = "";
        char file = square.getFile();
        int col = (int) (file) - 97;
        char rank = square.getRank();
        int row = (int) (rank) - 49;
        if (this.getColor() == Color.WHITE) {
            type = "White";
        } else {
            type = "Black";
        }
        if (type.equals("White")) {
            if (row == 1) {
                poss = poss + " " + (char) (file) + (char) (rank + 2);
                poss = poss + " " + (char) (file) + (char) (rank + 1);
            } else if (row < 7) {
                poss = poss + " " + (char) (file) + (char) (rank + 1);
            }
        } else {
            if (row == 6) {
                poss = poss + " " + (char) (file) + (char) (rank - 2);
                poss = poss + " " + (char) (file) + (char) (rank - 1);
            } else if (row > 0) {
                poss = poss + " " + (char) (file) + (char) (rank - 1);
            }
        }
        if (poss.length() != 0) {
            poss = poss.substring(1, poss.length());
            String[] arr = poss.split(" ");
            Square[] result = new Square[arr.length];
            for (int i = 0; i < arr.length; i++) {
                result[i] = new Square(arr[i]);
            }
            return result;
        }
        return new Square[0];
    }
}