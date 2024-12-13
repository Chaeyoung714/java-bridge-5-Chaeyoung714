package bridge.view.input;

import bridge.exception.ExceptionMessages;

public class InputValidator {

    public static void validatePositiveInteger(String answer) {
        try {
            if (Integer.parseInt(answer) <= 0) {
                throw new IllegalArgumentException(ExceptionMessages.WRONG_BRIDGE_LENGTH.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_BRIDGE_LENGTH.getMessage());
        }
    }
}
