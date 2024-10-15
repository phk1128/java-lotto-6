package lotto.controller;

import java.util.List;
import java.util.function.Supplier;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.PrizeResult;
import lotto.domain.Winning;
import lotto.dto.LottosDto;
import lotto.dto.PrizeResultDto;
import lotto.error.AppException;
import lotto.service.LottoService;
import lotto.util.convertor.InputConvertor;
import lotto.util.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

	private final InputView inputView;
	private final OutputView outputView;
	private final LottoService lottoService;
	private final InputValidator inputValidator;
	private final InputConvertor<Integer> inputConvertor;

	private LottoController(final InputView inputView, final OutputView outputView, final LottoService lottoService,
		final InputValidator inputValidator, final InputConvertor<Integer> inputConvertor) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.lottoService = lottoService;
		this.inputValidator = inputValidator;
		this.inputConvertor = inputConvertor;
	}

	public static LottoController of(final InputView inputView, final OutputView outputView,
		final LottoService lottoService,
		final InputValidator inputValidator,
		final InputConvertor<Integer> inputConvertor) {
		return new LottoController(inputView, outputView, lottoService, inputValidator, inputConvertor);
	}

	public void run() {
		final Money money = requestMoney();
		final List<Lotto> lottos = lottoService.generateLottos(money);
		responseLottos(money.countTicketCount(), lottos);
		final Winning winning = requestWinning();
		final LottoResult lottoResult = lottoService.generateLottoResult(lottos, winning);
		final PrizeResult prizeResult = lottoService.generatePrizeResult(lottoResult);
		final PrizeResultDto prizeResultDto = lottoService.getPrizeResult(prizeResult, money);
		responsePrizeResult(prizeResultDto);
	}

	private void responsePrizeResult(final PrizeResultDto prizeResultDto) {
		outputView.printPrizeResult(prizeResultDto);
	}

	private void responseLottos(final int count, final List<Lotto> lottos) {
		final LottosDto lottosDto = LottosDto.from(count, lottos);
		outputView.printLottosTitle(count);
		outputView.printLottos(lottosDto);
	}

	private Money requestMoney() {
		return LoopTemplate.tryCatch(() -> {
			outputView.printAskMoney();
			final String input = inputView.readInput();
			inputValidator.validateNumberFormat(input);
			final Integer amount = inputConvertor.covert(input);
			return lottoService.generateMoney(amount);
		});
	}

	private Winning requestWinning() {
		final Lotto winningNumbers = requestWinningNumbers();
		return LoopTemplate.tryCatch(() -> {
			final Integer bonusNumber = requestBonusNumber();
			return lottoService.generateWinning(winningNumbers, bonusNumber);
		});
	}

	private Lotto requestWinningNumbers() {
		return LoopTemplate.tryCatch(() -> {
			outputView.printAskWinningNumbers();
			final String input = inputView.readInput();
			inputValidator.validateNumbersFormat(input);
			final List<Integer> numbers = inputConvertor.covertToList(input);
			return new Lotto(numbers);
		});
	}

	private Integer requestBonusNumber() {
		return LoopTemplate.tryCatch(() -> {
			outputView.printAskBonusNumber();
			final String input = inputView.readInput();
			inputValidator.validateNumberFormat(input);
			return inputConvertor.covert(input);
		});
	}

	static class LoopTemplate {

		static <T> T tryCatch(Supplier<T> supplier) {
			while (true) {
				try {
					return supplier.get();
				} catch (AppException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
