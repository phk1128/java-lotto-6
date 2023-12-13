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

    public static LottoGroup create(List<Lotto> lottos) {
        return new LottoGroup(lottos);
    }


    public List<Lotto> getLottos() {
        return lottos;
    }
}
