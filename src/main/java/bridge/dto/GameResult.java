package bridge.dto;

import bridge.model.game.GameStatus;
import bridge.model.user.Movement;
import java.util.Collections;
import java.util.List;

public class GameResult {
    private final GameStatus gameStatus;
    private final int tryCount;
    private final List<Movement> movements;

    public GameResult(GameStatus gameStatus, int tryCount, List<Movement> movements) {
        this.gameStatus = gameStatus;
        this.tryCount = tryCount;
        this.movements = movements;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getTryCount() {
        return tryCount;
    }

    public List<Movement> getMovements() {
        return Collections.unmodifiableList(movements);
    }
}
