package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.util.convertor.InputConvertor;
import lotto.util.convertor.LottoInputConvertor;
import lotto.util.generator.LottoGenerator;
import lotto.util.generator.QuickpickLottoGenerator;
import lotto.util.validator.InputValidator;
import lotto.util.validator.ListValidator;
import lotto.util.validator.LottoInputValidator;
import lotto.util.validator.LottoNumberValidator;
import lotto.util.validator.LottoNumbersValidator;
import lotto.util.validator.NumberValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

	public NumberValidator<Integer> numberValidator() {
		return LottoNumberValidator.getInstance();
	}

	public ListValidator<Integer> listValidator() {
		return LottoNumbersValidator.getInstance();
	}

	public InputValidator inputValidator() {
		return LottoInputValidator.getInstance();
	}

	public LottoGenerator lottoGenerator() {
		return QuickpickLottoGenerator.getInstance();
	}

	public InputConvertor<Integer> inputConvertor() {
		return LottoInputConvertor.getInstance();
	}

	public InputView inputView() {
		return new InputView();
	}

	public OutputView outputView() {
		return new OutputView();
	}

	public LottoService lottoService() {
		return LottoService.of(
			listValidator(),
			numberValidator(),
			lottoGenerator()
		);
	}

	public LottoController lottoController() {
		return LottoController.of(
			inputView(),
			outputView(),
			lottoService(),
			inputValidator(),
			inputConvertor()
		);
	}
}
