package lotto.util.convertor;

import java.util.Arrays;
import java.util.List;

public class LottoInputConvertor implements InputConvertor<Integer> {

	private static final String DELIMITER = ",";

	private LottoInputConvertor() {

	}

	public static LottoInputConvertor getInstance() {
		return BillPughSingleton.INSTANCE;
	}

	@Override
	public List<Integer> covertToList(final String str) {
		final String[] split = str.split(DELIMITER);
		return Arrays.stream(split)
			.map(Integer::parseInt)
			.toList();
	}

	@Override
	public Integer covert(final String str) {
		return Integer.parseInt(str);
	}

	private static class BillPughSingleton {
		private static final LottoInputConvertor INSTANCE = new LottoInputConvertor();
	}
}
