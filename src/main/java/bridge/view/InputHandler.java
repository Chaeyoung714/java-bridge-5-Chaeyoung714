package bridge.view;

import bridge.exception.RetryHandler;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public int readBridgeSize() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            try {
                int lengthInput = inputView.readBridgeSize();
                return lengthInput;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
            }
        });
    }

//    public String read() {
//        return RetryHandler.retryUntilSuccessAndReturn(() -> {
//            String answer = inputView.read();
//            InputValidator.validate(answer);
//            return answer;
//        });
//    }
}
