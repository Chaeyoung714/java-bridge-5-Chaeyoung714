package bridge.view;

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

    public void validate1(String answer) {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("값을 채워주세요.");
        }
    }

}
