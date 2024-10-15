package lotto.domain;

import java.util.List;

import lotto.util.validator.ListValidator;
import lotto.util.validator.NumberValidator;

public class Winning {

	private final Lotto winningNumbers;
	private final int bonusNumber;

	private Winning(final Lotto winningNumbers, final int bonusNumber,
		final ListValidator<Integer> listValidator, final NumberValidator<Integer> numberValidator) {

		listValidator.validateElementRange(winningNumbers.getNumbers(), numberValidator::validateRange);

		numberValidator.validateRange(bonusNumber)
			.validateContains(bonusNumber, winningNumbers.getNumbers());

		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonusNumber;

	}

	public static Winning of(final Lotto winningNumbers, final int bonusNumber,
		final ListValidator<Integer> listValidator, final NumberValidator<Integer> numberValidator) {
		return new Winning(winningNumbers, bonusNumber, listValidator, numberValidator);
	}

	public boolean isMatchBonusNumber(final int number) {
		return number == bonusNumber;
	}

	public boolean isContainsWinningNumber(final int number) {
		return winningNumbers.isContainsNumber(number);
	}


}
