package lotto.util.validator;

import java.util.List;

public interface ListValidator<T> {

	ListValidator<T> validateDuplicate(List<T> list);
	ListValidator<T> validateSize(List<T> list);
	ListValidator<T> validateElementRange(List<T> list, ValidatorFunction<T> validatorFunction);

	@FunctionalInterface
	interface ValidatorFunction<T> {
		void validate(T element);
	}

}

