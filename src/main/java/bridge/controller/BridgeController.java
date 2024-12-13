package bridge.controller;

import bridge.BridgeMaker;
import bridge.model.Bridge;
import bridge.model.Line;
import bridge.view.InputHandler;
import bridge.view.OutputView;

public class BridgeController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    public BridgeController(InputHandler inputHandler, OutputView outputView, BridgeMaker bridgeMaker) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
    }

    public void run() {
        int bridgeLength = inputHandler.readBridgeSize();
        Bridge bridge = Bridge.from(bridgeLength, bridgeMaker.makeBridge(bridgeLength));

    }
}
