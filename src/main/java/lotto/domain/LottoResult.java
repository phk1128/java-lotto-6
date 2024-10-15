package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.constant.Prize;

public class LottoResult {

	private final List<Lotto> lottos;
	private final Winning winning;

	private LottoResult(final List<Lotto> lottos, final Winning winning) {
		this.lottos = lottos;
		this.winning = winning;
	}

	public static LottoResult of(final List<Lotto> lottos, final Winning winning) {
		return new LottoResult(lottos, winning);
	}

	public Map<Prize, Integer> calculatePrizes() {
		Map<Prize, Integer> prizes = new EnumMap<>(Prize.class);
		for (Lotto lotto : lottos) {
			final int count = countContainsWinning(lotto);
			final boolean matchBonus = isMatchBonus(lotto);
			final Prize prize = Prize.findByMatchCountAndBonus(count, matchBonus);
			prizes.put(prize, prizes.getOrDefault(prize, 0) + 1);
		}
		return prizes;
	}

	private int countContainsWinning(final Lotto lotto) {
		int count = 0;
		for (int idx = 0; idx < lotto.getNumberSize(); idx++) {
			final int number = lotto.getNumber(idx);
			if (winning.isContainsWinningNumber(number)) {
				count++;
			}
		}
		return count;
	}

	private boolean isMatchBonus(final Lotto lotto) {
		for (int idx = 0; idx < lotto.getNumberSize(); idx++) {
			final int number = lotto.getNumber(idx);
			if (winning.isMatchBonusNumber(number)) {
				return true;
			}
		}
		return false;
	}


}
