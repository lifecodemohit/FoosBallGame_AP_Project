package gameGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import keyboardInputs.MyKeyListener;
import score.ScoreKeeper;
import team.abstractClasses.Player;
import ball.Ball;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	Image image;
	Image goalScoreImage;
	Rectangle rectangle;
	private Color color = new Color(0, 0, 0);
	ScorePanel scorePanel;
	private int GoalScored;
	Ball aBall;

	Dimension dm;

	public ArrayList<Player> playersInTeamA;
	public ArrayList<Player> playersInTeamB;

	public ScoreKeeper scoreKeeper;
	String teamAName, teamBName;

	MyKeyListener listener;

	public MyPanel(String teamAName, String teamBName) {

		this.teamAName = teamAName;
		this.teamBName = teamBName;

		this.image = new ImageIcon("field.jpg").getImage();
		this.goalScoreImage = new ImageIcon("goal.png").getImage();
		this.dm = new Dimension(image.getWidth(null), image.getHeight(null));

		setSize(dm);
		this.aBall = Ball.getInstance(this);
		this.aBall.start();
		this.GoalScored = 0;
		this.playersInTeamA = null;
		this.playersInTeamB = null;

		this.scoreKeeper = new ScoreKeeper();

		this.listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // Draw
																			// Field

		for (int i = 0; i < playersInTeamA.size(); i++) {
			setRectangle(playersInTeamA.get(i).getxAxisPosition() + 2, 0);
			Graphics2D g2 = (Graphics2D) g;
			draw(g2);
		}

		for (int i = 0; i < playersInTeamB.size(); i++) {
			setRectangle(playersInTeamB.get(i).getxAxisPosition() + 2, 0);
			Graphics2D g2 = (Graphics2D) g;
			draw(g2);
		}
		Graphics2D g2draw = (Graphics2D) g;

		this.aBall.draw(g2draw);

		if (playersInTeamA != null)
			for (Player player : playersInTeamA) {
				player.draw(g2draw, Color.ORANGE);
			}
		if (playersInTeamB != null)
			for (Player player : playersInTeamB) {
				player.draw(g2draw, Color.CYAN);
			}

		if (GoalScored == 1) // Draw "GOAL!"
		{
			g.drawImage(goalScoreImage, this.getWidth() / 4,
					this.getHeight() / 4, this.getWidth() / 2,
					this.getHeight() / 2, this);

		}

	}

	public void repaintPanel() {
		this.repaint();
	}

	public void goalScoreScreen() {
		this.GoalScored = 1;
	}

	public void resetGoalScoreScreen() {
		this.GoalScored = 0;
	}

	public void setPlayersInTeam(ArrayList<Player> playersInTeam) {
		if (this.playersInTeamA == null) {
			this.playersInTeamA = playersInTeam;
			this.listener.setPlayersInTeam(playersInTeam);
		} else {
			this.playersInTeamB = playersInTeam;
			this.listener.setPlayersInTeam(playersInTeam);
		}
	}

	public void resizePanel(Dimension newDimension) {
		this.dm = newDimension;
		this.setSize(newDimension);

		this.aBall.setResizedFlag();

		if (this.playersInTeamA != null) {
			for (Player player : playersInTeamA)
				player.setRescaledFlag();
		}
		if (this.playersInTeamB != null) {
			for (Player player : playersInTeamB)
				player.setRescaledFlag();
		}

	}

	public static int frameWidthError = 20;
	public static int frameHeightError = 70;

	@Override
	public int getHeight() {
		return dm.height - frameHeightError;
	}

	@Override
	public int getWidth() {
		return dm.width - frameWidthError;
	}

	public void draw(Graphics2D g2d) {
		if (this.rectangle != null) {
			g2d.setColor(this.color);
			g2d.fill(this.rectangle);
		}
	}

	public void setRectangle(int xpos, int ypos) {
		this.rectangle = new Rectangle(xpos, ypos, 4, this.getHeight());
	}

	public Ball getaBall() {
		return aBall;
	}

	public void gameFinished() {

		for (Player player : playersInTeamA) {
			player.isMoving = false;
		}
		for (Player player : playersInTeamB) {
			player.isMoving = false;
		}

		this.setVisible(false);

		MyFrame topFrame = (MyFrame) SwingUtilities.getWindowAncestor(this);

		topFrame.showFinishScreen();

	}

	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		aBall.setScorePanel(scorePanel);
	}

}
