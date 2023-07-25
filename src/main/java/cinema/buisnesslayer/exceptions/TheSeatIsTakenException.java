package cinema.buisnesslayer.exceptions;

public class TheSeatIsTakenException extends RuntimeException {
    public TheSeatIsTakenException() {
        super("The ticket has been already purchased!");
    }
}
