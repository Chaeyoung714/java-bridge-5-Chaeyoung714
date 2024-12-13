package bridge.model.user;

import bridge.model.game.Line;

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
