package bridge.model;

public enum Status {
    CORRECT("O"),
    WRONG("X"),
    ;

    private final String expression;

    Status(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
