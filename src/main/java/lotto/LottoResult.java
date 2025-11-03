package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result;
    private final double profitRate;

    public LottoResult(List<Lotto> userLottos, WinningLotto winningLotto) {
        this.result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : userLottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }

        long totalPrize = 0;
        for (Rank rank : result.keySet()) {
            totalPrize += (long) rank.getPrizeMoney() * result.get(rank);
        }

        int purchaseAmount = userLottos.size() * 1000;
        this.profitRate = (double) totalPrize / purchaseAmount * 100;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
