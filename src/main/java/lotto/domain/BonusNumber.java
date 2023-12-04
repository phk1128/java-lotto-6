package lotto.domain;

import static lotto.util.Converter.convertStringToInt;
import static lotto.util.ErrorMessage.INVALID_INPUT;

public class BonusNumber {

    private final int number;

    private BonusNumber(int number) {
        this.number = number;
    }

    public static BonusNumber create(String inputBonusNumber, WinningNumber winningNumber) {
        int number = convertStringToInt(inputBonusNumber);
        validateRange(number);
        validateDuplicate(number, winningNumber);

        return new BonusNumber(number);
    }

    public boolean isContainsBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(number);

    }

    private static void validateRange(int number) {
        if (isWrongRange(number)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static void validateDuplicate(int number, WinningNumber winningNumber) {
        if (winningNumber.isDuplicateWithWinningNumber(number)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static boolean isWrongRange(int number) {
        return number < 1 || number > 45;
    }
}
