package bridge.exception;

public class ErrorHandler {

    public static void handleUserError(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage() + System.lineSeparator());
    }

    public static void handleSystemError(IllegalStateException e) {
        System.out.println("[SYSTEM] " + e.getMessage());
    }

    private ErrorHandler() {
    }
}
