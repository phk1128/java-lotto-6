package lotto.controller;

import static lotto.util.LoopTemplate.tryCatchLoop;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PrizeResult;
import lotto.domain.WinningNumber;
import lotto.dto.LottosDto;
import lotto.dto.PrizeResultDtos;
import lotto.service.LottoService;
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
        Lottos lottos = lottoService.createLottos(money);
        responseLottos(lottos);
        WinningNumber winningNumber = requestWinningNumber();
        BonusNumber bonusNumber = requestBonusNumber(winningNumber);
        PrizeResult prizeResult = lottoService.createPrizeResult(lottos, winningNumber, bonusNumber);
        double returnOfRate = lottoService.calculateReturnOfRate(prizeResult, money);
        responsePrizeResult(prizeResult, returnOfRate);

    }

    private void responsePrizeResult(PrizeResult prizeResult, double returnOfRate) {
        PrizeResultDtos prizeResultDtos = PrizeResultDtos.from(prizeResult);
        outputView.printPrizeResult(prizeResultDtos, returnOfRate);
    }

    private void responseLottos(Lottos lottos) {
        LottosDto lottosDto = LottosDto.from(lottos);
        outputView.printLottos(lottosDto);
    }

    private Money requestMoney() {
        return tryCatchLoop(() -> {
            outputView.printAskMoney();
            String inputMoney = inputView.readInput();
            return lottoService.createMoney(inputMoney);
        });
    }

    private WinningNumber requestWinningNumber() {
        return tryCatchLoop(() -> {
            outputView.printAskWinningNumber();
            String inputWinningNumber = inputView.readInput();
            return lottoService.createWinningNumber(inputWinningNumber);
        });
    }

    private BonusNumber requestBonusNumber(WinningNumber winningNumber) {
        return tryCatchLoop(() -> {
            outputView.printAskBonusNumber();
            String inputBonusNumber = inputView.readInput();
            return lottoService.createBonusNumber(inputBonusNumber, winningNumber);
        });
    }


}
