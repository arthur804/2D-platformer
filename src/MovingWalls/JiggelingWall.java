package movingWalls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.PushAble;
import interfacesAndAbstract.ThingsInTheWorld;

//TODO V that
@SuppressWarnings("unused")
 //@Deprecated //TODO it doesnt work
public class JiggelingWall extends MyMovingObject {

	//TODO maybe also make this the pushing wall thingy?
	private int xMaxWiggle;
	private int yMaxWiggle;
	private int xMinWiggle;
	private int yMinWiggle;
	private int[] standard;
	private int touchingYVector = 100;
	private int touchingXVector;
	private int slowDownYVector = 10;
	private int slowDownXVector;
	private int maxYFlyingSpeed;
	private int maxXFlyingSpeed;
	private boolean playerOnly;
	
	private int flying_Slowdown_SpeedX;
	private int flying_Slowdown_SpeedY;
	
	private boolean stoped = false;

	public JiggelingWall(Rectangle bounds, ThingsInTheWorld e, int yMaxWiggle, int xMaxWiggle, int xMaxSpeed,
			int yMaxSpeed, int flyingSpeed, boolean sticky) {
		super(bounds, e);
		this.xMaxWiggle = (bounds.x + xMaxWiggle) * INCREASE;
		this.yMaxWiggle = (bounds.y + yMaxWiggle) * INCREASE;
		this.xMinWiggle = (bounds.x - xMaxWiggle) * INCREASE;
		this.yMinWiggle = (bounds.y - yMaxWiggle) * INCREASE;
		flying_Slowdown_SpeedY = flyingSpeed;
		this.standard = new int[]{bounds.x * INCREASE, bounds.y * INCREASE};
	}

	@Override
	protected void calcNextX() {
		// test if have enough speed

	}

	@Override
	protected void calcNextY() {
		if (touchingUp || touchingDown) {

			if (touchingUp) {
				goingDown = true;
				if (vector[1] == 0) {
					vector[1] = touchingYVector;
				} else {
					vector[1] += flying_Slowdown_SpeedY;
					if (vector[1] > maxYFlyingSpeed)
						vector[1] = maxYFlyingSpeed;
				}
				if (vector[1] + absoluteLocation[1] > yMaxWiggle){
					vector[1] = yMaxWiggle - absoluteLocation[1];
				}

			} 
			if (touchingDown){
				goingUp = true;
				if (vector[1] == 0) {
					vector[1] = -touchingYVector;
				} else {
					vector[1] -= flying_Slowdown_SpeedY;

					if (vector[1] < -maxYFlyingSpeed)
						vector[1] = -maxYFlyingSpeed;
				}
				if (vector[1] + absoluteLocation[1] < yMinWiggle){
					vector[1] = yMinWiggle - absoluteLocation[1];
				}
			}
		} else if (absoluteLocation[1] != standard[1]){
			if (absoluteLocation[1] > standard[1]) {
				vector[1] = -slowDownYVector;
				if (vector[1] + absoluteLocation[1] > standard[1]) {
					vector[1] = standard[1] - absoluteLocation[1];
				}
			} else if (absoluteLocation[1] < standard[1]) {
				vector[1] = slowDownYVector;
				if (vector[1] + absoluteLocation[1] < standard[1]) {
					vector[1] = standard[1] - absoluteLocation[1];
				}
			}
		}
		else if (absoluteLocation[1] == standard[1]){
			vector[1] = 0;
		}

//		System.out.println(vector[1] + "  x " + touchingUp/*absoluteLocation[1] + "  " + standard[1]*/);
		reTrueY();

	}

	private void reTrueY() {
		touchingDown = touchingUp = false;
	}

	@Override
	public void touchingY(int y, GameObject staticObject) {
		// TODO Auto-generated method stub
		boolean playerOnTop = staticObject.myRectangle.y < myRectangle.y;
		if (playerOnTop){
			goingUp = true;
			touchingUp = true;
		}else{
			goingDown = true;
			touchingDown = true;
		}
		((PushAble) staticObject).touching[1] = this.type;
		int location;
		boolean playerLocUp = staticObject.myRectangle.y < myRectangle.y;
//		System.out.println(staticObject.myRectangle.y + "   " + myRectangle.y);
		if (playerLocUp){
			location = absoluteLocation[1] - staticObject.myRectangle.height*INCREASE;
		} else {
			location = absoluteLocation[1] + myRectangle.height*INCREASE;
		}
		((PushAble) staticObject).jiggleY(vector[1], playerLocUp, 
				location);			
//		System.out.println(staticObject.toString());
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		super.baseDraw(g);
	}

}
