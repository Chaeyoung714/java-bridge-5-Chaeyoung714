package bridge.exception;

public enum ExceptionMessages {
    WRONG_BRIDGE_LENGTH("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
    WRONG_RETRY_ANSWER("R 또는 Q를 입력해주세요. (재시도: R, 종료: Q)"),
    WRONG_LINE_ANSWER("U 또는 D를 입력해주세요. (위: U, 아래: D)"),
    ;

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
