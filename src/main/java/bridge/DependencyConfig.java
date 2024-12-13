package bridge;

import bridge.controller.BridgeController;
import bridge.view.InputHandler;
import bridge.view.InputView;
import bridge.view.OutputView;

public class DependencyConfig {
    private final BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();

    private BridgeMaker bridgeMaker() {
        return new BridgeMaker(bridgeNumberGenerator);
    }

    public BridgeController controller() {
        return new BridgeController(
                new InputHandler(new InputView())
                , new OutputView()
                , bridgeMaker()
        );
    }
}
