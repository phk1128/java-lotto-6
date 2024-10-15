package lotto.util.validator;

import java.util.List;

public interface NumberValidator<T> {

	NumberValidator<T> validateRange(final T number);
	NumberValidator<T> validateUnit(final T number);
	NumberValidator<T> validateContains(final T number, final List<T> numbers);

}
