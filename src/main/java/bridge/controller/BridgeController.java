package bridge.controller;

import bridge.BridgeMaker;
import bridge.dto.GameResult;
import bridge.exception.GameQuitException;
import bridge.exception.GameRestartException;
import bridge.model.Bridge;
import bridge.model.Line;
import bridge.model.Movement;
import bridge.service.BridgeGame;
import bridge.view.Answer;
import bridge.view.InputHandler;
import bridge.view.OutputView;
import java.util.List;

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
        doBridgeGame(bridge);
        GameResult gameResult = bridgeGame.calculateGameResult();
        outputView.printResult(gameResult);
    }

    private void doBridgeGame(Bridge bridge) {
        try {
            for (int i = 0; i < bridge.getLength(); i++) { //TODO bridge.repeat로 바꾸기
                Line line = Line.findByExpression(inputHandler.readMoving());
                List<Movement> intermediateMap = bridgeGame.move(line, bridge.getAnswerOf(i));
                outputView.printMap(intermediateMap);
                retryIfWrongMovement();
            }
        } catch (GameRestartException e) {
            doBridgeGame(bridge);
        } catch (GameQuitException e) {
        }
    }

    private void retryIfWrongMovement() {
        if (bridgeGame.checkIfMovementIsWrong()) {
            Answer answer = inputHandler.readGameCommand();
            if (answer.equals(Answer.RETRY)) {
                bridgeGame.retry();
                throw new GameRestartException();
            }
            throw new GameQuitException();
        }
    }
}
