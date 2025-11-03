package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class LottoGenerator {
	
	public List<Lotto> purchase(int money){
		int lottoTicket = money / 1000;
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < lottoTicket; i++) {
			lottos.add(generateTicket());
		}
		return lottos;
	}
	
	private Lotto generateTicket() {
		List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
		List<Integer> sortNumbers = new ArrayList<>(numbers);
		Collections.sort(sortNumbers);
		return new Lotto(sortNumbers);
	}
}
