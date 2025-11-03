package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("로또 결과가 정확하게 계산되는지 테스트한다.")
    void calculateResult() {
        // given
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);

        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)), // 5등
                new Lotto(List.of(1, 2, 3, 4, 11, 12)), // 4등
                new Lotto(List.of(1, 2, 3, 4, 5, 12)), // 3등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),  // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),  // 1등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 미당첨
        );

        // when
        LottoResult lottoResult = new LottoResult(userLottos, winningLotto);

        // then
        Map<Rank, Integer> result = lottoResult.getResult();
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.MISS)).isEqualTo(1);

        double expectedProfitRate = (double) (5000 + 50000 + 1500000 + 30000000 + 2000000000) / (6 * 1000) * 100;
        assertThat(lottoResult.getProfitRate()).isEqualTo(expectedProfitRate);
    }
}
