package bridge.dto;

import bridge.model.GameStatus;

public class GameResult {
    private final GameStatus gameStatus;
    private final int tryCount;

    public GameResult(GameStatus gameStatus, int tryCount) {
        this.gameStatus = gameStatus;
        this.tryCount = tryCount;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getTryCount() {
        return tryCount;
    }
}
