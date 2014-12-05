package keyboardInputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import keyboardInputs.interfaces.SetPlayersInTeam;

import team.abstractClasses.Player;

public class MyKeyListener implements KeyListener, SetPlayersInTeam {

	ArrayList<Player> playersInTeamA;
	ArrayList<Player> playersInTeamB;

	public void keyPressed(KeyEvent k) {

		switch (k.getKeyCode()) {
		case KeyEvent.VK_UP:
		// up arrow
		{
			if (playersInTeamA != null)
				for (Player player : playersInTeamA)
					player.setMovedUp();
			// System.out.println("UpKeyPressed");

			break;
		}
		case KeyEvent.VK_DOWN:
		// down arrow
		{
			if (playersInTeamA != null)
				for (Player player : playersInTeamA)
					player.setMovedDown();
			// System.out.println("DownKeyPressed");
			break;
		}

		case KeyEvent.VK_W:
		// up arrow
		{
			if (playersInTeamB != null)
				for (Player player : playersInTeamB)
					player.setMovedUp();
			// System.out.println("UpKeyPressed");

			break;
		}
		case KeyEvent.VK_S:
		// down arrow
		{
			if (playersInTeamB != null)
				for (Player player : playersInTeamB)
					player.setMovedDown();
			// System.out.println("DownKeyPressed");
			break;
		}

		}
	}

	public void keyReleased(KeyEvent k) {

	}

	public void keyTyped(KeyEvent k) {

	}

	public void setPlayersInTeam(ArrayList<Player> playersInTeam) {
		if (this.playersInTeamA == null)
			this.playersInTeamA = playersInTeam;
		else
			this.playersInTeamB = playersInTeam;
	}

}
