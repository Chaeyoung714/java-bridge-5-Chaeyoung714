package bridge.handler;

public class ErrorHandler {

    public static void handleUserError(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage() + System.lineSeparator());
    }

    private ErrorHandler() {
    }
}
