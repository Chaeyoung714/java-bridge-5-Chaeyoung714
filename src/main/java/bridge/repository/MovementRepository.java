package bridge.repository;

import bridge.model.Movement;
import java.util.ArrayList;
import java.util.List;

public class MovementRepository {
    private final List<Movement> movements;

    public MovementRepository() {
        this.movements = new ArrayList<>();
    }

    public void save(Movement movement) {
        this.movements.add(movement);
    }
}
