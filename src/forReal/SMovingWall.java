package forReal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;

public class SMovingWall extends MyMovingObject {

	private Point[] points;
	private int xSpeed;
	private int ySpeed;
	private int step = 0;

	public SMovingWall(Rectangle bounds, ThingsInTheWorld e, Point[] points, int xSpeed, int ySpeed) {
		super(bounds, e);
		this.points = points;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	protected void calcNextX() {
		if (goingLeft) {
			if (points[step].x >= myRectangle.x)
				step++;
			else
				vector[0] -= xSpeed;
		} else if (goingRight) {
			if (points[step].x <= myRectangle.x)
				step++;
			else
				vector[0] += xSpeed;
		} else {
			if (points[step].x != myRectangle.x){
				if (points[step].x < myRectangle.x)
					goingLeft = true;
				else
					goingRight = true;
			}
		}
	}

	@Override
	protected void calcNextY() {
		// TODO Auto-generated method stub

	}

	//TODO
	@Override
	public void reTrue() {};
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);

	}

}
