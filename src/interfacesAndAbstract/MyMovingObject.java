package interfacesAndAbstract;

import java.awt.Rectangle;

import forReal.SMovingWall;
import staticClasses.Formulas;

public abstract class MyMovingObject extends GameObject {

	public boolean touchingLeft, touchingRight, touchingUp, touchingDown;
	public int[] vector = new int[] { 0, 0 };
	public ThingsInTheWorld[] touching = new ThingsInTheWorld[2];
	public int[] absoluteLocation = new int[] { 0, 0 };
	public final int INCREASE = 100;
	public boolean goingUp = false;
	public boolean goingLeft = false;
	public boolean goingRight = false;
	public boolean goingDown = false;
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
	
	public Rectangle nextTangleOnlyX(int i) {
		return new Rectangle(myRectangle.x + i, myRectangle.y, myRectangle.width, myRectangle.height);
	}
	
	public Rectangle nextTangle(int nextX, int nextY) {
		return new Rectangle(myRectangle.x + nextX, myRectangle.y + nextY, myRectangle.width, myRectangle.height);
	}

	public void touchingX(int x, GameObject staticObject) {
		touching[0] = staticObject.type;
		//TODO i have this part of the method also in the override in SMovingWall try to get this in 1 place so it doesnt take up as much space
		if (staticObject instanceof SMovingWall){
			((SMovingWall)staticObject).touchingX(0, this);
		} else
			vector[0] = x * INCREASE;
	}

	public void touchingY(int y, GameObject staticObject) {
		touching[1] = staticObject.type;
		vector[1] = y * INCREASE;
		// TODO
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
	
	public int nextX() {
		int nextX = calc(0);
		if (goingRight){
			nextX += 1;
		} else if (goingLeft){
			nextX -= 1;
		}
		return nextX; // omdat 1300 - 30 = 12 TODO Fix
	}
	
	public int nextY() {
		int nextY = calc(1);
		
		return nextY;
	}
	private int calc(int i){
		if (absoluteLocation[i] < 0)
			return (-absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
		else
			return (absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
	}
	//TODO
	protected abstract void calcNextX();
	//TODO
	protected abstract void calcNextY();


	public void pushedX(int calc2, int calc1, int newVector, boolean movingwallIsGoingLeft, boolean playerIsOnLeft) {
		//(this.absoluteLocation[0] + vector[0] + extra);
		int newLocation = 0;
		if (playerIsOnLeft){
			newLocation = calc2 - myRectangle.width * INCREASE;			
			if (movingwallIsGoingLeft){
				if (vector[0] > newVector)//Doesnt work
					vector[0] = newVector;
			}
			else{
				vector[0] = 0;			
			}
		} else {
			newLocation = calc2 + calc1;
			if (!movingwallIsGoingLeft){
				if (vector[0] < newVector)
					vector[0] = newVector;
			} else{
				vector[0] = 0;
			}
		}
		absoluteLocation[0] = newLocation;
	}


	public void pushedY(int calc2, int calc1, int newVector, boolean movingWallIsGoingUp, boolean playerIsAboveWall, boolean isSticky) {
		// TODO Auto-generated method stub
		if (playerIsAboveWall){
			if (movingWallIsGoingUp){
				
			} else {
				
			}
		} else {
			if (movingWallIsGoingUp){
							
			} else {
				
			}
		}
		
	}


	
	
}
