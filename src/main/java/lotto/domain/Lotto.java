package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lotto.util.validator.LottoNumberValidator;
import lotto.util.validator.LottoNumbersValidator;

public class Lotto {
	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		numbers = new ArrayList<>(numbers);
		numbers.sort(Comparator.naturalOrder());
		this.numbers = numbers;
	}

	public int getNumber(final int idx) {
		return numbers.get(idx);
	}

	public int getNumberSize() {
		return numbers.size();
	}

	public boolean isContainsNumber(final int number) {
		return numbers.contains(number);
	}

	public List<Integer> getNumbers() {
		return Collections.unmodifiableList(numbers);
	}

	private void validate(List<Integer> numbers) {
		final LottoNumberValidator lottoNumberValidator = LottoNumberValidator.getInstance();
		final LottoNumbersValidator lottoNumbersValidator = LottoNumbersValidator.getInstance();

		lottoNumbersValidator.validateDuplicate(numbers)
			.validateSize(numbers)
			.validateElementRange(numbers, lottoNumberValidator::validateRange);
	}

}
