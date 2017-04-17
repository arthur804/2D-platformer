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
	private boolean sticky;

	public SMovingWall(Rectangle bounds, ThingsInTheWorld e, Point[] points, int xSpeed, int ySpeed, int flyingSpeed, boolean sticky) {
		super(bounds, e);
		this.points = points;
		step++;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.flyingSpeed = flyingSpeed;
		this.sticky = sticky;
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
		goingUp = false;
		goingDown = false;
		if (step == points.length)
			step = 0;
		vector[0] = vector[1] = 0;
	}

	@Override
	protected void calcNextY() {
		if (goingUp) {
			if (points[step].y >= myRectangle.y){
				nextStep();
			} else
				vector[1] -= ySpeed;
		} else if (goingDown) {
			if (points[step].y <= myRectangle.y){
				nextStep();
			}else
				vector[1] += ySpeed;
		} else {
			if (points[step].y != myRectangle.y){
				if (points[step].y < myRectangle.y){
					goingUp = true;
				}else {
					goingDown = true;
				}
			}
		}
		
		if (vector[1] > flyingSpeed)
			vector[1] = flyingSpeed;
		else if (vector[1] < -flyingSpeed)
			vector[1] = -flyingSpeed;
	}

	@Override
	public void touchingX(int x, GameObject staticObject) {
		if (staticObject instanceof MyMovingObject){
			((MyMovingObject) staticObject).touching[0] = this.type;
			((MyMovingObject) staticObject).pushedX(absoluteLocation[0], this.myRectangle.width * INCREASE, vector[0], goingLeft, staticObject.myRectangle.x < myRectangle.x);
		}	
	}

	@Override
	public void touchingY(int y, GameObject staticObject) {
		if (staticObject instanceof MyMovingObject){
			((MyMovingObject) staticObject).touching[1] = this.type;
			((MyMovingObject) staticObject).pushedY(absoluteLocation[1], vector[1], goingUp, staticObject.myRectangle.y < myRectangle.y, sticky);			
		}	
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		super.baseDraw(g);
	}

}
