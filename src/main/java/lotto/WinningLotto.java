package lotto;

public class WinningLotto {
	private final Lotto winningTicket;
	private final int bonusNumber;
	
	public WinningLotto(Lotto winningTicket, int bonusNumber) {		
		this.winningTicket = winningTicket;
		this.bonusNumber = bonusNumber;
	}	
}
