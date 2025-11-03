package lotto;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        try {
            int purchaseAmount = getPurchaseAmount();
            LottoGenerator lottoGenerator = new LottoGenerator();
            List<Lotto> userLottos = lottoGenerator.purchase(purchaseAmount);
            printPurchasedLottos(userLottos);

            WinningLotto winningLotto = getWinningLotto();

            LottoResult lottoResult = new LottoResult(userLottos, winningLotto);
            printResult(lottoResult);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getPurchaseAmount() {
        while (true) {
            try {
                String input = InputLotto.readPurchaseAmount();
                int amount = Integer.parseInt(input);
                LottoValidator.validatePurchaseAmount(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printPurchasedLottos(List<Lotto> userLottos) {
        System.out.println("\n" + userLottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : userLottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    private static WinningLotto getWinningLotto() {
        Lotto winningTicket = getWinningTicket();
        int bonusNumber = getBonusNumber(winningTicket);
        return new WinningLotto(winningTicket, bonusNumber);
    }

    private static Lotto getWinningTicket() {
        while (true) {
            try {
                String input = InputLotto.readWinningNumbers();
                List<Integer> numbers = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getBonusNumber(Lotto winningTicket) {
        while (true) {
            try {
                String input = InputLotto.readBonusNumber();
                int bonusNumber = Integer.parseInt(input);
                LottoValidator.validateBonusNumber(winningTicket, bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        Map<Rank, Integer> result = lottoResult.getResult();
        DecimalFormat formatter = new DecimalFormat("###,###");

        System.out.println("3개 일치 (" + formatter.format(Rank.FIFTH.getPrizeMoney()) + "원) - " + result.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (" + formatter.format(Rank.FOURTH.getPrizeMoney()) + "원) - " + result.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (" + formatter.format(Rank.THIRD.getPrizeMoney()) + "원) - " + result.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (" + formatter.format(Rank.SECOND.getPrizeMoney()) + "원) - " + result.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (" + formatter.format(Rank.FIRST.getPrizeMoney()) + "원) - " + result.get(Rank.FIRST) + "개");

        System.out.println("총 수익률은 " + String.format("%.1f", lottoResult.getProfitRate()) + "%입니다.");
    }
}
