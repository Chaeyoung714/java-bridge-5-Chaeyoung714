package bridge.view;

import java.util.Arrays;

public enum RetryAnswer {
    RETRY("R"),
    QUIT("Q"),
    ;

    private final String inputValue;

    RetryAnswer(String inputValue) {
        this.inputValue = inputValue;
    }

    public static RetryAnswer findByInputValue(String inputValue) {
        return Arrays.stream(RetryAnswer.values())
                .filter(a -> a.getInputValue().equals(inputValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private String getInputValue() {
        return inputValue;
    }
}