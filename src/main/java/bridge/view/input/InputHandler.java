package bridge.view.input;

import bridge.exception.RetryHandler;
import bridge.view.answer.LineAnswer;
import bridge.view.answer.RetryAnswer;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public int readBridgeSize() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readBridgeSize();
            InputValidator.validatePositiveInteger(answer);
            return Integer.parseInt(answer);
        });
    }

    public LineAnswer readMoving() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readMoving();
            return LineAnswer.findByInputValue(answer);
        });
    }

    public RetryAnswer readGameCommand() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readGameCommand();
            return RetryAnswer.findByInputValue(answer);
        });
    }
}
