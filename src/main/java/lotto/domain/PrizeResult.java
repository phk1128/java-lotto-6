package lotto.domain;

import java.util.Map;
import java.util.Map.Entry;

import lotto.constant.Prize;

public class PrizeResult {

	private final Map<Prize, Integer> prizes;

	private PrizeResult(final Map<Prize, Integer> prizes) {
		this.prizes = prizes;
	}

	public static PrizeResult of(final Map<Prize, Integer> prizes) {
		return new PrizeResult(prizes);
	}

	public long calculateTotalPrize() {
		long totalPrize = 0;
		for (Entry<Prize, Integer> entry : prizes.entrySet()) {
			final Prize prize = entry.getKey();
			final Integer count = entry.getValue();
			totalPrize += prize.getPrize() * count;
		}
		return totalPrize;
	}

	public int getPrizeCount(final Prize prize) {
		return prizes.getOrDefault(prize, 0);
	}


}
