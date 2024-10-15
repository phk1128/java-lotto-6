package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.util.validator.ListValidator;
import lotto.util.validator.LottoNumberValidator;
import lotto.util.validator.LottoNumbersValidator;
import lotto.util.validator.NumberValidator;

class WinningTest {

	private ListValidator<Integer> listValidator;
	private NumberValidator<Integer> numberValidator;

	@BeforeEach
	void setUp() {
		listValidator = LottoNumbersValidator.getInstance();
		numberValidator = LottoNumberValidator.getInstance();
	}

	@Test
	@DisplayName("당첨 번호와 보너스 번호가 중복되어 예외가 발생한다.")
	void validateDuplicateTest() throws Exception {
		//given
		final Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
		final int bonusNumber = 6;

		//when
		//then
		assertThatThrownBy(() -> Winning.of(winningLotto, bonusNumber, listValidator, numberValidator)).isInstanceOf(
			IllegalArgumentException.class);

	}

	@ParameterizedTest
	@DisplayName("보너스 번호가 1~45 범위가 아니므로 예외가 발생한다.")
	@ValueSource(ints = {0, 46})
	void validateRangeTest(final int number) throws Exception {
		//given
		final Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

		//when

		//then
		assertThatThrownBy(() -> Winning.of(winningLotto, number, listValidator, numberValidator)).isInstanceOf(
			IllegalArgumentException.class);
	}

}