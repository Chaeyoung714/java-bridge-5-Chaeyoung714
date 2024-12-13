package bridge.model;

public class Movement {
    private final Line line;
    private final Status status;

    public Movement(Line line, Status status) {
        this.line = line;
        this.status = status;
    }

    public Line getLine() {
        return line;
    }

    public Status getStatus() {
        return status;
    }
}
