package lotto.dto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;

public record LottosDto(
	int count,
	List<List<Integer>> lottos
) {

	public static LottosDto from(final int count, final List<Lotto> lottos) {
		return new LottosDto(
			count,
			lottos.stream()
				.map(Lotto::getNumbers)
				.toList()
		);
	}
}
