package bridge.repository;

import bridge.model.Movement;
import bridge.model.MovementStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MovementRepository {
    private final List<Movement> movements;
    private int deletionCount; //TODO : 가능하면 옮기기

    public MovementRepository() {
        this.movements = new ArrayList<>();
        this.deletionCount = 1;
    }

    public void save(Movement movement) {
        this.movements.add(movement);
    }

    public void deleteAll() {
        this.movements.clear();
        this.deletionCount++;
    }

    public Optional<Movement> findFirstByMovementStatus(MovementStatus movementStatus) {
        return movements.stream()
                .filter(m -> m.getStatus().equals(movementStatus))
                .findFirst();
    }

    public List<Movement> findAll() {
        return Collections.unmodifiableList(movements);
    }

    public Movement findByLastIndex() {
        if (movements.size() == 0) {
            throw new IllegalStateException();
        }
        return movements.get(movements.size() - 1);
    }

    public int getDeletionCount() {
        return deletionCount;
    }
}
