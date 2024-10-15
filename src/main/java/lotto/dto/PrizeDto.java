package lotto.dto;

import lotto.constant.Prize;

public record PrizeDto(
	int rank,
	int matchCount,
	long prize,
	int count
) {
	public static PrizeDto from(final Prize prize, final int count) {
		return new PrizeDto(
			prize.getRank(),
			prize.getMatchCount(),
			prize.getPrize(),
			count
		);
	}
}
