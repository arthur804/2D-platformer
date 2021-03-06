package movingWalls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import eccentialItems.SMyPlayer;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.PushAble;
import interfacesAndAbstract.ThingsInTheWorld;

//TODO V that
@SuppressWarnings("unused")
 //@Deprecated //TODO it doesnt work
public class JiggelingWall extends BaseMovingWall {

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
	
	private boolean stoped = false;

	public JiggelingWall(Rectangle bounds, ThingsInTheWorld e, int yMaxWiggle, int xMaxWiggle, int xMaxSpeed,
			int yMaxSpeed, int flyingSpeed, boolean sticky) {
		super(bounds, e, flyingSpeed, flyingSpeed, flyingSpeed, sticky);
		this.xMaxWiggle = (bounds.x + xMaxWiggle) * INCREASE;
		this.yMaxWiggle = (bounds.y + yMaxWiggle) * INCREASE;
		this.xMinWiggle = (bounds.x - xMaxWiggle) * INCREASE;
		this.yMinWiggle = (bounds.y - yMaxWiggle) * INCREASE;
		this.standard = new int[]{bounds.x * INCREASE, bounds.y * INCREASE};
	}

	@Override
	protected void calcNextX() {
		// test if have enough speed

	}

	@Override
	protected void calcNextY() {
		if (touchingUp) {

				goingDown = true;
				if (vector[1] == 0) {
					vector[1] = touchingYVector;
				} else {
					vector[1] += ySpeed;
					if (vector[1] > maxYFlyingSpeed)
						vector[1] = maxYFlyingSpeed;
				}
				if (vector[1] + absoluteLocation[1] > yMaxWiggle){
					vector[1] = yMaxWiggle - absoluteLocation[1];
				}

			
		} else if (absoluteLocation[1] != standard[1]){
			if (absoluteLocation[1] > standard[1]) {
				vector[1] = -slowDownYVector;
				if (vector[1] + absoluteLocation[1] < standard[1]) {					
					vector[1] = standard[1] - absoluteLocation[1];
				}
			} 
			else if (absoluteLocation[1] < standard[1]) {
				vector[1] = slowDownYVector;
				if (vector[1] + absoluteLocation[1] > standard[1]) {
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
	public void touchingX(int x, GameObject staticObject){

		((PushAble) staticObject).touching[0] = this.type;
		((PushAble) staticObject).pushedX(vector[0], goingLeft, staticObject.myRectangle.x < myRectangle.x);
	}
	
	@Override
	public void touchingY(int y, GameObject staticObject) {
		// TODO Auto-generated method stub
		((PushAble) staticObject).touching[1] = this.type;
		boolean playerOnTop = staticObject.myRectangle.y < myRectangle.y;
		if (playerOnTop){
			goingUp = true;
			touchingUp = true;
		}else{
			int location;
			location = absoluteLocation[1] + myRectangle.height*INCREASE;
			((PushAble) staticObject).pushedY(vector[1], goingUp, 
					playerOnTop , false, 0, location);		
			return;
		}
		int location;
		location = absoluteLocation[1] - staticObject.myRectangle.height*INCREASE;
		
		//TODO
		boolean goingUp = false;
		if (vector[1] == 0)//we are standing still
			goingUp = true;
		((PushAble) staticObject).pushedY(vector[1], goingUp, 
				true , false, vector[0], location);	
		((PushAble) staticObject).touchingDown = ((PushAble) staticObject).goingDown = true;
		if (staticObject instanceof SMyPlayer)
			((SMyPlayer) staticObject).touchingNotMovingNow();
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		super.baseDraw(g);
	}

}
