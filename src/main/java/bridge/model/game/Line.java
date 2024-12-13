package bridge.model.game;

import java.util.Arrays;

public enum Line {
    UP("U", 1),
    DOWN("D", 0),
    ;

    private final String expression;
    private final int sign;

    Line(String expression, int sign) {
        this.expression = expression;
        this.sign = sign;
    }

    public static Line findBySign(int randomNumber) {
        return Arrays.stream(Line.values())
                .filter(l -> l.getSign() == randomNumber)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public static Line findByExpression(String expression) {
        return Arrays.stream(Line.values())
                .filter(l -> l.getExpression().equals(expression))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getExpression() {
        return expression;
    }

    public int getSign() {
        return sign;
    }
}
