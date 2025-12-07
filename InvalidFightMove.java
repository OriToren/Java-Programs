package JavaBattleSim;

public class InvalidFightMove extends RuntimeException {
    public InvalidFightMove(String message) {
        super(message);
    }
}
