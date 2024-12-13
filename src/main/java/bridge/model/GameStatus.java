package bridge.model;

public enum GameStatus {
    SUCCESS("성공"),
    FAIL("실패"),
    ;

    private final String expression;

    GameStatus(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
