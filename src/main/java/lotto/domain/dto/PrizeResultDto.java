package lotto.domain.dto;

import java.util.Objects;
import lotto.domain.Prize;

public record PrizeResultDto(
        Prize prize,
        int quantity
) {

    public PrizeResultDto {
        Objects.requireNonNull(prize);
    }

    public static PrizeResultDto from(Prize prize, int quantity) {
        return new PrizeResultDto(prize, quantity);
    }
}
