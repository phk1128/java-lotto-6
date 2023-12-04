package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import lotto.domain.Prize;
import lotto.domain.PrizeResult;

public record PrizeResultDtos(List<PrizeResultDto> prizeResultDtos) {

    public PrizeResultDtos {
        Objects.requireNonNull(prizeResultDtos);
    }

    public static PrizeResultDtos from(PrizeResult prizeResult) {
        List<PrizeResultDto> prizeResultDtos = new ArrayList<>();
        Set<Entry<Prize, Integer>> entries = prizeResult.getResult().entrySet();

        for (Entry<Prize, Integer> entry : entries) {
            PrizeResultDto prizeResultDto = PrizeResultDto.from(entry.getKey(), entry.getValue());
            prizeResultDtos.add(prizeResultDto);
        }
        return new PrizeResultDtos(prizeResultDtos);
    }
}
