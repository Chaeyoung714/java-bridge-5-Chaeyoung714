package bridge.controller;

import bridge.BridgeMaker;
import bridge.model.Bridge;
import bridge.model.Line;
import bridge.service.BridgeGame;
import bridge.view.InputHandler;
import bridge.view.OutputView;

public class BridgeController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;
    private final BridgeGame bridgeGame;

    public BridgeController(InputHandler inputHandler, OutputView outputView, BridgeMaker bridgeMaker,
                            BridgeGame bridgeGame) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
        this.bridgeGame = bridgeGame;
    }

    public void run() {
        int bridgeLength = inputHandler.readBridgeSize();
        Bridge bridge = Bridge.from(bridgeLength, bridgeMaker.makeBridge(bridgeLength));
        for (int i = 0; i < bridgeLength; i++) { //TODO bridge.repeat로 바꾸기
            Line line = Line.findByExpression(inputHandler.readMoving());
            bridgeGame.move(line, bridge.getAnswerOf(i));
        }

    }
}
