package bridge.service;

import bridge.dto.GameResult;
import bridge.model.game.GameStatus;
import bridge.model.game.Line;
import bridge.model.user.Movement;
import bridge.model.user.MovementStatus;
import bridge.repository.MovementRepository;
import java.util.List;
import java.util.Optional;

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
        Optional<Movement> wrongMovement = movementRepository.findFirstByMovementStatus(MovementStatus.WRONG);
        return wrongMovement.isPresent() && wrongMovement.get().equals(movementRepository.findByLastIndex());
    }

    public void retry() {
        movementRepository.deleteAll();
    }

    public GameResult calculateGameResult() {
        int tryCount = movementRepository.getInitializationCount();
        List<Movement> movements = movementRepository.findAll();
        if (movementRepository.findFirstByMovementStatus(MovementStatus.WRONG).isPresent()) {
            return new GameResult(GameStatus.FAIL, tryCount, movements);
        }
        return new GameResult(GameStatus.SUCCESS, tryCount, movements);
    }
}
