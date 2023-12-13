package lotto.constant;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Prize {
    FAIL(0, false, 0, "실패"),
    FIFTH(3, false, 5_000, "3개 일치 (5,000원)"),
    FOURTH(4, false, 50_000, "4개 일치 (50,000원)"),
    THIRD(5, false, 1_500_000, "5개 일치 (1,500,000원)"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FIRST(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원)");

    private final int sameNumberCount;
    private final boolean bonusMatching;
    private final int prizePrice;
    private final String message;

    Prize(int sameNumberCount, boolean bonusMatching, int prizePrice, String message) {
        this.sameNumberCount = sameNumberCount;
        this.bonusMatching = bonusMatching;
        this.prizePrice = prizePrice;
        this.message = message;
    }

    public static Prize findBySameNumberCountAndBonusMatching(int sameNumberCount, boolean bonusMatching) {
        return Arrays.stream(Prize.values())
                .filter(prize -> Objects.equals(sameNumberCount, prize.sameNumberCount))
                .filter(prize -> Objects.equals(bonusMatching, prize.bonusMatching))
                .findAny()
                .orElse(Prize.FAIL);
    }

    public static List<Prize> getAllPrize() {
        return Arrays.stream(Prize.values()).toList();
    }

    public int getSameNumberCount() {
        return sameNumberCount;
    }

    public boolean isBonusMatching() {
        return bonusMatching;
    }

    public int getPrizePrice() {
        return prizePrice;
    }

    public String getMessage() {
        return message;
    }
}
