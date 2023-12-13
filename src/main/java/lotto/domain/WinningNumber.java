package lotto.domain;

import java.util.List;
import lotto.constant.ErrorMessage;

public class WinningNumber {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;

    private final List<Integer> numbers;

    private WinningNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static WinningNumber create(List<Integer> inputNumbers) {
        validateRange(inputNumbers);
        validateDuplicate(inputNumbers);
        return new WinningNumber(inputNumbers);
    }

    private static void validateDuplicate(List<Integer> inputNumbers) {
        if (isDuplicate(inputNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_WINNING_NUMBER.getMessage());
        }
    }

    private static boolean isDuplicate(List<Integer> inputNumbers) {
        return inputNumbers.stream()
                .distinct()
                .count() != inputNumbers.size();
    }

    private static void validateRange(List<Integer> inputNumbers) {
        if (isWrongRange(inputNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getMessage());
        }
    }

    private static boolean isWrongRange(List<Integer> inputNumbers) {
        return inputNumbers.stream()
                .anyMatch(num -> num < MINIMUM || num > MAXIMUM);
    }

    public int countSameNumber(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        return (int) lottoNumbers.stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean isContainsNumber(int number) {
        return numbers.contains(number);
    }
}
