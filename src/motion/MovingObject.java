package motion;

import motion.interfaces.Motion;

public abstract class MovingObject extends Thread implements Motion {

	Position position = new Position();
	Speed speed = new Speed();

	protected boolean isMoving;

	public void setPosition(int x, int y) {
		position.setPosition(x, y);
	}

	public int getxAxisPosition() {
		return position.getX();
	}

	public void setxAxisPosition(int xAxisPosition) {
		position.setX(xAxisPosition);
	}

	public int getyAxisPosition() {
		return position.getY();
	}

	public void setyAxisPosition(int yAxisPosition) {
		position.setY(yAxisPosition);
	}

	public void setSpeed(int x, int y) {
		speed.setSpeed(x, y);
	}

	public int getxAxisSpeed() {
		return speed.getX();
	}

	public void setxAxisSpeed(int xAxisSpeed) {
		speed.setX(xAxisSpeed);
	}

	public int getyAxisSpeed() {
		return speed.getY();
	}

	public void setyAxisSpeed(int yAxisSpeed) {
		speed.setY(yAxisSpeed);
	}

	public int getMod(int x) {
		if (x < 0)
			x = ((-1) * x);
		return x;
	}

	public int getSign(int x) {
		if (x < 0)
			return -1;
		return 1;
	}

}
