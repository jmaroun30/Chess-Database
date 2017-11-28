/**
 * Square class that defines the contents of a square on a chess board
 * @author gmaroun3
 *
 * @version 1.0
 */
public class Square {
    private char file;
    private char rank;
    private String name;

    /**
     * Creates a square
     * @param file name of the file(column)
     * @param rank name of the rank(row)
     */
    public Square(char file, char rank) throws InvalidSquareException {
        this.file = file;
        this.rank = rank;
        this.name = "" + file + rank;
        if (file > 'h' || file < 'a') {
            throw new InvalidSquareException(this.name);
        }
        if (rank < '1' || rank > '8') {
            throw new InvalidSquareException(this.name);
        }
    }

    /**
     * Creates a square
     * @param name of starting position
     *
     */
    public Square(String name) throws InvalidSquareException {
        this.name = name;
        this.file = name.charAt(0);
        this.rank = name.charAt(1);
        if (name.charAt(0) > 'h' || name.charAt(0) < 'a' || name.length() > 2) {
            throw new InvalidSquareException(name);
        }
        if (name.charAt(1) < '1' || name.charAt(1) > '8') {
            throw new InvalidSquareException(name);
        }
    }

    /**
     * @Override the toString() method
     * @return string name
     */
    public String toString() {
        return this.name;
    }

    /**
     * getter method for the column
     * @return the name of the file
     */
    public char getFile() {
        return this.file;
    }

    /**
     * getter method for the row
     * @return the name of the rank
     */
    public char getRank() {
        return this.rank;
    }

    /**
     * @Override the equals() method
     * @param obj of type Object
     * @return equal value
     */
    public boolean equals(Object obj) {
        if (obj instanceof Square) {
            return ((Square) obj).getFile() == this.file
                && ((Square) obj).getRank() == this.rank;
        }
        return false;
    }
}