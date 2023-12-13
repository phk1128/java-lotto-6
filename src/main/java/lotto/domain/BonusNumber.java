package lotto.domain;

import lotto.constant.ErrorMessage;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        this.number = number;
    }

    public static BonusNumber create(WinningNumber winningNumber , int inputNumber) {
        validateDuplicate(winningNumber,inputNumber);
        return new BonusNumber(inputNumber);
    }

    private static void validateDuplicate(WinningNumber winningNumber, int inputNumber) {
        if (winningNumber.isContainsNumber(inputNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    public boolean isMatchBonus(Lotto lotto) {
        return lotto.getNumbers().contains(number);
    }
}
