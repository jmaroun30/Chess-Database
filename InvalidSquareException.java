/**
 * InavalidSquareException that checks to see if instantiated square is OOB
 * @author gmaroun3
 *
 * @version 1.0
 * I decided on a checked exception because I knew that the exception thrown
 * would be expected. Further, I have no way of preventing what the user inputs,
 * even though the parameters and rules of the game do not allow such inputs.
 * Since the exception is expected yet unpreventable on my part, a checked
 * exception is the best choice.
 */
public class InvalidSquareException extends Exception {
    /**
    * @param square name passed in by user
    */
    public InvalidSquareException(String square) {
        super(square);
    }
}