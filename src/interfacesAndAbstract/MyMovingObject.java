package interfacesAndAbstract;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import forReal.SMovingWall;

public abstract class MyMovingObject extends GameObject {

	public boolean touchingLeft, touchingRight, touchingUp, touchingDown;
	public int[] vector = new int[] { 0, 0 };
	public ThingsInTheWorld[] touching = new ThingsInTheWorld[2];
	public int[] absoluteLocation = new int[] { 0, 0 };
	protected static final int INCREASE = 100;
	public boolean goingUp = false;
	public boolean goingLeft = false;
	public boolean goingRight = false;
	public boolean goingDown = false;
	public boolean dead = false;
	
	// public final int AMOUNTZERO = 2;

	/**
	 * use this one if it is a moving object
	 */	
	public MyMovingObject(Rectangle bounds, ThingsInTheWorld e) {
		super(bounds, e);
		absoluteLocation[0] = bounds.x * INCREASE;
		absoluteLocation[1] = bounds.y * INCREASE;
		canBeStepedOn = false;
	}	

	 public MyMovingObject(Rectangle bounds, ThingsInTheWorld e, BufferedImage spriteSheet, int sizeSpriteX, int sizeSpriteY, int xPixelsBet,int yPixelsBet){
		super(bounds, e, spriteSheet, sizeSpriteX, sizeSpriteY, xPixelsBet, yPixelsBet);
		absoluteLocation[0] = bounds.x * INCREASE;
		absoluteLocation[1] = bounds.y * INCREASE;
		canBeStepedOn = false;
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
//		if (staticObject instanceof SMovingWall){
//			((SMovingWall)staticObject).touchingX(0, this);
//		} else{
			vector[0] = x * INCREASE;
			touching[0] = staticObject.type;
//		}
	}

	public void touchingY(int y, GameObject staticObject) {
		/*if (staticObject instanceof SMovingWall){
			((SMovingWall)staticObject).touchingY(0, this);
		} else {*/
			vector[1] = y * INCREASE;
			touching[1] = staticObject.type;
//		}
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
		if (goingLeft){
			nextX -= 1;
		}
		return nextX; // (1300 - 30 )/INCREASE = 12
	}
	
	public int nextY() {
		int nextY = calc(1);
//		if (goingDown){
//			nextY += 1;
//		} 
//		else if (goingUp){
//			nextY -= 1;
//		}
		return nextY;
	}
	private int calc(int i){
		if (absoluteLocation[i] < 0)
			return (-absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
		else
			return (absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
	}

	protected abstract void calcNextX();

	protected abstract void calcNextY();


	public void pushedX(int newVector, boolean movingwallIsGoingLeft, boolean playerIsOnLeft) {
		if (playerIsOnLeft){
			touchingRight = true;
			goingRight = true;
				
			if (movingwallIsGoingLeft){
				if (vector[0] > newVector)
					vector[0] = newVector;
			}
			else{
				vector[0] = 0;				
			}
			
		} else {
			touchingLeft = true;
			if (!movingwallIsGoingLeft){
				if (vector[0] < newVector)
					vector[0] = newVector;
			} else{
				vector[0] = 0;
			}
			
		}
	}
	

	public void pushedY(int newVector, boolean movingWallIsGoingUp,
			boolean playerIsAboveWall, boolean isSticky) {
		// TODO
		//hij gaat omhoog ik ga omlaag
		if (playerIsAboveWall){
			touchingDown = true;
			if (movingWallIsGoingUp){
				if (vector[1] > newVector)
					vector[1] = newVector;
				touchingDown = true;
				
			}else {
				vector[1] = newVector;
			}
		} else {
			goingDown = true;
			goingUp = false;
			if (movingWallIsGoingUp){
				vector[1] = 0; // if you want to hang on to something that moves you need to adapt this
			} else {
				touchingUp = true;
				vector[1] = newVector + INCREASE;
				
			}
		}
		
	}


	
	
}
