package lotto.constant;

import java.util.Arrays;
import java.util.List;

public enum Prize {

	FIRST(1,6, 2_000_000_000, false),
	SECOND(2,5, 30_000_000, true),
	THIRD(3,5, 1_500_000, false),
	FOURTH(4,4, 50_000, false),
	FIFTH(5, 3, 5_000, false),
	NONE(Integer.MAX_VALUE, 0, 0, false);


	private final int rank;
	private final int matchCount;
	private final long prize;
	private final boolean matchBonus;

	Prize(final int rank, final int matchCount, final long prize, final boolean matchBonus) {
		this.rank = rank;
		this.matchCount = matchCount;
		this.prize = prize;
		this.matchBonus = matchBonus;
	}

	public static Prize findByMatchCountAndBonus(final int matchCount, final boolean matchBonus) {
		return Arrays.stream(Prize.values())
			.filter(prize -> matchCount == prize.getMatchCount())
			.filter(prize -> matchBonus == prize.isMatchBonus())
			.findAny()
			.orElse(NONE);
	}

	public int getRank() {
		return rank;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public long getPrize() {
		return prize;
	}

	public boolean isMatchBonus() {
		return matchBonus;
	}
}
