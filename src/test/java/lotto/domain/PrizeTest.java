package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @DisplayName("당첨 번호와 보너스 번호 일치여부로 상금을 찾는다.")
    @Test
    void findBydSameNumberCountAndBonus() throws Exception {
        //given
        int sameNumberCount = 4;
        boolean bonus = false;
        //when
        //then
        assertThat(Prize.findBySameNumberCountAndBonus(sameNumberCount, bonus))
                .isEqualTo(Prize.FOURTH);
    }

    @DisplayName("정렬테스트")
    @Test
    void sortTest() throws Exception {
        //given
        Prize[] prizes = Prize.values();
        Arrays.sort(prizes, Comparator.comparing(Prize::getPrize));
        Map<Prize, Integer> test = new EnumMap<>(Prize.class);
        //when
        for (Prize prize : prizes) {
            test.put(prize, 0);
        }
        List<Integer> test2 = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        test2.sort(Comparator.comparing(Integer::intValue));
        //then
        System.out.println(Arrays.toString(prizes));
        System.out.println(test.toString());
    }

}