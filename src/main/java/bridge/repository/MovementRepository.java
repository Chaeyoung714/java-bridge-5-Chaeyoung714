package bridge.repository;

import bridge.model.Movement;
import bridge.model.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MovementRepository {
    private final List<Movement> movements;
    private int initializationCount; //TODO : 가능하면 옮기기

    public MovementRepository() {
        this.movements = new ArrayList<>();
        this.initializationCount = 1;
    }

    public void save(Movement movement) {
        this.movements.add(movement);
    }

    public Optional<Movement> findByMovementStatus(Status status) {
        return movements.stream()
                .filter(m -> m.getStatus().equals(status))
                .findFirst();
    }

    public List<Movement> findAll() {
        return Collections.unmodifiableList(movements);
    }

    public int getInitializationCount() {
        return initializationCount;
    }
}
