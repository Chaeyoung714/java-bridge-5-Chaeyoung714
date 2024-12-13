package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.exception.ExceptionMessages;
import bridge.model.game.Bridge;
import bridge.model.game.Line;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BridgeMakingTest {
    private BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

    @Test
    void 다리_길이에_맞는_개수의_랜덤표현값을_생성한다() {
        List<String> lines = Arrays.stream(Line.values()).map(l -> l.getExpression()).collect(Collectors.toList());
        int testSize = 4;
        List<String> randomLines = bridgeMaker.makeBridge(testSize);

        assertThat(randomLines.size()).isEqualTo(testSize);
        assertThat(lines).containsAll(new HashSet<>(randomLines));
    }

    @Test
    void 생성한_표현값을_바탕으로_다리를_생성한다() {
        List<String> testLines = List.of("U", "U", "D", "D");

        Bridge bridge = Bridge.from(4, testLines);

        assertThat(bridge.getLength()).isEqualTo(4);
        assertThat(bridge.getAnswerByOrder(0)).isEqualTo(Line.UP);
        assertThat(bridge.getAnswerByOrder(1)).isEqualTo(Line.UP);
        assertThat(bridge.getAnswerByOrder(2)).isEqualTo(Line.DOWN);
        assertThat(bridge.getAnswerByOrder(3)).isEqualTo(Line.DOWN);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 21})
    void 잘못된_길이를_입력하면_다리를_생성할때_예외가_발생한다(int wrongLength) {
        List<String> wrongLines = new ArrayList<>();
        IntStream.range(0, wrongLength).forEach(i -> {
            wrongLines.add("U");
        });

        assertThatThrownBy(() -> Bridge.from(wrongLength, wrongLines))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_BRIDGE_LENGTH.getMessage());
    }
}
