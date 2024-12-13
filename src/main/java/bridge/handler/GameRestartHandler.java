package bridge.handler;

import bridge.exception.customException.GameQuitException;
import bridge.exception.customException.GameRestartException;

public class GameRestartHandler {

    public static void retryUntilSuccessOrQuit(Runnable game) {
        try {
            game.run();
        } catch (GameRestartException e) {
            game.run();
        } catch (GameQuitException e) {
        }
    }

    private GameRestartHandler() {
    }
}
