package lotto.util.validator;

import static lotto.error.ErrorCode.*;

import java.util.Arrays;
import java.util.regex.Pattern;

import lotto.error.ErrorCode;
import lotto.error.exception.InvalidInputException;

public class LottoInputValidator implements InputValidator {

	private static final String REGEX = "\\d+";
	private static final String DELIMITER = ",";

	private LottoInputValidator() {}

	public static LottoInputValidator getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public void validateNumberFormat(final String input) {
		if (!input.matches(REGEX)) {
			throw new InvalidInputException(INVALID_INPUT);
		}
	}

	@Override
	public void validateNumbersFormat(final String input) {
		if (isWrongInput(input)) {
			throw new InvalidInputException(INVALID_INPUT);
		}
	}

	private boolean isWrongInput(final String input) {
		final String[] split = input.split(DELIMITER);
		return Arrays.stream(split)
			.anyMatch(s -> !s.matches(REGEX));
	}

	private static class BillPughSingleton {
		private static final LottoInputValidator INSTANCE = new LottoInputValidator();
	}
}
