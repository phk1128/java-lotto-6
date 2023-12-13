package lotto.domain;

import lotto.constant.ErrorMessage;

public class Money {

    private static final int UNIT = 1000;

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money create(int inputMoney) {
        validateMoneyUnit(inputMoney);
        return new Money(inputMoney);
    }

    private static void validateMoneyUnit(int inputMoney) {
        if(isWrongUnit(inputMoney)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY.getMessage());
        }
    }
    private static boolean isWrongUnit(int inputMoney) {
        return inputMoney % UNIT != 0;
    }

    public int calculateTickets() {
        return amount / UNIT;
    }

    public double calculateRateOfReturn(double totalPrizePrice) {
        return (totalPrizePrice / amount) * 100;
    }
}
