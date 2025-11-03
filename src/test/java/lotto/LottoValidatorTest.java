package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoValidatorTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
    void validateLottoNumbersSize() {
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다.")
    void validateLottoNumbersDuplicates() {
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("로또 번호가 1-45 범위를 벗어나면 예외가 발생한다.")
    void validateLottoNumbersRange() {
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void validateBonusNumberDuplicates() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(winningTicket, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("보너스 번호가 1-45 범위를 벗어나면 예외가 발생한다.")
    void validateBonusNumberRange() {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(winningTicket, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    void validatePurchaseAmount() {
        assertThatThrownBy(() -> LottoValidator.validatePurchaseAmount(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
    }
}
