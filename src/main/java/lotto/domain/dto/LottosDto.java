package lotto.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public record LottosDto(List<List<Integer>> lottosDto, int quantity) {

    public LottosDto {
        Objects.requireNonNull(lottosDto);
    }

    public static LottosDto from(Lottos lottos) {
        List<List<Integer>> lottosDto = new ArrayList<>();
        for (Lotto lotto : lottos.getNumbers()) {
            lottosDto.add(lotto.getNumbers());
        }
        return new LottosDto(lottosDto, lottosDto.size());
    }
}
