package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.model.game.Bridge;
import bridge.model.game.Line;
import bridge.model.user.Movement;
import bridge.model.user.MovementStatus;
import bridge.repository.MovementRepository;
import bridge.service.BridgeGame;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BridgeGameTest {
    private MovementRepository movementRepository;
    private BridgeGame bridgeGame;

    @BeforeEach
    void setUp() {
        movementRepository = new MovementRepository();
        bridgeGame = new BridgeGame(movementRepository);
    }

    @Test
    void 다리를_틀리지않고_건넌다() {
        Bridge bridge = Bridge.from(3, List.of("U", "U", "D"));
        List<Line> chosenLines = List.of(Line.UP, Line.UP, Line.DOWN);

        List<Movement> firstMovement = bridgeGame.move(chosenLines.get(0), bridge.getAnswerByOrder(0));
        List<Movement> secondMovement = bridgeGame.move(chosenLines.get(1), bridge.getAnswerByOrder(1));
        List<Movement> thirdMovement = bridgeGame.move(chosenLines.get(2), bridge.getAnswerByOrder(2));

        boolean hasWrongMovement = bridgeGame.checkIfMovementIsWrong();

        assertThat(firstMovement.size()).isEqualTo(1);
        assertThat(firstMovement.get(0).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(firstMovement.get(0).getLine()).isEqualTo(Line.UP);

        assertThat(secondMovement.size()).isEqualTo(2);
        assertThat(secondMovement.get(1).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(secondMovement.get(1).getLine()).isEqualTo(Line.UP);

        assertThat(thirdMovement.size()).isEqualTo(3);
        assertThat(thirdMovement.get(2).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(thirdMovement.get(2).getLine()).isEqualTo(Line.DOWN);

        assertThat(hasWrongMovement).isFalse();
    }

    @Test
    void 다리를_건너는_중간에_틀린다() {
        Bridge bridge = Bridge.from(3, List.of("U", "U", "D"));
        List<Line> chosenLines = List.of(Line.UP, Line.UP, Line.UP);

        List<Movement> firstMovement = bridgeGame.move(chosenLines.get(0), bridge.getAnswerByOrder(0));
        List<Movement> secondMovement = bridgeGame.move(chosenLines.get(1), bridge.getAnswerByOrder(1));
        List<Movement> thirdMovement = bridgeGame.move(chosenLines.get(2), bridge.getAnswerByOrder(2));

        boolean hasWrongMovement = bridgeGame.checkIfMovementIsWrong();

        assertThat(firstMovement.size()).isEqualTo(1);
        assertThat(firstMovement.get(0).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(firstMovement.get(0).getLine()).isEqualTo(Line.UP);

        assertThat(secondMovement.size()).isEqualTo(2);
        assertThat(secondMovement.get(1).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(secondMovement.get(1).getLine()).isEqualTo(Line.UP);

        assertThat(thirdMovement.size()).isEqualTo(3);
        assertThat(thirdMovement.get(2).getStatus()).isEqualTo(MovementStatus.WRONG);
        assertThat(thirdMovement.get(2).getLine()).isEqualTo(Line.UP);

        assertThat(hasWrongMovement).isTrue();
    }

    @Test
    void 잘못_건널시_재시도를_선택하면_이전_결과를_초기화한다() {
        Bridge bridge = Bridge.from(3, List.of("U", "U", "D"));
        List<Line> chosenLines = List.of(Line.UP, Line.UP, Line.UP);

        bridgeGame.move(chosenLines.get(0), bridge.getAnswerByOrder(0));
        bridgeGame.move(chosenLines.get(1), bridge.getAnswerByOrder(1));
        bridgeGame.move(chosenLines.get(2), bridge.getAnswerByOrder(2));

        bridgeGame.retry();

        assertThat(movementRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void 잘못_건널시_재시도를_선택하면_재시도_이후_결과를_저장한다() {
        Bridge bridge = Bridge.from(3, List.of("U", "U", "D"));
        List<Line> firstChosenLines = List.of(Line.UP, Line.UP, Line.UP);
        List<Line> secondChosenLines = List.of(Line.UP, Line.UP, Line.DOWN);

        List<Movement> firstMovement = bridgeGame.move(firstChosenLines.get(0), bridge.getAnswerByOrder(0));
        List<Movement> secondMovement = bridgeGame.move(firstChosenLines.get(1), bridge.getAnswerByOrder(1));
        List<Movement> thirdMovement = bridgeGame.move(firstChosenLines.get(2), bridge.getAnswerByOrder(2));

        bridgeGame.retry();

        firstMovement = bridgeGame.move(secondChosenLines.get(0), bridge.getAnswerByOrder(0));
        secondMovement = bridgeGame.move(secondChosenLines.get(1), bridge.getAnswerByOrder(1));
        thirdMovement = bridgeGame.move(secondChosenLines.get(2), bridge.getAnswerByOrder(2));

        assertThat(movementRepository.findAll().size()).isEqualTo(3);

        assertThat(firstMovement.size()).isEqualTo(1);
        assertThat(firstMovement.get(0).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(firstMovement.get(0).getLine()).isEqualTo(Line.UP);

        assertThat(secondMovement.size()).isEqualTo(2);
        assertThat(secondMovement.get(1).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(secondMovement.get(1).getLine()).isEqualTo(Line.UP);

        assertThat(thirdMovement.size()).isEqualTo(3);
        assertThat(thirdMovement.get(2).getStatus()).isEqualTo(MovementStatus.CORRECT);
        assertThat(thirdMovement.get(2).getLine()).isEqualTo(Line.DOWN);
    }

    @Test
    void 재시도_할때마다_총시도횟수가_1씩_증가한다() {
        IntStream.range(1, 5).forEach(i -> {
            assertThat(movementRepository.getInitializationCount()).isEqualTo(i);
            bridgeGame.retry();
        });
    }
}
