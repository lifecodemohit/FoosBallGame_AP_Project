package ball;

import gameGUI.MyPanel;
import gameGUI.ScorePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ball.interfaces.BallInterface;
import ball.interfaces.Reflectable;
import ball.interfaces.Resetable;
import ball.interfaces.Resizable;
import motion.MovingObject;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import team.Team;
import team.abstractClasses.Player;

public class Ball extends MovingObject implements BallInterface, Resizable,
		Resetable, Reflectable {

	Player lastContact, currentContact, futureContact;

	private Ellipse2D.Double ball;
	private MyPanel myPanel;

	private static Ball aBall; // For Singleton
	private int size;
	private static int speedLowerLimit = 10;
	private static int sizeScale = 100;
	private boolean resized;
	private static int scoreLimit = 5;
	private int tossWinner;
	ScorePanel scorePanel;

	// Singleton
	private Ball(MyPanel myPanel) {

		this.myPanel = myPanel;
		this.size = (myPanel.getHeight() + myPanel.getWidth()) / sizeScale;
		this.ball = new Ellipse2D.Double(899, 699, size, size);
		this.resized = false;

		// Set Initial Speed
		this.setxAxisSpeed(-10 + (int) (Math.random() * 20));
		this.setyAxisSpeed(-10 + (int) (Math.random() * 20));

		// Set Initial Position
		this.setxAxisPosition(myPanel.getWidth() / 2);
		this.setyAxisPosition(myPanel.getHeight() / 2);

		this.isMoving = true;
	}

	public Ball(int x, int y) {
		this.setxAxisPosition(x);
		this.setyAxisSpeed(y);
	}

	public static Ball getInstance(MyPanel myPanel) {
		if (aBall == null)
			aBall = new Ball(myPanel);
		return aBall;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return aBall;
	}

	public void setTossWinner(int tossWinner) {
		this.tossWinner = tossWinner;
	}

	// draw the ball
	public void draw(Graphics2D g2d) {
		if (this.ball != null) {
			g2d.setColor(new Color(12, 12, 12));
			g2d.fill(this.ball);
		}
	}

	@Override
	public void run() {

		while (myPanel.playersInTeamA == null || myPanel.playersInTeamB == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Winner Of Toss is Player " + tossWinner);
		this.setBallAfterToss(tossWinner);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		while (isMoving) {
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Set Ball Size
			if (this.resized)
				this.resizeBall();

			// If Goal : Reset to centre of field
			if ((getMod(this.getxAxisPosition() - 0) < 10)
					|| (getMod(myPanel.getWidth() - this.getxAxisPosition()) < 10))
				if (getMod(this.getyAxisPosition() - (myPanel.getHeight() / 2)) < myPanel
						.getWidth() / 10) {
					if (getMod(this.getxAxisPosition() - 0) < 10) {
						String goalSoundFile = "GoalSmall.wav";
						InputStream in = null;
						try {
							in = new FileInputStream(goalSoundFile);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// create an audiostream from the inputstream
						AudioStream audioStream = null;
						try {
							audioStream = new AudioStream(in);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// play the audio clip with the audioplayer class
						AudioPlayer.player.start(audioStream);

						myPanel.scoreKeeper.increaseTeamBScore();
						resetBall(1);
					} else {
						String goalSoundFile = "GoalSmall.wav";
						InputStream in = null;
						try {
							in = new FileInputStream(goalSoundFile);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// create an audiostream from the inputstream
						AudioStream audioStream = null;
						try {
							audioStream = new AudioStream(in);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// play the audio clip with the audioplayer class
						AudioPlayer.player.start(audioStream);

						myPanel.scoreKeeper.increaseTeamAScore();
						resetBall(2);
					}

					scorePanel.setScoreTeam1(myPanel.scoreKeeper
							.getTeamAScore());
					scorePanel.setScoreTeam2(myPanel.scoreKeeper
							.getTeamBScore());

				}

			// Calculate new position
			int newx = this.getxAxisPosition() + this.getxAxisSpeed();
			int newy = this.getyAxisPosition() + this.getyAxisSpeed();

			// Set new position
			this.setxAxisPosition(newx);
			this.setyAxisPosition(newy);

			// Reflect on boundary walls
			reflectOnWalls(newx, newy);

			// REflect on Players
			reflectOnPlayers(newx, newy);

			// Repaint Ball
			this.getBall().setFrame(newx, newy, size, size);
			myPanel.repaintPanel();

			// Check Finish Game
			checkGameFinish();
		}

	}

	public void setBallAfterToss(int tossWinner) {
		// Set Initial Position
		if (tossWinner == 1) {
			this.setxAxisPosition(myPanel.playersInTeamA.get(0)
					.getxAxisPosition() + 10);
			this.setyAxisPosition(myPanel.playersInTeamA.get(0)
					.getyAxisPosition());
			this.setxAxisSpeed(-10);
		} else {
			this.setxAxisPosition(myPanel.playersInTeamB.get(0)
					.getxAxisPosition() - 10);
			this.setyAxisPosition(myPanel.playersInTeamB.get(0)
					.getyAxisPosition());
			this.setxAxisSpeed(10);
		}
		// Set Frame
		this.getBall().setFrame(this.getxAxisPosition(),
				this.getyAxisPosition(), size, size);
		myPanel.repaintPanel();

	}

	public void checkGameFinish() {
		if (myPanel.scoreKeeper.getTeamAScore() >= scoreLimit
				|| myPanel.scoreKeeper.getTeamBScore() >= scoreLimit) {
			this.isMoving = false;
			String goalSoundFile = "GameOver.wav";
			InputStream in = null;
			try {
				in = new FileInputStream(goalSoundFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// create an audiostream from the inputstream
			AudioStream audioStream = null;
			try {
				audioStream = new AudioStream(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// play the audio clip with the audioplayer class
			AudioPlayer.player.start(audioStream);
			myPanel.gameFinished();
		}
	}

	public void pass(Player p1, Player p2) {
		setCurrentContact(p1);
		setFutureContact(p2);
	}

	public void resetAfterGoal(Team X) {
		this.setxAxisPosition(0);
		this.setyAxisSpeed(0);
	}

	public void resizeBall() {
		this.size = (myPanel.getHeight() + myPanel.getWidth()) / sizeScale;

		/*
		 * this.setxAxisPosition((myPanel.getWidth() / 2) - (myPanel.getWidth()
		 * / 200) ) ; this.setyAxisPosition((myPanel.getHeight() / 2) -
		 * (myPanel.getHeight() / 200)) ;
		 * 
		 * this.getBall().setFrame(this.getxAxisPosition(),
		 * this.getyAxisPosition(), size, size);
		 */
		this.resized = false;

	}

	public void setResizedFlag() {
		this.resized = true;
	}

	// Reset Ball to centre of field on Goal Scored
	public void resetBall(int tossWinner) {
		// Reset Players
		if (myPanel.playersInTeamA != null)
			for (Player player : myPanel.playersInTeamA)
				player.resetPlayer();
		if (myPanel.playersInTeamB != null)
			for (Player player : myPanel.playersInTeamB)
				player.resetPlayer();

		// Set Initial Speed
		this.setyAxisSpeed(0);

		// Set Initial Position
		if (tossWinner == 1) {
			this.setxAxisPosition(myPanel.playersInTeamA.get(0)
					.getxAxisPosition() + 10);
			this.setyAxisPosition(myPanel.playersInTeamA.get(0)
					.getyAxisPosition());
			this.setxAxisSpeed(-10);
		} else {
			this.setxAxisPosition(myPanel.playersInTeamB.get(0)
					.getxAxisPosition() - 10);
			this.setyAxisPosition(myPanel.playersInTeamB.get(0)
					.getyAxisPosition());
			this.setxAxisSpeed(10);
		}
		// Set Frame
		this.getBall().setFrame(this.getxAxisPosition(),
				this.getyAxisPosition(), size, size);
		myPanel.repaint();

		try {
			Thread.sleep(200);
			myPanel.goalScoreScreen();
			myPanel.repaint();
			Thread.sleep(2000);

			myPanel.resetGoalScoreScreen();
			myPanel.repaint();
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Getters and Setters
	public Ellipse2D getBall() {
		return ball;
	}

	public Player getLastContact() {
		return lastContact;
	}

	public void setLastContact(Player lastContact) {
		this.lastContact = lastContact;
	}

	public Player getCurrentContact() {
		return currentContact;
	}

	public void setCurrentContact(Player currentContact) {
		this.currentContact = currentContact;
	}

	public Player getFutureContact() {
		return futureContact;
	}

	public void setFutureContact(Player futureContact) {
		this.futureContact = futureContact;
	}

	public void reflectOnWalls(int newx, int newy) {
		if (newx > myPanel.getWidth() || newx < 0) // Collision on vertical
													// walls
		{
			accelerateXIfNecessary();
			this.setxAxisSpeed(this.getxAxisSpeed() * (-1)); // Reflect
			randomAccelerate(); // To Avoid Deadlocks
		}
		if (newy > myPanel.getHeight() || newy < 0) // Collision on horizontal
													// walls
		{
			accelerateXIfNecessary();
			accelerateYIfNecessary();
			this.setyAxisSpeed(this.getyAxisSpeed() * (-1)); // Reflect
			randomAccelerate(); // To Avoid Deadlocks
		}
	}

	private void accelerateXIfNecessary() {
		if (getMod(this.getxAxisSpeed()) < speedLowerLimit)
			this.setxAxisSpeed(this.getxAxisSpeed() * 1
					+ getSign(this.getxAxisSpeed()) * 1);

	}

	private void accelerateYIfNecessary() {
		if (getMod(this.getyAxisSpeed()) < speedLowerLimit)
			this.setyAxisSpeed(this.getyAxisSpeed() * 1
					+ getSign(this.getyAxisSpeed()) * 1);

	}

	private void randomAccelerate() {

		int choice = ((int) Math.random()) * 2;
		int sign;
		if (choice == 0)
			sign = 1;
		else
			sign = -1;

		if (getMod(this.getxAxisSpeed()) < 3) {
			this.setxAxisSpeed(this.getxAxisSpeed()
					+ (sign * ((int) (((Math.random() * 5))))));
			// System.out.println("Random Accelerate");
		}
		if (getMod(this.getyAxisSpeed()) < 5) {
			this.setyAxisSpeed(this.getyAxisSpeed()
					+ (sign * ((int) (((Math.random() * 5))))));
			// System.out.println("Random Accelerate");
			// System.out.println("Y speed : " + this.getyAxisSpeed());
		}

	}

	public void reflectOnPlayers(int newx, int newy) {
		ArrayList<Integer> passingPlayerXCoordinate = new ArrayList<Integer>();
		ArrayList<Integer> passingPlayerYCoordinate = new ArrayList<Integer>();

		if (myPanel.playersInTeamA != null)
			for (Player player : myPanel.playersInTeamA) {
				if ((Math.abs(player.getxAxisPosition()
						- this.getxAxisPosition()) < player.xErrorMargin)
						&& (Math.abs(this.getyAxisPosition()
								- player.getyAxisPosition()) < player.yErrorMargin)) {
					if (player.getCategory().toString().compareTo("GoalKeeper") == 0) {
						for (Player passingPlayer : myPanel.playersInTeamA) {
							if (passingPlayer.getCategory().toString()
									.compareTo("Defender") == 0) {
								passingPlayerXCoordinate.add(passingPlayer
										.getxAxisPosition());
								passingPlayerYCoordinate.add(passingPlayer
										.getyAxisPosition());
							}
						}
					} else if (player.getCategory().toString()
							.compareTo("Defender") == 0) {
						boolean shootOrPass = (int) (Math.random() * 10) % 4 == 1 ? false
								: true; // pass on true
						if (shootOrPass) {
							for (Player passingPlayer : myPanel.playersInTeamA) {
								if (passingPlayer.getCategory().toString()
										.compareTo("MidFielder") == 0) {
									passingPlayerXCoordinate.add(passingPlayer
											.getxAxisPosition());
									passingPlayerYCoordinate.add(passingPlayer
											.getyAxisPosition());
								}
							}
						} else {
							passingPlayerXCoordinate.add(myPanel.getWidth());
							passingPlayerYCoordinate.add(myPanel.getHeight()
									- 245 - (int) (Math.random() * 150));
						}
					} else if (player.getCategory().toString()
							.compareTo("MidFielder") == 0) {
						boolean shootOrPass = (int) (Math.random() * 10) % 4 == 1 ? false
								: true; // pass on true
						if (shootOrPass) {
							for (Player passingPlayer : myPanel.playersInTeamA) {
								if (passingPlayer.getCategory().toString()
										.compareTo("Attacker") == 0) {
									passingPlayerXCoordinate.add(passingPlayer
											.getxAxisPosition());
									passingPlayerYCoordinate.add(passingPlayer
											.getyAxisPosition());
								}
							}
						} else {
							passingPlayerXCoordinate.add(myPanel.getWidth());
							passingPlayerYCoordinate.add(myPanel.getHeight()
									- 245 - (int) (Math.random() * 150));
						}
					} else if (player.getCategory().toString()
							.compareTo("Attacker") == 0) {
						passingPlayerXCoordinate.add(myPanel.getWidth());
						passingPlayerYCoordinate.add(myPanel.getHeight() - 245
								- (int) (Math.random() * 150));
					}
					int KickSpeed = (int) (Math.random() * 10);
					int selectedPassingPlayer = (int) (Math.random() * 10)
							% passingPlayerXCoordinate.size();
					double slope = ((double) passingPlayerYCoordinate
							.get(selectedPassingPlayer) - (double) player
							.getyAxisPosition())
							/ ((double) passingPlayerXCoordinate
									.get(selectedPassingPlayer) - (double) player
									.getxAxisPosition());
					this.setxAxisPosition((int) (player.getxAxisPosition() + (15 + KickSpeed)
							/ Math.sqrt(1 + slope * slope))); // 20 is temporary
																// kick speed of
																// player
					this.setyAxisPosition((int) (player.getyAxisPosition() + slope
							* (this.getxAxisPosition() - player
									.getxAxisPosition())));
					this.setxAxisSpeed((int) ((15 + KickSpeed) * Math.cos(Math
							.atan(slope))));
					this.setyAxisSpeed((int) ((15 + KickSpeed) * Math.sin(Math
							.atan(slope))));

				}
			}
		int NumPassingPlayer = passingPlayerXCoordinate.size();
		for (int passingPlayer = 0; passingPlayer < NumPassingPlayer; passingPlayer++) {
			passingPlayerXCoordinate.remove(NumPassingPlayer - 1
					- passingPlayer);
			passingPlayerYCoordinate.remove(NumPassingPlayer - 1
					- passingPlayer);
		}

		if (myPanel.playersInTeamB != null)
			for (Player player : myPanel.playersInTeamB) {
				if ((Math.abs(player.getxAxisPosition()
						- this.getxAxisPosition()) < player.xErrorMargin)
						&& (Math.abs(this.getyAxisPosition()
								- player.getyAxisPosition()) < player.yErrorMargin)) {
					if (player.getCategory().toString().compareTo("GoalKeeper") == 0) {
						for (Player passingPlayer : myPanel.playersInTeamB) {
							if (passingPlayer.getCategory().toString()
									.compareTo("Defender") == 0) {
								passingPlayerXCoordinate.add(passingPlayer
										.getxAxisPosition());
								passingPlayerYCoordinate.add(passingPlayer
										.getyAxisPosition());
							}
						}
					} else if (player.getCategory().toString()
							.compareTo("Defender") == 0) {
						boolean shootOrPass = (int) (Math.random() * 10) % 4 == 1 ? false
								: true; // pass on true
						if (shootOrPass) {
							for (Player passingPlayer : myPanel.playersInTeamB) {
								if (passingPlayer.getCategory().toString()
										.compareTo("MidFielder") == 0) {
									passingPlayerXCoordinate.add(passingPlayer
											.getxAxisPosition());
									passingPlayerYCoordinate.add(passingPlayer
											.getyAxisPosition());
								}
							}
						} else {
							passingPlayerXCoordinate.add(0);
							passingPlayerYCoordinate.add(myPanel.getHeight()
									- 245 - (int) (Math.random() * 150));
						}
					} else if (player.getCategory().toString()
							.compareTo("MidFielder") == 0) {
						boolean shootOrPass = (int) (Math.random() * 10) % 4 == 1 ? false
								: true; // pass on true
						if (shootOrPass) {
							for (Player passingPlayer : myPanel.playersInTeamB) {
								if (passingPlayer.getCategory().toString()
										.compareTo("Attacker") == 0) {
									passingPlayerXCoordinate.add(passingPlayer
											.getxAxisPosition());
									passingPlayerYCoordinate.add(passingPlayer
											.getyAxisPosition());
								}
							}
						} else {
							passingPlayerXCoordinate.add(0);
							passingPlayerYCoordinate.add(myPanel.getHeight()
									- 245 - (int) (Math.random() * 150));
						}
					} else if (player.getCategory().toString()
							.compareTo("Attacker") == 0) {
						passingPlayerXCoordinate.add(0);
						passingPlayerYCoordinate.add(myPanel.getHeight() - 245
								- (int) (Math.random() * 150));
					}
					int KickSpeed = (int) (Math.random() * 10);
					int selectedPassingPlayer = (int) (Math.random() * 10)
							% passingPlayerXCoordinate.size();
					double slope = ((double) player.getyAxisPosition() - ((double) passingPlayerYCoordinate
							.get(selectedPassingPlayer)))
							/ ((double) player.getxAxisPosition() - (double) passingPlayerXCoordinate
									.get(selectedPassingPlayer));
					this.setxAxisPosition((int) (player.getxAxisPosition() - (15 + KickSpeed)
							/ Math.sqrt(1 + (slope * slope)))); // 20 is
																// temporary
																// kick speed of
																// player
					this.setyAxisPosition((int) (player.getyAxisPosition() - slope
							* (this.getxAxisPosition() - player
									.getxAxisPosition())));
					this.setxAxisSpeed(-1
							* (int) ((15 + KickSpeed) * Math.cos(Math
									.atan(slope))));
					this.setyAxisSpeed((int) ((15 + KickSpeed) * Math.sin(Math
							.atan(-1 * slope))));

				}
			}
		int NumOppositePassingPlayer = passingPlayerXCoordinate.size();
		for (int passingPlayer = 0; passingPlayer < NumOppositePassingPlayer; passingPlayer++) {
			passingPlayerXCoordinate.remove(NumOppositePassingPlayer - 1
					- passingPlayer);
			passingPlayerYCoordinate.remove(NumOppositePassingPlayer - 1
					- passingPlayer);
		}
	}

	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}

}
