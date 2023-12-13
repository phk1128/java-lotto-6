package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGroup {

    private final List<Lotto> lottos;

    private LottoGroup(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoGroup create(Money money) {
        int tickets = money.calculateTickets();
        List<Lotto> lottos = generateLottos(tickets);
        return new LottoGroup(lottos);
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

}
