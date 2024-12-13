package bridge.view;

import java.util.Arrays;

public enum Answer {
    RETRY("R"),
    QUIT("Q"),
    ;

    private final String inputValue;

    Answer(String inputValue) {
        this.inputValue = inputValue;
    }

    public static Answer findByInputValue(String inputValue) {
        return Arrays.stream(Answer.values())
                .filter(a -> a.getInputValue().equals(inputValue))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private String getInputValue() {
        return inputValue;
    }
}
