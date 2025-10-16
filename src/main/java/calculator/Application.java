package calculator;

import camp.nextstep.edu.missionutils.Console;


public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String firstLine = Console.readLine();
        String secondLine = Console.readLine();
        String input = firstLine + "\n" + secondLine;
        StringCalculator stringCalculator = new StringCalculator();

        stringCalculator.calc(input);

    }
}
