package bridge.repository;

import bridge.model.user.Movement;
import bridge.model.user.MovementStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovementRepository {
    private final List<Movement> movements;
    private int initializationCount;

    public MovementRepository() {
        this.movements = new ArrayList<>();
        this.initializationCount = 1;
    }

    public void save(Movement movement) {
        this.movements.add(movement);
    }

    public void deleteAll() {
        this.movements.clear();
        this.initializationCount++;
    }

    public Optional<Movement> findFirstByMovementStatus(MovementStatus movementStatus) {
        return movements.stream()
                .filter(m -> m.getStatus().equals(movementStatus))
                .findFirst();
    }

    public List<Movement> findAll() {
        return List.copyOf(movements);
    }

    public Movement findByLastIndex() {
        if (movements.size() == 0) {
            throw new IllegalStateException();
        }
        return movements.get(movements.size() - 1);
    }

    public int getInitializationCount() {
        return initializationCount;
    }
}
