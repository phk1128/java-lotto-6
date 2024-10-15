package lotto.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lotto.constant.Prize;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.PrizeResult;
import lotto.domain.Winning;
import lotto.dto.PrizeDto;
import lotto.dto.PrizeResultDto;
import lotto.util.validator.ListValidator;
import lotto.util.generator.LottoGenerator;
import lotto.util.validator.NumberValidator;

public class LottoService {

	private final ListValidator<Integer> listValidator;
	private final NumberValidator<Integer> numberValidator;
	private final LottoGenerator lottoGenerator;

	private LottoService(final ListValidator<Integer> listValidator, final NumberValidator<Integer> numberValidator,
		final LottoGenerator lottoGenerator) {
		this.listValidator = listValidator;
		this.numberValidator = numberValidator;
		this.lottoGenerator = lottoGenerator;
	}

	public static LottoService of(final ListValidator<Integer> listValidator,
		final NumberValidator<Integer> numberValidator, final LottoGenerator lottoGenerator) {
		return new LottoService(listValidator, numberValidator, lottoGenerator);
	}

	public Money generateMoney(final int amount) {
		return Money.of(amount, numberValidator);
	}

	public List<Lotto> generateLottos(final Money money) {
		List<Lotto> lottos = new ArrayList<>();
		final int count = money.countTicketCount();
		for (int i = 0; i < count; i++) {
			final Lotto lotto = lottoGenerator.generate();
			lottos.add(lotto);
		}
		return lottos;
	}

	public LottoResult generateLottoResult(final List<Lotto> lottos, final Winning winning) {
		return LottoResult.of(lottos, winning);
	}

	public Winning generateWinning(final Lotto winningNumbers, final int bonusNumber) {
		return Winning.of(winningNumbers, bonusNumber, listValidator, numberValidator);
	}

	public PrizeResult generatePrizeResult(final LottoResult lottoResult) {
		final Map<Prize, Integer> prizes = lottoResult.calculatePrizes();
		return PrizeResult.of(prizes);
	}

	public PrizeResultDto getPrizeResult(final PrizeResult prizeResult, final Money money) {
		final LinkedList<PrizeDto> prizeDtos = getPrizeDtos(prizeResult);
		final long totalPrize = prizeResult.calculateTotalPrize();
		final double revenue = money.calculateRevenue(totalPrize);
		return PrizeResultDto.of(prizeDtos, revenue);
	}

	private LinkedList<PrizeDto> getPrizeDtos(final PrizeResult prizeResult) {
		LinkedList<PrizeDto> prizeDtos = new LinkedList<>();
		for (Prize prize : Prize.values()) {
			final int count = prizeResult.getPrizeCount(prize);
			final PrizeDto prizeDto = PrizeDto.from(prize, count);
			prizeDtos.add(prizeDto);
		}
		prizeDtos.sort((d1, d2) -> d2.rank() - d1.rank());

		return (LinkedList<PrizeDto>)prizeDtos.subList(1, prizeDtos.size());
	}

}
