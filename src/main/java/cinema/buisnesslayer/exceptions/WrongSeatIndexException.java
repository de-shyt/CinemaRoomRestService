package cinema.buisnesslayer.exceptions;

public class WrongSeatIndexException extends RuntimeException {

    public WrongSeatIndexException() {
        super("The number of a row or a column is out of bounds!");
    }
}
