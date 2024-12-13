package bridge;

import bridge.controller.BridgeController;
import bridge.repository.MovementRepository;
import bridge.service.BridgeGame;
import bridge.view.InputHandler;
import bridge.view.InputView;
import bridge.view.OutputView;

public class DependencyConfig {
    private final BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
    private final MovementRepository movementRepository = new MovementRepository();

    private BridgeMaker bridgeMaker() {
        return new BridgeMaker(bridgeNumberGenerator);
    }

    private BridgeGame bridgeGame() {
        return new BridgeGame(movementRepository);
    }

    public BridgeController controller() {
        return new BridgeController(
                new InputHandler(new InputView())
                , new OutputView()
                , bridgeMaker()
                , bridgeGame()
        );
    }
}
