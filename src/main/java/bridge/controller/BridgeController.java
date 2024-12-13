package bridge.controller;

import bridge.view.InputHandler;
import bridge.view.OutputView;

public class BridgeController {
    private final InputHandler inputHandler;
    private final OutputView outputView;

    public BridgeController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void run() {

    }
}
