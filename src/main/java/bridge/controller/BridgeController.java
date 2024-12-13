package bridge.controller;

import bridge.BridgeMaker;
import bridge.dto.GameResult;
import bridge.exception.customException.GameQuitException;
import bridge.exception.customException.GameRestartException;
import bridge.exception.RetryHandler;
import bridge.model.game.Bridge;
import bridge.model.game.Line;
import bridge.model.user.Movement;
import bridge.service.BridgeGame;
import bridge.view.answer.LineAnswer;
import bridge.view.answer.RetryAnswer;
import bridge.view.input.InputHandler;
import bridge.view.output.OutputView;
import java.util.List;
import java.util.stream.IntStream;

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
        outputView.printStartLine();
        Bridge bridge = registerBridge();
        doBridgeGame(bridge);
        GameResult gameResult = bridgeGame.calculateGameResult();
        outputView.printResult(gameResult);
    }

    private Bridge registerBridge() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            int bridgeLength = inputHandler.readBridgeSize();
            return Bridge.from(bridgeLength, bridgeMaker.makeBridge(bridgeLength));
        });
    }

    private void doBridgeGame(Bridge bridge) {
        try {
            IntStream.range(0, bridge.getLength()).forEach(i -> {
                LineAnswer line = inputHandler.readMoving();
                List<Movement> intermediateMap = bridgeGame.move(Line.findByExpression(line.getInputValue()),
                        bridge.getAnswerByOrder(i));
                outputView.printMap(intermediateMap);
                retryIfWrongMovement();
            });
        } catch (GameRestartException e) {
            doBridgeGame(bridge);
        } catch (GameQuitException e) {
        }
    }

    private void retryIfWrongMovement() {
        if (bridgeGame.checkIfMovementIsWrong()) {
            RetryAnswer retryAnswer = inputHandler.readGameCommand();
            if (retryAnswer.equals(RetryAnswer.RETRY)) {
                bridgeGame.retry();
                throw new GameRestartException();
            }
            throw new GameQuitException();
        }
    }
}
