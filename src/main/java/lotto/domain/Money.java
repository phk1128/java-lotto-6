package lotto.domain;

import static lotto.util.Converter.convertStringToInt;
import static lotto.util.ErrorMessage.INVALID_INPUT;


public class Money {

    private static final int TICKET_PRICE = 1000;
    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money create(String inputMoney) {
        int amount = convertStringToInt(inputMoney);
        validateUnit(amount);
        return new Money(amount);
    }

    private static void validateUnit(int amount) {
        if (isWrongUnit(amount)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static boolean isWrongUnit(int amount) {
        return (amount % TICKET_PRICE) != 0;
    }

    public int calculateTickets() {
        return amount / TICKET_PRICE;
    }

    public double calculateRateOfReturn(double totalPrize) {
        return (totalPrize / amount) * 100;
    }

}
