package calculator;


import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = ",|:";

    public int calc(String input) {
        // 입력 값이 null 이라면 예외를 던진다.
        validateInput(input);

        // 입력 값이 빈 문자열이라면 0을 반환한다.
        if (input.isEmpty()) {
            return 0;
        }

        String delimiter = addCustomDelimiter(input);
        String numbers = extractNumbers(input);

        String[] tokens = numbers.split(delimiter);

        return 1;
    }

    private void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력이 null 일 수 없습니다.");
        }
    }

    public String addCustomDelimiter(String input) {
        if (!input.startsWith("//")) {
            return DEFAULT_DELIMITER;
        }
        int idx = input.indexOf("\n");
        if (idx == -1) {
            throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
        }

        String customDelimiter = Pattern.quote(input.substring(2, idx));
        return customDelimiter + "|" + DEFAULT_DELIMITER;
    }

    public String extractNumbers(String input) {
        if (input.startsWith("//")) {
            int idx = input.indexOf("\n");
            return input.substring(idx + 1);
        }

        return input;
    }


}
