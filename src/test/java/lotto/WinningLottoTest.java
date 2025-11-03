package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 주어졌을 때, WinningLotto 객체를 생성한다.")
    void createWinningLotto() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        assertThat(winningLotto).isNotNull();
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void createWinningLottoWithDuplicateBonusNumber() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;
        assertThatThrownBy(() -> new WinningLotto(winningTicket, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("1등 당첨 테스트")
    void matchFirstPrize() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("2등 당첨 테스트")
    void matchSecondPrize() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("3등 당첨 테스트")
    void matchThirdPrize() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("5등 당첨 테스트")
    void matchFifthPrize() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("미당첨 테스트")
    void matchMiss() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        Lotto userLotto = new Lotto(List.of(8, 9, 10, 11, 12, 13));
        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.MISS);
    }
}
