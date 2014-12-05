package coin;

import coin.enums.CoinFace;
import coin.interfaces.Flip;

public class Coin implements Flip {

	CoinFace cf;

	public CoinFace toss() {
		if (Math.random() > 0.5) {
			cf = CoinFace.TAILS;
		} else {
			cf = CoinFace.HEADS;
		}
		return cf;
	}
}