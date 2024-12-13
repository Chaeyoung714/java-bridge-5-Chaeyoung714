package bridge.service;

import bridge.dto.GameResult;
import bridge.model.GameStatus;
import bridge.model.Line;
import bridge.model.Movement;
import bridge.model.MovementStatus;
import bridge.repository.MovementRepository;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final MovementRepository movementRepository;

    public BridgeGame(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public List<Movement> move(Line chosenLine, Line answer) {
        if (chosenLine.equals(answer)) {
            movementRepository.save(new Movement(chosenLine, MovementStatus.CORRECT));
            return movementRepository.findAll();
        }
        movementRepository.save(new Movement(chosenLine, MovementStatus.WRONG));
        return movementRepository.findAll();
    }

    public boolean checkIfMovementIsWrong() {
        //TODO : 마지막 인덱스인지도 확인
        return movementRepository.findByMovementStatus(MovementStatus.WRONG).isPresent();
    }

    public void retry() {
        movementRepository.deleteAll();
    }

    public GameResult calculateGameResult() {
        int tryCount = movementRepository.getDeletionCount();
        List<Movement> movements = movementRepository.findAll();
        if (movementRepository.findByMovementStatus(MovementStatus.WRONG).isPresent()) {
            return new GameResult(GameStatus.FAIL, tryCount, movements);
        }
        return new GameResult(GameStatus.SUCCESS, tryCount, movements);
    }
}
