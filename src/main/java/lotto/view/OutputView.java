package lotto.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lotto.domain.PrizeResult;
import lotto.dto.LottosDto;
import lotto.dto.PrizeDto;
import lotto.dto.PrizeResultDto;

public class OutputView {

	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String ASK_MONEY = "구입금액을 입력해 주세요.";
	private static final String LOTTOS_TITLE = "%d개를 구매했습니다.";
	private static final String ASK_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
	private static final String ASK_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
	private static final String PRIZE_RESULT_TITLE = "당첨 통계";
	private static final String PRIZE_RESULT_LINE = "---";
	private static final String PRIZE_GENERAL_MESSAGE = "%d개 일치 (%,d원) - %d 개";
	private static final String PRIZE_SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
	private static final String REVENUE = "총 수익률은 %,.1f% 입니다.";
	private static final Map<Integer, String> PRIZE_MESSAGE = Map.of(
		1, PRIZE_GENERAL_MESSAGE,
		2, PRIZE_SECOND_MESSAGE,
		3, PRIZE_GENERAL_MESSAGE,
		4, PRIZE_GENERAL_MESSAGE,
		5, PRIZE_GENERAL_MESSAGE
	);

	public void printAskMoney() {
		System.out.println(ASK_MONEY);
	}

	public void printAskWinningNumbers() {
		System.out.println(LINE_SEPARATOR + ASK_WINNING_NUMBERS);
	}

	public void printAskBonusNumber() {
		System.out.println(LINE_SEPARATOR + ASK_BONUS_NUMBER);
	}

	public void printLottosTitle(final int count) {
		final String format = String.format(LOTTOS_TITLE, count);
		System.out.println(LINE_SEPARATOR + format);
	}

	public void printLottos(final LottosDto lottosDto) {
		final List<List<Integer>> lottos = lottosDto.lottos();
		StringBuilder sb = new StringBuilder();
		for (List<Integer> lotto : lottos) {
			sb.append(lotto.toString())
				.append(LINE_SEPARATOR);
		}
		System.out.print(sb);
	}

	public void printPrizeResult(final PrizeResultDto prizeResultDto) {
		final LinkedList<PrizeDto> prizeDtos = prizeResultDto.prizeDtos();
		StringBuilder sb = new StringBuilder();
		sb.append(PRIZE_RESULT_TITLE)
			.append(LINE_SEPARATOR)
			.append(PRIZE_RESULT_LINE)
			.append(LINE_SEPARATOR);
		final String prizeMessage = getPrizeMessage(prizeDtos);
		sb.append(prizeMessage);
		final double revenue = prizeResultDto.revenue();
		final String format = String.format(REVENUE, revenue);
		sb.append(format);
		System.out.println(sb);
	}

	private String getPrizeMessage(final LinkedList<PrizeDto> prizeDtos) {
		StringBuilder sb = new StringBuilder();
		for (PrizeDto prizeDto : prizeDtos) {
			final int rank = prizeDto.rank();
			final String message = PRIZE_MESSAGE.get(rank);
			final String format = String.format(message, prizeDto.matchCount(), prizeDto.prize(), prizeDto.count());
			sb.append(format)
				.append(LINE_SEPARATOR);
		}
		return sb.toString();
	}

}
