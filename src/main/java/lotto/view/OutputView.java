package lotto.view;

import java.util.List;
import lotto.domain.Prize;
import lotto.domain.dto.LottosDto;
import lotto.domain.dto.PrizeResultDto;
import lotto.domain.dto.PrizeResultDtos;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_MONEY = "구입금액을 입력해 주세요.";
    private static final String BUY_LOTTOS = LINE_SEPARATOR + "%d개를 구매했습니다.";
    private static final String ASK_WINNING_NUMBER = LINE_SEPARATOR + "당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_NUMBER = LINE_SEPARATOR + "보너스 번호를 입력해 주세요.";
    private static final String STATISTICS = LINE_SEPARATOR + "당첨 통계" + LINE_SEPARATOR + "---" + LINE_SEPARATOR;
    private static final String PRIZE_RESULT = "%s - %d개";
    private static final String RETURN_OF_RATE = "총 수익률은 %,.1f%%입니다.";


    public void printAskMoney() {
        System.out.println(ASK_MONEY);
    }

    public void printLottos(LottosDto lottosDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(BUY_LOTTOS, lottosDto.quantity()));
        sb.append(LINE_SEPARATOR);
        for (List<Integer> lotto : lottosDto.lottosDto()) {
            sb.append(lotto);
            sb.append(LINE_SEPARATOR);
        }
        System.out.print(sb.toString());
    }

    public void printAskWinningNumber() {
        System.out.println(ASK_WINNING_NUMBER);
    }

    public void printAskBonusNumber() {
        System.out.println(ASK_BONUS_NUMBER);
    }

    public void printPrizeResult(PrizeResultDtos prizeResultDtos, double returnOfRate) {
        StringBuilder sb = new StringBuilder();
        sb.append(STATISTICS);
        for (PrizeResultDto prizeResultDto : prizeResultDtos.prizeResultDtos()) {
            appendPrizeResultMessage(prizeResultDto, sb);
        }
        sb.append(String.format(RETURN_OF_RATE, returnOfRate));
        System.out.print(sb.toString());
    }

    private void appendPrizeResultMessage(PrizeResultDto prizeResultDto, StringBuilder sb) {
        if (prizeResultDto.prize() != Prize.FAIL) {
            String message = prizeResultDto.prize().getMessage();
            int quantity = prizeResultDto.quantity();
            sb.append(String.format(PRIZE_RESULT, message, quantity));
            sb.append(LINE_SEPARATOR);
        }
    }

}
