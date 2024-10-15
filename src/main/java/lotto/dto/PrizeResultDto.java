package lotto.dto;

import java.util.LinkedList;

public record PrizeResultDto(
	LinkedList<PrizeDto> prizeDtos,
	double revenue
) {
	public static PrizeResultDto of(LinkedList<PrizeDto> prizeDtos, double revenue) {
		return new PrizeResultDto(prizeDtos, revenue);
	}
}
