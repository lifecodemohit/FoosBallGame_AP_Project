package ball.interfaces;

import gameGUI.ScorePanel;
import team.abstractClasses.Player;

public interface BallInterface {

	public void setTossWinner(int tossWinner);
	void setBallAfterToss(int tossWinner);
	
	public void checkGameFinish();
	public void setScorePanel(ScorePanel scorePanel);
	
	public Player getLastContact() ;
	public void setLastContact(Player lastContact);
	public Player getCurrentContact();
	public void setCurrentContact(Player currentContact);
	public Player getFutureContact();
	public void setFutureContact(Player futureContact);
}
