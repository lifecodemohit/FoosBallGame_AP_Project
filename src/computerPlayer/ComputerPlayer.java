package computerPlayer;

import gameGUI.MyPanel;

import java.util.ArrayList;

import computerPlayer.interfaces.PlayerInterface;

import team.abstractClasses.Player;
import ball.Ball;

public class ComputerPlayer extends Thread implements PlayerInterface {
	Ball ball;
	ArrayList<Player> playersInTeam;
	MyPanel myPanel;

	public boolean gameRunning;
	public int sleeptime = 50;

	public ComputerPlayer(ArrayList<Player> playersInTeam, Ball ball,
			MyPanel myPanel, int sleeptime) {
		this.playersInTeam = playersInTeam;
		this.ball = ball;
		this.myPanel = myPanel;
		this.gameRunning = true;
		this.sleeptime = sleeptime;
		System.out.println("SleepTime : " + sleeptime);
	}

	public void run() {
		while (gameRunning) {
			// System.out.println("Computer Player ");
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			play();
		}
	}

	public void play() {
		if (getMod(playersInTeam.get(0).getyAxisPosition()
				- ball.getyAxisPosition()) > 10)
			if ((playersInTeam.get(0).getyAxisPosition() - ball
					.getyAxisPosition()) > 0)
				for (Player player : playersInTeam)
					player.setMovedUp();
			else
				for (Player player : playersInTeam)
					player.setMovedDown();

	}

	private int getMod(int x) {
		if (x < 0)
			return -1 * x;
		return x;
	}

}
