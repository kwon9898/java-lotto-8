package lotto;

public class WinningLotto {
	private final Lotto winningTicket;
	private final int bonusNumber;
	
	public WinningLotto(Lotto winningTicket, int bonusNumber) {
		validate(winningTicket, bonusNumber);
		this.winningTicket = winningTicket;
		this.bonusNumber = bonusNumber;
	}

	        private void validate(Lotto winningTicket, int bonusNumber) {
	            LottoValidator.validateBonusNumber(winningTicket, bonusNumber);
	        }	
	    public Rank match(Lotto userLotto) {
	        long matchCount = userLotto.getNumbers().stream()
	                .filter(winningTicket.getNumbers()::contains)
	                .count();
	
	        boolean bonusMatch = userLotto.getNumbers().contains(bonusNumber);
	
	        if (matchCount == 6) {
	            return Rank.FIRST;
	        }
	        if (matchCount == 5 && bonusMatch) {
	            return Rank.SECOND;
	        }
	        if (matchCount == 5) {
	            return Rank.THIRD;
	        }
	        if (matchCount == 4) {
	            return Rank.FOURTH;
	        }
	        if (matchCount == 3) {
	            return Rank.FIFTH;
	        }
	        return Rank.MISS;
	    }
}
