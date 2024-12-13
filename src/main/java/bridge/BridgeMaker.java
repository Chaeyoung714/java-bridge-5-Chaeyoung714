package bridge;

import bridge.model.Line;
import java.util.ArrayList;
import java.util.List;

public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        List<String> bridgeNumbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int randomNumber = bridgeNumberGenerator.generate();
            System.out.println(randomNumber); //TODO debug
            Line randomLine = Line.findBySign(randomNumber);
            bridgeNumbers.add(randomLine.getExpression());
        }
        return bridgeNumbers;
    }
}
