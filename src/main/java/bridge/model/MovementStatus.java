package bridge.model;

public enum MovementStatus {
    CORRECT("O"),
    WRONG("X"),
    ;

    private final String expression;

    MovementStatus(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
