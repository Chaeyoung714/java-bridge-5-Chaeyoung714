package bridge.view;

import bridge.exception.RetryHandler;

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

    public String readMoving() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readMoving();
           //TODO :  InputValidator.validate(answer);
            return answer;
        });
    }

    public Answer readGameCommand() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readGameCommand();
            //TODO :  InputValidator.validate(answer);
            return Answer.findByInputValue(answer);
        });
    }
}
