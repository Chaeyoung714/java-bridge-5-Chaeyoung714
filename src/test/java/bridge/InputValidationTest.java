package bridge;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.exception.ExceptionMessages;
import bridge.view.answer.LineAnswer;
import bridge.view.answer.RetryAnswer;
import bridge.view.input.InputValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidationTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "0.5"})
    void 다리_길이_입력시_양의_정수가_아닌_값을_입력하면_예외가_발생한다(String wrongInput) {
        assertThatThrownBy(() -> InputValidator.validatePositiveInteger(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_BRIDGE_LENGTH.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"u", "d", "A", " ", ""})
    void 이동_방향_입력시_잘못된_값을_입력하면_예외가_발생한다(String wrongInput) {
        assertThatThrownBy(() -> LineAnswer.findByInputValue(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_LINE_ANSWER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "q", "A", " ", ""})
    void 재시도_여부_입력시_잘못된_값을_입력하면_예외가_발생한다(String wrongInput) {
        assertThatThrownBy(() -> RetryAnswer.findByInputValue(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_RETRY_ANSWER.getMessage());
    }
}
