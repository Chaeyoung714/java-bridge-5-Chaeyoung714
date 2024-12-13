package bridge.view;

import java.util.Arrays;

public enum LineAnswer {
    UP("U"),
    DOWN("D"),
    ;

    private final String inputValue;

    LineAnswer(String inputValue) {
        this.inputValue = inputValue;
    }

    public static LineAnswer findByInputValue(String inputValue) {
        return Arrays.stream(LineAnswer.values())
                .filter(a -> a.getInputValue().equals(inputValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getInputValue() {
        return inputValue;
    }
}
