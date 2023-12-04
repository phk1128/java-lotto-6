package lotto.service;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PrizeResult;
import lotto.domain.WinningNumber;

public class LottoService {

    public Money createMoney(String inputMoney) {
        return Money.create(inputMoney);
    }

    public Lottos createLottos(Money money) {
        int tickets = money.calculateTickets();
        return Lottos.create(tickets);
    }

    public WinningNumber createWinningNumber(String inputWinningNumber) {
        return WinningNumber.create(inputWinningNumber);
    }

    public BonusNumber createBonusNumber(String inputBonusNumber, WinningNumber winningNumber) {
        return BonusNumber.create(inputBonusNumber, winningNumber);
    }

    public PrizeResult createPrizeResult(Lottos lottos, WinningNumber winningNumber, BonusNumber bonusNumber) {
        return PrizeResult.create(lottos, winningNumber, bonusNumber);
    }

    public double calculateReturnOfRate(PrizeResult prizeResult, Money money) {
        double totalPrize = prizeResult.calculateTotalPrize();
        return money.calculateRateOfReturn(totalPrize);
    }
}
