package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrizeResult {

    private final Map<Prize, Integer> result;

    private PrizeResult(Map<Prize, Integer> prizeResult) {
        this.result = prizeResult;
    }

    public static PrizeResult create(
            Lottos lottos,
            WinningNumber winningNumber,
            BonusNumber bonusNumber
    ) {
        Map<Prize, Integer> prizeResult = calculatePrize(lottos, winningNumber, bonusNumber);
        return new PrizeResult(prizeResult);
    }

    public Map<Prize, Integer> getResult() {
        return result;
    }

    private static Map<Prize, Integer> initializePrizeResult() {
        Map<Prize, Integer> initialPrizeResult = new LinkedHashMap<>();
        Prize[] prizes = Prize.values();
        Arrays.sort(prizes, Comparator.comparing((Prize::getPrize)));
        for (Prize prize : prizes) {
            initialPrizeResult.put(prize, 0);
        }
        return initialPrizeResult;
    }

    private static Map<Prize, Integer> calculatePrize(
            Lottos lottos,
            WinningNumber winningNumber,
            BonusNumber bonusNumber) {
        Map<Prize, Integer> prizeResult = initializePrizeResult();
        for (Lotto lotto : lottos.getNumbers()) {
            int sameNumberCount = winningNumber.countSameNumber(lotto);
            boolean bonus = bonusNumber.isContainsBonusNumber(lotto);
            Prize prize = Prize.findBySameNumberCountAndBonus(sameNumberCount, bonus);
            prizeResult.computeIfPresent(prize, (key, value) -> ++value);
        }
        return prizeResult;
    }

    public double calculateTotalPrize() {
        return result.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

}
