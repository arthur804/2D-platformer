package interfacesAndAbstract;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class MyMovingObject extends GameObject implements GameMoveInt {

	public MyMovingObject(Rectangle bounds, char t) {
		super(bounds, t);
	}

	public boolean touchingLeft, touchingRight, touchingUp, touchingDown;
	public int[] vector = new int[] { 0, 0 };
	public int maxSpeed;
	public char[] touching = new char[2];
	public int[] absoluteLocation = new int[] { 0, 0 };
	public final int INCREASE = 100;
	// public final int AMOUNTZERO = 2;

	/**
	 * use this one if it is a moving object
	 */
	public MyMovingObject(char t, Rectangle bounds) {
		super(bounds, t);
		absoluteLocation[0] = bounds.x * INCREASE;
		absoluteLocation[1] = bounds.y * INCREASE;
	}

	// public Rectangle nextTangleOnlyX(int i) {
	// return new Rectangle(myRectangle.x + i, myRectangle.y, myRectangle.width,
	// myRectangle.height);
	// }

	public Rectangle nextTangleOnlyY(int i) {
		return new Rectangle(myRectangle.x, myRectangle.y + i, myRectangle.width, myRectangle.height);
	}

	public Rectangle nextTangle(int nextX, int nextY) {
		return new Rectangle(myRectangle.x + nextX, myRectangle.y + nextY, myRectangle.width, myRectangle.height);
	}

	public void touchingX(int x, char c, boolean goingLeft) {
		touching[0] = c;
		vector[0] = 0;
		// TODO
//		System.out.println((-absoluteLocation[0])%INCREASE);
		if (goingLeft)
			absoluteLocation[0] = absoluteLocation[0]+(-absoluteLocation[0])%INCREASE+ x * INCREASE;
		else
			absoluteLocation[0] = absoluteLocation[0]-(absoluteLocation[0]%INCREASE)+ x * INCREASE;
	}

	public void touchingY(int y, char c) {
		touching[1] = c;
		vector[1] = 0;
		// TODO
		absoluteLocation[1] = absoluteLocation[1] + y * INCREASE;
	}

	public void reTrue() {
		touchingLeft = touchingRight = touchingUp = touchingDown = false;
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

}
