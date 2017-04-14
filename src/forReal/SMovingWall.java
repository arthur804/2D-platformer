package forReal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;
import staticClasses.Formulas;

public class SMovingWall extends MyMovingObject {

	private Point[] points;
	private int xSpeed;
	private int ySpeed;
	private int step = 0;
	private int flyingSpeed;

	public SMovingWall(Rectangle bounds, ThingsInTheWorld e, Point[] points, int xSpeed, int ySpeed, int flyingSpeed) {
		super(bounds, e);
		this.points = points;
		step++;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.flyingSpeed = flyingSpeed;
	}

	@Override
	protected void calcNextX() {
		if (goingLeft) {
			if (points[step].x >= myRectangle.x){
				nextStep();
			} else
				vector[0] -= xSpeed;
		} else if (goingRight) {
			if (points[step].x <= myRectangle.x){
				nextStep();
			}else
				vector[0] += xSpeed;
		} else {
			if (points[step].x != myRectangle.x){
				if (points[step].x < myRectangle.x){
					goingLeft = true;
				}else {
					goingRight = true;
				}
			}
		}
		
		if (vector[0] > flyingSpeed)
			vector[0] = flyingSpeed;
		else if (vector[0] < -flyingSpeed)
			vector[0] = -flyingSpeed;
		
	}
	
	public void nextStep(){
		step++;
		goingLeft = false;
		goingRight = false;
		if (step == points.length)
			step = 0;
		vector[0] = vector[1] = 0;
	}

	@Override
	protected void calcNextY() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchingX(int x, GameObject staticObject) {
		if (staticObject instanceof MyMovingObject){
//			((MyMovingObject) staticObject).touchingX(0, this);
			
			int extra;
			if (goingLeft){
				extra = absoluteLocation[0] -((MyMovingObject) staticObject).myRectangle.width * INCREASE;
			} else {
				extra = absoluteLocation[0] + this.myRectangle.width * INCREASE;
			}
			((MyMovingObject) staticObject).pushedX(extra, vector[0], goingLeft);
		}	
	}

	@Override
	public void touchingY(int y, GameObject staticObject) {
		if (staticObject instanceof MyMovingObject){
			((MyMovingObject) staticObject).touchingY(this.myRectangle.y, this);				
		}	
	}
	
	//TODO
	@Override
	public void reTrue() {
//		if (step == points.length)
//			step = 0;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		super.baseDraw(g);
	}

}
