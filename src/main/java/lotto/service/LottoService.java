package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constant.Prize;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.Money;
import lotto.domain.PrizeResult;
import lotto.domain.PrizeResultGroup;
import lotto.domain.WinningNumber;

public class LottoService {

    public Money createMoney(int inputMoney) {
        return Money.create(inputMoney);
    }

    public LottoGroup createLottoGroup(Money money) {
        List<Lotto> lottos = generateLottos(money.calculateTickets());
        return LottoGroup.create(lottos);
    }

    public WinningNumber createWinningNumber(List<Integer> inputNumbers) {
        return WinningNumber.create(inputNumbers);
    }

    public BonusNumber createBonusNumber(WinningNumber winningNumber, int inputNumber) {
        return BonusNumber.create(winningNumber, inputNumber);
    }

    public PrizeResultGroup createPrizeResultGroup(
            WinningNumber winningNumber,
            BonusNumber bonusNumber,
            LottoGroup lottoGroup) {
        List<PrizeResult> prizeResults = generatePrizeResult(winningNumber, bonusNumber, lottoGroup);
        return PrizeResultGroup.create(prizeResults);
    }

    public double getRateOfReturn(Money money, PrizeResultGroup prizeResultGroup) {
        double totalPrizePrice = prizeResultGroup.calculateTotalPrizePrice();
        return money.calculateRateOfReturn(totalPrizePrice);
    }


    private static List<Lotto> generateLottos (int tickets) {
        List<Lotto> lottos = new ArrayList<>();
        addLotto(lottos, tickets);
        return lottos;
    }

    private static void addLotto(List<Lotto> lottos, int tickets) {
        if (tickets == 0) {
            return;
        }
        tickets--;
        List<Integer> randomNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(randomNumbers);
        lottos.add(new Lotto(randomNumbers));
        addLotto(lottos, tickets);
    }

    private static List<PrizeResult> generatePrizeResult(
            WinningNumber winningNumber,
            BonusNumber bonusNumber,
            LottoGroup lottoGroup) {
        List<PrizeResult> prizeResults = new ArrayList<>();
        List<Prize> prizes = calculatePrize(winningNumber, bonusNumber, lottoGroup);
        for (Prize prize : Prize.getAllPrize()) {
            int quantity = Collections.frequency(prizes, prize);
            PrizeResult prizeResult = PrizeResult.create(prize, quantity);
            prizeResults.add(prizeResult);
        }
        return prizeResults;
    }

    private static List<Prize> calculatePrize(
            WinningNumber winningNumber,
            BonusNumber bonusNumber,
            LottoGroup lottoGroup) {
        List<Prize> prizes = new ArrayList<>();
        for (Lotto lotto : lottoGroup.getLottos()) {
            int sameNumberCount = winningNumber.countSameNumber(lotto);
            boolean matchBonus = bonusNumber.isMatchBonus(lotto);
            Prize prize = Prize.findBySameNumberCountAndBonusMatching(sameNumberCount, matchBonus);
            prizes.add(prize);
        }
        return prizes;
    }
}
