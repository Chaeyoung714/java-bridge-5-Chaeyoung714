package bridge;

import bridge.controller.BridgeController;

public class Application {
    public static void main(String[] args) {
        DependencyConfig config = new DependencyConfig();
        BridgeController controller = config.controller();
        controller.run();
    }
}
