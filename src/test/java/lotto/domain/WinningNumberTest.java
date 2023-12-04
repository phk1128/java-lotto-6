package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumberTest {

    @DisplayName("잘못된 당첨번호 입력으로 오류가 발생한다.")
    @ValueSource(strings = {"1,2,3,4,0,11", "1,2", "1,2,3,4,5,6,7", "ㅁ,1,2,3,4,5", "1,2,3,4,5,47"})
    @ParameterizedTest
    void validateWinningNumber(String input) throws Exception {
        //given

        //when

        //then
        assertThatThrownBy(() -> WinningNumber.create(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}