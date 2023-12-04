package lotto.domain;

import java.util.Arrays;
import java.util.Objects;

public enum Prize {

    SECOND(5, 30000000, true, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FAIL(0, 0, false, ""),
    FIFTH(3, 5000, false, "3개 일치 (5,000원)"),
    FOURTH(4, 50000, false, "4개 일치 (50,000원)"),
    THIRD(5, 1500000, false, "5개 일치 (1,500,000원)"),
    FIRST(6, 2000000000, false, "6개 일치 (2,000,000,000원)");


    private final int sameNumberCount;
    private final int prize;
    private final boolean bonus;
    private final String message;

    Prize(int sameNumberCount, int prize, boolean bonus, String message) {
        this.sameNumberCount = sameNumberCount;
        this.prize = prize;
        this.bonus = bonus;
        this.message = message;
    }

    public static Prize findBySameNumberCountAndBonus(int sameNumberCount, boolean bonus) {
        return Arrays.stream(Prize.values())
                .filter(prize -> Objects.equals(prize.sameNumberCount, sameNumberCount))
                .filter(prize -> Objects.equals(prize.bonus, bonus))
                .findFirst()
                .orElse(Prize.FAIL);
    }

    public int getSameNumberCount() {
        return sameNumberCount;
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
