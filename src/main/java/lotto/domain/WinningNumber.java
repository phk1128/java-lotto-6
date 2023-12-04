package lotto.domain;

import static lotto.util.Converter.convertStringListToIntegerList;
import static lotto.util.Converter.convertStringToList;
import static lotto.util.ErrorMessage.INVALID_INPUT;

import java.util.List;
import lotto.util.Converter;

public class WinningNumber {

    private final List<Integer> numbers;

    private WinningNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static WinningNumber create(String inputWinningNumber) {
        inputWinningNumber = removeSpace(inputWinningNumber);
        List<String> winningNumber = convertStringToList(inputWinningNumber);

        validateRange(winningNumber);
        validateSize(winningNumber);
        validateDuplicate(winningNumber);

        List<Integer> convertedWinningNumber = convertStringListToIntegerList(winningNumber);

        return new WinningNumber(convertedWinningNumber);

    }

    public int countSameNumber(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean isDuplicateWithWinningNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private static void validateDuplicate(List<String> winningNumber) {
        if (isDuplicate(winningNumber)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static boolean isDuplicate(List<String> winningNumber) {
        return winningNumber.stream()
                .distinct()
                .count() != winningNumber.size();
    }

    private static void validateSize(List<String> winningNumber) {
        if (isWrongSize(winningNumber)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static boolean isWrongSize(List<String> winningNumber) {
        return winningNumber.size() != 6;
    }

    private static void validateRange(List<String> winningNumber) {
        if (isExceedsRange(winningNumber)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static boolean isExceedsRange(List<String> winningNumber) {
        return winningNumber.stream()
                .map(Converter::convertStringToInt)
                .anyMatch(num -> num < 1 || num > 45);
    }


    private static String removeSpace(String inputWinningNumber) {
        return inputWinningNumber.replace(" ", "");
    }
}
