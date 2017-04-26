package MovingWalls;

import java.awt.Point;
import java.awt.Rectangle;

import forReal.SMyPlayer;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;

//TODO V that
@SuppressWarnings("unused")
public class JiggelingWall extends BaseMovingWall{

	
	private int xMaxWiggle;
	private int yMaxWiggle;
	private int xMinWiggle;
	private int yMinWiggle;
	private Point standard;
	private int touchingYVector = 0;
	private int touchingXVector;
	private int sensitivity;//TODO need this?
	private boolean stoped = false;
	
	public JiggelingWall(Rectangle bounds, ThingsInTheWorld e, int yMaxWiggle, int xMaxWiggle , int xMaxSpeed, int yMaxSpeed, int flyingSpeed,
			boolean sticky, int sensitivity) {
		super(bounds, e, xMaxSpeed, yMaxSpeed, flyingSpeed, sticky);
		this.xMaxWiggle = bounds.x + xMaxWiggle;
		this.yMaxWiggle = bounds.y + yMaxWiggle;
		this.xMinWiggle = bounds.x - xMaxWiggle;
		this.yMinWiggle = bounds.y - yMaxWiggle;
		this.standard = new Point(bounds.x, bounds.y);
		this.sensitivity = sensitivity;
	}

	@Override
	protected void calcNextX() {
		//test if have enough speed
		
		
	}

	@Override
	protected void calcNextY() {
		if (touchingYVector != 0 || touchingUp || touchingDown){
				vector[1] = touchingYVector;
		} else
			if (myRectangle.y > standard.y){//als het groter dan 0 is het verschil na een fix dan moet hij direct op nul gezet worden
				vector[1] = -flyingSpeed;
				if (vector[1] + absoluteLocation[1] < standard.y*INCREASE){
					vector[1] = -absoluteLocation[1] + standard.y*INCREASE;
				}
			} else {
				vector[1] = flyingSpeed;
				if (vector[1] + absoluteLocation[1] > standard.y*INCREASE){
					vector[1] = -absoluteLocation[1] + standard.y*INCREASE;
				}
			}
		reTrueY();
		
	}
	
	private void reTrueY() {
		if (!touchingDown || !touchingUp)//TODO slowdown
			touchingYVector = 0;
		touchingDown = touchingUp = false;
	}

	@Override
	public void touchingX(int x, GameObject staticObject) {
		if (staticObject instanceof MyMovingObject){
			//TODO test
			touchingXVector = ((MyMovingObject) staticObject).vector[0];
			boolean left = staticObject.myRectangle.x < myRectangle.x;
			if (left)
				touchingRight = true;
			else
				touchingLeft = true;
			
			((MyMovingObject) staticObject).touching[0] = this.type;
			((MyMovingObject) staticObject).pushedX(vector[0], goingLeft, left);
		}	
	}

	@Override
	public void touchingY(int y, GameObject staticObject) {
		boolean up = staticObject.myRectangle.y < myRectangle.y;
		if (up){
			touchingUp = true;
		}
		else {
			touchingDown = true;
		}
		if (staticObject instanceof SMyPlayer && touchingUp && ((SMyPlayer) staticObject).up){
			touchingYVector = ((MyMovingObject) staticObject).vector[1];//bodyMass*2*sensitivity;
		} else
			touchingYVector = ((MyMovingObject) staticObject).vector[1];//bodyMass*sensitivity;
		
		if (!up)
			touchingYVector = -touchingYVector;
		
		if (touchingYVector < -ySpeed)
			touchingYVector = -ySpeed;
		else if (touchingYVector > ySpeed)
			touchingYVector = ySpeed;
		
		((MyMovingObject) staticObject).touching[1] = this.type;
		if (up)
			((MyMovingObject) staticObject).absoluteLocation[1] = -((MyMovingObject) staticObject).myRectangle.height*INCREASE + absoluteLocation[1];
		else
			((MyMovingObject) staticObject).absoluteLocation[1] = myRectangle.height*INCREASE + absoluteLocation[1] + INCREASE;
		
		((MyMovingObject) staticObject).pushedY(touchingYVector, !up, 
				up, sticky, vector[0]);	
			
	}

}
