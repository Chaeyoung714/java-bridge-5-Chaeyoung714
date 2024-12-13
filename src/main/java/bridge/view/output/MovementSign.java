package bridge.view.output;

import bridge.model.user.MovementStatus;
import java.util.Arrays;
import java.util.Optional;

public enum MovementSign {
    CORRECT("O", MovementStatus.CORRECT),
    WRONG("X", MovementStatus.WRONG),
    NONE(" ", null),
    ;

    private final String sign;
    private final MovementStatus movementStatus;

    MovementSign(String sign, MovementStatus movementStatus) {
        this.sign = sign;
        this.movementStatus = movementStatus;
    }

    public static String findByStatusOrEmpty(Optional<MovementStatus> status) {
        if (status.isEmpty()) {
            return NONE.getSign();
        }
        return Arrays.stream(MovementSign.values())
                .filter(m -> !m.equals(NONE))
                .filter(m -> m.getMovementStatus().equals(status.get()))
                .map(m -> m.getSign())
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private String getSign() {
        return sign;
    }

    private MovementStatus getMovementStatus() {
        return movementStatus;
    }
}
