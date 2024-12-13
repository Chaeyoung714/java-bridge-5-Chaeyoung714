package bridge.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Bridge {
    private final int length;
    private final List<Line> answer;

    public Bridge(int length, List<Line> answer) {
        this.length = length;
        this.answer = answer;
    }

    public static Bridge from(int bridgeLength, List<String> bridges) {
        return new Bridge(bridgeLength, bridges.stream()
                .map(b -> Line.findByExpression(b))
                .collect(Collectors.toList()));
    }

    public Line getAnswerOf(int index) {
        return answer.get(index);
    }

    public int getLength() {
        return length;
    }

    public List<Line> getAnswer() {
        return Collections.unmodifiableList(answer);
    }
}
