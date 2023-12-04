package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> numbers;

    private Lottos(List<Lotto> numbers) {
        this.numbers = numbers;
    }

    public static Lottos create(int count) {
        List<Lotto> lottos = generateLotto(count);
        return new Lottos(lottos);
    }

    public List<Lotto> getNumbers() {
        return numbers;
    }

    private static List<Lotto> generateLotto(int count) {
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < count) {
            List<Integer> number = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(number);
            Lotto lotto = new Lotto(number);
            lottos.add(lotto);
        }
        return lottos;
    }

}
