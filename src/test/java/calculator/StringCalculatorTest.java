package calculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"//;!n1;2;3", "//?!n1;2?3"})
    void 올바른_커스텀구분자_형식이아니면_예외를_반환한다(String input) {
        // calc() 호출 시 잘못된 커스텀 구분자 형식이면 예외 발생
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calc(input));
    }

    @Test
    void 올바른_커스텀구분자_형식이면_커스텀구분자를_추가하여_반환한다() {

        String input = "//;\n1;2:3";

        String delimiter = stringCalculator.addCustomDelimiter(input);

        assertEquals("\\Q;\\E|,|:", delimiter);
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\n1;2:3", "//?\n1?2?3"})
    void 커스텀구분자_선언부를_제거한다(String input) {

        String delimiter = stringCalculator.addCustomDelimiter(input);
        String numbers = stringCalculator.extractNumbers(input);

        String[] tokens = numbers.split(delimiter);

        assertArrayEquals(new String[]{"1", "2", "3"}, tokens);
    }
}