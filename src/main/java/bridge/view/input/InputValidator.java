package bridge.view.input;

public class InputValidator {

    public static void validatePositiveInteger(String answer) {
        try {
            if (Integer.parseInt(answer) < 0) {
                throw new IllegalArgumentException("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }
}