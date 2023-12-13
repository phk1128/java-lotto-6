package lotto.domain;

import lotto.constant.Prize;

public class PrizeResult {

    private final Prize prize;
    private final int quantity;

    private PrizeResult(Prize prize, int quantity) {
        this.prize = prize;
        this.quantity = quantity;
    }

    public static PrizeResult create(Prize prize, int quantity) {
        return new PrizeResult(prize, quantity);
    }

    public int calculatePrizePrice() {
        return prize.getPrizePrice() * quantity;
    }

    public Prize getPrize() {
        return prize;
    }

    public int getQuantity() {
        return quantity;
    }
}
