package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.LottoGroup;
import lotto.domain.Money;
import lotto.domain.PrizeResultGroup;
import lotto.domain.WinningNumber;
import lotto.service.LottoService;
import lotto.util.Convertor;
import lotto.util.LoopTemplate;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;


    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Money money = requestMoney();
        LottoGroup lottoGroup = lottoService.createLottoGroup(money);
        responseLottoGroup(lottoGroup);
        WinningNumber winningNumber = requestWinningNumber();
        BonusNumber bonusNumber = requestBonusNumber(winningNumber);
        PrizeResultGroup prizeResultGroup = lottoService.createPrizeResultGroup(winningNumber, bonusNumber, lottoGroup);
        double rateOfReturn = lottoService.getRateOfReturn(money, prizeResultGroup);
        responsePrizeResultGroup(prizeResultGroup);
        responseRateOfReturn(rateOfReturn);
    }

    private void responseRateOfReturn(double rateOfReturn) {
        outputView.printRateOfReturn(rateOfReturn);
    }

    private void responsePrizeResultGroup(PrizeResultGroup prizeResultGroup) {
        outputView.printBreakLine();
        outputView.printPrizeResult(prizeResultGroup.getResults());
    }

    private void responseLottoGroup(LottoGroup lottoGroup) {
        outputView.printBreakLine();
        outputView.printLottoGroup(lottoGroup.getLottos());
    }

    private BonusNumber requestBonusNumber(WinningNumber winningNumber) {
        return LoopTemplate.tryCatchTemplate(() ->{
            outputView.printBreakLine();
            outputView.printAskBonusNumber();
            String input = inputView.readInput();
            int inputBonusNumber = Convertor.convertToInt(input);
            return lottoService.createBonusNumber(winningNumber,inputBonusNumber);
        });
    }

    private Money requestMoney() {
        return LoopTemplate.tryCatchTemplate(() -> {
            outputView.printAskMoney();
            String input = inputView.readInput();
            int inputMoney = Convertor.convertToInt(input);
            return lottoService.createMoney(inputMoney);
        });
    }

    private WinningNumber requestWinningNumber() {
        return LoopTemplate.tryCatchTemplate(() -> {
            outputView.printBreakLine();
            outputView.printAskWinningNumber();
            String input = inputView.readInput();
            input = input.replace(" ", "");
            List<Integer> inputWinningNumber = Convertor.convertToIntegerList(Convertor.convertToStringList(input));
            return lottoService.createWinningNumber(inputWinningNumber);
        });
    }
}
