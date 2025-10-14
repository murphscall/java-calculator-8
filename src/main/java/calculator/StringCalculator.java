package calculator;


public class StringCalculator {

    private static final String DEFAULT_DELIMITER = "[,|:]";

    public int calc(String input) {
        // 입력 값이 null 이라면 예외를 던진다.
        validateInput(input);
        // 입력 값이 빈 문자열이라면 0을 반환한다.
        if (input.isEmpty()) {
            return 0;
        }

        return 1;
    }

    private void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력이 null 일 수 없습니다.");
        }
    }


}
