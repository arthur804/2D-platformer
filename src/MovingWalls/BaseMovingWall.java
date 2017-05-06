package movingWalls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.PushAble;
import interfacesAndAbstract.ThingsInTheWorld;

public abstract class BaseMovingWall extends MyMovingObject {

	protected int xSpeed;
	protected int ySpeed;
	protected final int FLYING_SPEED;
	public boolean sticky;

	public BaseMovingWall(Rectangle bounds, ThingsInTheWorld e, int xSpeed, int ySpeed, int flyingSpeed, boolean sticky) {
		super(bounds, e);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.FLYING_SPEED = flyingSpeed;
		this.sticky = sticky;
	}

	@Override
	public void touchingX(int x, GameObject staticObject) {
		((PushAble) staticObject).touching[0] = this.type;
		((PushAble) staticObject).pushedX(vector[0], goingLeft, staticObject.myRectangle.x < myRectangle.x);
		
	}

	@Override
	public void touchingY(int y, GameObject staticObject) {
		((PushAble) staticObject).touching[1] = this.type;
		int location;
		boolean playerLocUp = staticObject.myRectangle.y < myRectangle.y;
		if (playerLocUp){
			location = absoluteLocation[1] - staticObject.myRectangle.height*INCREASE;
		} else {
			location = absoluteLocation[1] + myRectangle.height*INCREASE;
		}
		((PushAble) staticObject).pushedY(vector[1], goingUp, 
				playerLocUp , sticky, vector[0], location);			
		
	}
	
	//TODO
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		super.baseDraw(g);
	}

}
