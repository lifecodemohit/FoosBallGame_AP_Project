package team.abstractClasses;

import gameGUI.MyPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import team.Team;
import team.enums.Category;

public abstract class Player extends Person {

	Team team;
	int minKickSpeed;
	int maxKickSpeed;
	Category category;
	MyPanel myPanel;

	Rectangle2D.Double player;
	private int xSize;
	private int ySize;
	public boolean isMoving;
	private int movementLimit, baseX, baseY, movedUp, movedDown;

	private static int heightScale = 20;
	private static int widthScale = 100;

	private float xPosScale;
	private float yPosScale;
	private boolean rescaled;

	public int xErrorMargin;
	public int yErrorMargin;

	public Player(String name, int age, String country, MyPanel myPanel,
			int movementLimit, int baseX, int baseY) {
		super(name, age, country);

		this.myPanel = myPanel;
		this.player = new Rectangle2D.Double(baseX, baseY, xSize, ySize);
		this.isMoving = true;

		this.movementLimit = movementLimit;

		this.movedUp = 0;
		this.movedDown = 0;

		this.baseX = baseX;
		this.baseY = baseY;

		this.xPosScale = ((float) this.myPanel.getWidth()) / ((float) baseX);
		this.yPosScale = ((float) this.myPanel.getHeight()) / ((float) baseY);
		this.rescaled = false;

		this.xSize = myPanel.getWidth() / widthScale;
		this.ySize = myPanel.getHeight() / heightScale;

		this.setxAxisPosition(this.baseX);
		this.setyAxisPosition(this.baseY);

		this.setxAxisSpeed(0);
		this.setyAxisSpeed(20);
	}

	public Player(String name, String country, int x, int y) {
		super(name, 0, country);
		this.setxAxisPosition(x);
		this.setyAxisPosition(y);
	}

	@Override
	public void run() {
		while (isMoving) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (this.rescaled) {
				// Resize Player according to frame
				resizePlayer();

				// Rescale Position
				rescalePlayerPosition();

				this.rescaled = false; // Reset flag to false

			}

			if (movedUp == 1) {
				if ((baseY - this.getyAxisPosition()) < movementLimit - 20) {
					setyAxisPosition(getyAxisPosition() - getyAxisSpeed());

				}
			}
			this.resetMovedUp();

			if (movedDown == 1) {
				if (((this.getyAxisPosition() - baseY)) < movementLimit - 50) {
					setyAxisPosition(getyAxisPosition() + getyAxisSpeed());
				}
			}
			this.resetMovedDown();

			// Repaint Ball
			this.player.setFrame(this.getxAxisPosition(),
					this.getyAxisPosition(), xSize, ySize);
			myPanel.repaintPanel();

		}
	}

	public void rescalePlayerPosition() {
		baseX = (int) (this.myPanel.getWidth() / xPosScale);
		baseY = (int) (this.myPanel.getHeight() / yPosScale);

		this.setxAxisPosition(baseX);
		this.setyAxisPosition(baseY);

	}

	public void resizePlayer() {
		this.xSize = myPanel.getWidth() / widthScale;
		this.ySize = myPanel.getHeight() / heightScale;
	}

	public void resetPlayer() {
		this.setxAxisPosition(baseX);
		this.setyAxisPosition(baseY);
		this.player.setFrame(this.baseX, this.baseY, xSize, ySize);

		this.resetMovedDown();
		this.resetMovedUp();
	}

	public void setMovedUp() {
		this.movedUp = 1;
	}

	private void resetMovedUp() {
		this.movedUp = 0;
	}

	public boolean isMovedUp() {
		if (this.movedUp == 1)
			return true;
		return false;
	}

	public void setMovedDown() {
		this.movedDown = 1;
	}

	private void resetMovedDown() {
		this.movedDown = 0;
	}

	public boolean isMovedDown() {
		if (this.movedDown == 1)
			return true;
		return false;
	}

	// draw the player
	public void draw(Graphics2D g2d, Color color) {
		if (this.player != null) {
			g2d.setColor(color);
			g2d.fill(this.player);
		}
	}

	public void setRescaledFlag() {
		this.rescaled = true;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getMinKickSpeed() {
		return minKickSpeed;
	}

	public void setMinKickSpeed(int minKickSpeed) {
		this.minKickSpeed = minKickSpeed;
	}

	public int getMaxKickSpeed() {
		return maxKickSpeed;
	}

	public void setMaxKickSpeed(int maxKickSpeed) {
		this.maxKickSpeed = maxKickSpeed;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
