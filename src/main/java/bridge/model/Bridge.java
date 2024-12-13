package bridge.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bridge {
    private final int length;
    private final List<Line> answer;

    public Bridge(int length, List<Line> answer) {
        validateLength(length);
        this.length = length;
        this.answer = answer;
    }

    private void validateLength(int length) {
        if (length < 3 || length > 20) {
            throw new IllegalArgumentException("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
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
