package lotto.util.generator;

import static lotto.constant.LottoConstant.*;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

public class QuickpickLottoGenerator implements LottoGenerator {

	private QuickpickLottoGenerator() {

	}

	public static QuickpickLottoGenerator getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public Lotto generate() {
		final List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(NUMBER_MIN, NUMBER_MAX, NUMBERS_SIZE));
		return new Lotto(numbers);
	}

	private static class BillPughSingleton {
		private static final QuickpickLottoGenerator INSTANCE = new QuickpickLottoGenerator();
	}

}
