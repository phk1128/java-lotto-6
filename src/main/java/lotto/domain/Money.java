package lotto.domain;

import static lotto.constant.LottoConstant.*;

import lotto.util.validator.NumberValidator;

public class Money {

	private final int amount;

	private Money(final int amount, final NumberValidator<Integer> numberValidator) {
		numberValidator.validateUnit(amount);
		this.amount = amount;
	}

	public static Money of(final int amount, final NumberValidator<Integer> numberValidator) {
		return new Money(amount, numberValidator);
	}

	public int countTicketCount() {
		return amount / MONEY_UNIT;
	}

	public double calculateRevenue(final double totalPrize) {
		return (totalPrize / amount) * 100;
	}

}
