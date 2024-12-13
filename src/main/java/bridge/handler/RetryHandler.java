package bridge.handler;

import java.util.function.Supplier;

public class RetryHandler {

    public static <T> T retryUntilSuccessAndReturn(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                ErrorHandler.handleUserError(e);
            }
        }
    }

    private RetryHandler() {
    }
}
