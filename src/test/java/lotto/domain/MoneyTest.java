package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("수익률 계산 테스트")
    @Test
    void calculateReturnOfRate() throws Exception {
        //given
        String input = "8000";
        //when
        Money money = Money.create(input);
        //then
        System.out.println(money.calculateRateOfReturn(5000.0));
    }
}