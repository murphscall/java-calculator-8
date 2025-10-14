package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    private final StringCalculator stringCalculator = new StringCalculator();

    @Test
    void 입력값이_null이면_예외를_반환한다() {
        String input = null;

        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calc(input),
                "null 입력 시 IllegalArgumentException 이 발생해야 한다.");
    }

    @Test
    void 입력값이_빈문자열이면_0을_반환한다() {
        String input = "";

        int result = stringCalculator.calc(input);

        assertEquals(0, result);
    }
}