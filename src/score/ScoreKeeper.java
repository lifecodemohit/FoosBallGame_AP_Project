package score;

import score.interfaces.ScoreKeeperInterface;

public class ScoreKeeper implements ScoreKeeperInterface {

	int teamAScore;
	int teamBScore;

	public ScoreKeeper() {
		this.teamAScore = 0;
		this.teamBScore = 0;
	}

	public void increaseTeamAScore() {
		teamAScore++;
		showScore();
	}

	public void increaseTeamBScore() {
		teamBScore++;
		showScore();
	}

	public void showScore() {
		System.out.println("Team A :  " + teamAScore + "    Team B :   "
				+ teamBScore);
	}

	public int getTeamAScore() {
		return teamAScore;
	}

	public int getTeamBScore() {
		return teamBScore;
	}

}
