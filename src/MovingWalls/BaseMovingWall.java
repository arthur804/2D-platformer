package MovingWalls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;

public abstract class BaseMovingWall extends MyMovingObject {

	protected int xSpeed;
	protected int ySpeed;
	protected int flyingSpeed;
	public boolean sticky;

	public BaseMovingWall(Rectangle bounds, ThingsInTheWorld e, int xSpeed, int ySpeed, int flyingSpeed, boolean sticky) {
		super(bounds, e);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.flyingSpeed = flyingSpeed;
		this.sticky = sticky;
	}

	@Override
	public void touchingX(int x, GameObject staticObject) {
		((MyMovingObject) staticObject).touching[0] = this.type;
		((MyMovingObject) staticObject).pushedX(vector[0], goingLeft, staticObject.myRectangle.x < myRectangle.x);
		
	}

	@Override
	public void touchingY(int y, GameObject staticObject) {
		((MyMovingObject) staticObject).touching[1] = this.type;
		((MyMovingObject) staticObject).pushedY(vector[1], goingUp, 
				staticObject.myRectangle.y < myRectangle.y, sticky, vector[0]);			
		
	}
	
	//TODO
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		super.baseDraw(g);
	}

}
