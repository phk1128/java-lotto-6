package lotto.util.validator;

import static lotto.constant.LottoConstant.*;

import java.util.List;

import lotto.error.ErrorCode;
import lotto.error.exception.InvalidNumberException;

public class LottoNumberValidator implements NumberValidator<Integer> {

	private LottoNumberValidator() {}


	public static LottoNumberValidator getInstance() {
		return BillPughSingleton.INSTANCE;
	}


	@Override
	public NumberValidator<Integer> validateRange(final Integer number) {
		if (isWrongRange(number)) {
			throw new InvalidNumberException(ErrorCode.INVALID_NUMBER_RANGE);
		}

		return this;

	}

	@Override
	public NumberValidator<Integer> validateUnit(final Integer number) {
		if (isWrongUnit(number)) {
			throw new InvalidNumberException(ErrorCode.INVALID_NUMBER_UNIT);
		}
		return this;
	}

	@Override
	public NumberValidator<Integer> validateContains(final Integer number, final List<Integer> numbers) {
		if (isContains(number, numbers)) {
			throw new InvalidNumberException(ErrorCode.DUPLICATE_NUMBER);
		}
		return this;
	}

	private boolean isContains(final Integer number, final List<Integer> numbers) {
		return numbers.contains(number);
	}

	private boolean isWrongRange(final Integer number) {
		return number < NUMBER_MIN ||
			number > NUMBER_MAX;
	}

	private boolean isWrongUnit(final Integer number) {
		return number <= 0 ||
			number % MONEY_UNIT != 0;
	}

	// 싱글톤 인스턴스를 담는 정적 내부 클래스
	private static class BillPughSingleton {
		private static final LottoNumberValidator INSTANCE = new LottoNumberValidator();
	}
}
