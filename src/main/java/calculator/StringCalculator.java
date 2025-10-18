package calculator;


import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String NEWLINE = "\n";

    public int calc(String input) {
        // 입력 값이 null 이라면 예외를 던진다.
        validateInput(input);

        // 입력 값이 빈 문자열이라면 0을 반환한다.
        if (input.isEmpty()) {
            return 0;
        }

        input = input.replace("\\n", NEWLINE);

        String delimiter = addCustomDelimiter(input);
        String numbers = extractNumbers(input);

        return calculateSum(numbers, delimiter);
    }

    private void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력이 null 일 수 없습니다.");
        }
    }

    public String addCustomDelimiter(String input) {
        if (!input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            return DEFAULT_DELIMITER;
        }

        int newlineIdx = input.indexOf(NEWLINE);
        if (newlineIdx == -1) {
            throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
        }

        String customDelimiter = input.substring(2, newlineIdx);
        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
        }
        return Pattern.quote(customDelimiter) + "|" + DEFAULT_DELIMITER;
    }


    public String extractNumbers(String input) {
        if (!input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            return input;
        }

        // 커스텀 구분자면 \n 이후 숫자 부분만 반환
        int newlineIdx = input.indexOf(NEWLINE);
        return input.substring(newlineIdx + 1);
    }

    public int parseNumber(String token) {
        int num;
        try {
            num = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 숫자 형식입니다.");
        }

        if (num < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
        return num;
    }


    public int calculateSum(String numbers, String delimiter) {
        String[] tokens = numbers.split(delimiter);

        int sum = 0;
        for (String token : tokens) {
            sum += parseNumber(token);
        }
        return sum;
    }

}
