package bridge.model.game;

import bridge.exception.ExceptionMessages;
import java.util.List;
import java.util.stream.Collectors;

public class Bridge {
    private static final int MIN_BRIDGE_LENGTH = 3;
    private static final int MAX_BRIDGE_LENGTH = 20;

    private final int length;
    private final List<Line> answer;

    public Bridge(int length, List<Line> answer) {
        validateSize(length, answer);
        this.length = length;
        this.answer = answer;
    }

    private void validateSize(int length, List<Line> answer) {
        if (length < MIN_BRIDGE_LENGTH || length > MAX_BRIDGE_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_BRIDGE_LENGTH.getMessage());
        }
        if (length != answer.size()) {
            throw new IllegalStateException();
        }
    }

    public static Bridge from(int bridgeLength, List<String> bridges) {
        return new Bridge(bridgeLength, bridges.stream()
                .map(b -> Line.findByExpression(b))
                .collect(Collectors.toList()));
    }


    public Line getAnswerByOrder(int order) {
        return answer.get(order);
    }

    public int getLength() {
        return length;
    }

}
