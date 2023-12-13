package lotto.view;

import java.util.List;
import java.util.Objects;
import lotto.constant.Prize;
import lotto.domain.Lotto;
import lotto.domain.PrizeResult;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    public void printAskMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printAskWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printAskBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printLottoGroup(List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append(lotto.getNumbers());
            sb.append(LINE_SEPARATOR);
        }
        System.out.println(String.format("%d개를 구매했습니다.", lottos.size()));
        System.out.print(sb.toString());
    }

    public void printPrizeResult(List<PrizeResult> prizeResults) {
        StringBuilder sb = new StringBuilder();
        for (PrizeResult prizeResult : prizeResults) {
            Prize prize = prizeResult.getPrize();
            if (!Objects.equals(prize, Prize.FAIL)) {
                sb.append(String.format("%s - %d개", prize.getMessage(), prizeResult.getQuantity()));
                sb.append(LINE_SEPARATOR);
            }
            System.out.println("당첨 통계");
            System.out.println("---");
            System.out.print(sb.toString());
        }
    }

    public void printRateOfReturn(double rateOfReturn) {
        String message = String.format("총 수익률은 %,.1f%%입니다.", rateOfReturn);
        System.out.print(message);
    }

    public void printBreakLine() {
        System.out.println();
    }
}
