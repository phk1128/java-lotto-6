package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constant.Prize;

public class PrizeResultGroup {

    private final List<PrizeResult> results;

    private PrizeResultGroup(List<PrizeResult> results) {
        this.results = results;
    }

    public static PrizeResultGroup create(List<PrizeResult> prizeResults) {
        return new PrizeResultGroup(prizeResults);
    }


    public double calculateTotalPrizePrice() {
        return results.stream()
                .mapToInt(PrizeResult::calculatePrizePrice)
                .sum();
    }

    public List<PrizeResult> getResults() {
        return results;
    }
}
