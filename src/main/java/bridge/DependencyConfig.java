package bridge;

import bridge.controller.BridgeController;
import bridge.view.InputHandler;
import bridge.view.InputView;
import bridge.view.OutputView;

public class DependencyConfig {

    public BridgeController controller() {
        return new BridgeController(
                new InputHandler(new InputView())
                , new OutputView()
        );
    }
}
