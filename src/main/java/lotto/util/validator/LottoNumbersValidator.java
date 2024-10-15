package lotto.util.validator;

import static lotto.constant.LottoConstant.*;

import java.util.List;

import lotto.error.ErrorCode;
import lotto.error.exception.InvalidNumbersException;

public class LottoNumbersValidator implements ListValidator<Integer> {

	private LottoNumbersValidator() {}

	public static LottoNumbersValidator getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public ListValidator<Integer> validateSize(final List<Integer> numbers) {
		if (isWrongSize(numbers)) {
			throw new InvalidNumbersException(ErrorCode.INVALID_NUMBERS_SIZE);
		}
		return this;
	}

	@Override
	public ListValidator<Integer> validateDuplicate(final List<Integer> numbers) {
		if (isDuplicate(numbers)) {
			throw new InvalidNumbersException(ErrorCode.DUPLICATE_NUMBER);
		}
		return this;
	}

	@Override
	public ListValidator<Integer> validateElementRange(final List<Integer> numbers,
		final ValidatorFunction<Integer> validatorFunction) {
		for (Integer number : numbers) {
			validatorFunction.validate(number);
		}

		return this;
	}

	private boolean isWrongSize(final List<Integer> numbers) {
		return numbers.size() != NUMBERS_SIZE;
	}

	private boolean isDuplicate(final List<Integer> numbers) {
		return numbers.stream()
			.distinct()
			.count() != numbers.size();
	}

	private static class BillPughSingleton {
		private static final LottoNumbersValidator INSTANCE = new LottoNumbersValidator();
	}

}
