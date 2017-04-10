package interfacesAndAbstract;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class MyMovingObject extends GameObject {

	public boolean touchingLeft, touchingRight, touchingUp, touchingDown;
	public int[] vector = new int[] { 0, 0 };
	public ThingsInTheWorld[] touching = new ThingsInTheWorld[2];
	public int[] absoluteLocation = new int[] { 0, 0 };
	public final int INCREASE = 100;
	public boolean goingUp = false;
	public boolean goingLeft = false;
	public boolean goingRight = false;
	// public final int AMOUNTZERO = 2;

	/**
	 * use this one if it is a moving object
	 */
	public MyMovingObject(Rectangle bounds, ThingsInTheWorld e) {
		super(bounds, e);
		absoluteLocation[0] = bounds.x * INCREASE;
		absoluteLocation[1] = bounds.y * INCREASE;
	}


	public Rectangle nextTangleOnlyY(int i) {
		return new Rectangle(myRectangle.x, myRectangle.y + i, myRectangle.width, myRectangle.height);
	}

	public Rectangle nextTangle(int nextX, int nextY) {
		return new Rectangle(myRectangle.x + nextX, myRectangle.y + nextY, myRectangle.width, myRectangle.height);
	}

	public void touchingX(int x, ThingsInTheWorld c) {
		touching[0] = c;
		vector[0] = 0;

		absoluteLocation[0] = absoluteLocation[0] - (absoluteLocation[0] % INCREASE) + x * INCREASE;
	}

	public void touchingY(int y, ThingsInTheWorld c) {
		touching[1] = c;
		vector[1] = 0;
		// TODO
		absoluteLocation[1] = absoluteLocation[1] + y * INCREASE;
	}

	public void reTrue() {
		goingLeft = goingRight = goingUp = touchingLeft = touchingRight = touchingUp = touchingDown = false;
		touching[0] = touching[1] = null;
	}

	public void update() {
		absoluteLocation[0] += vector[0];
		absoluteLocation[1] += vector[1];
		myRectangle.setLocation(absoluteLocation[0] / INCREASE, absoluteLocation[1] / INCREASE);
	}

	public void preUpdate() {
		calcNextX();
		calcNextY();
	}

	public abstract int nextY();
	
	public abstract int nextX();
		
	public abstract void jump();
	
	//TODO
	protected abstract void calcNextX();
	//TODO
	protected abstract void calcNextY();
	
}
