package bridge.model;

public class Movement {
    private final Line line;
    private final MovementStatus movementStatus;

    public Movement(Line line, MovementStatus movementStatus) {
        this.line = line;
        this.movementStatus = movementStatus;
    }

    public Line getLine() {
        return line;
    }

    public MovementStatus getStatus() {
        return movementStatus;
    }
}
