package MovingWalls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;

public class NormalMovingWall extends BaseMovingWall {

	private Point[] points;
	private int stepY = 0;
	private int stepX = 0;

	public NormalMovingWall(Rectangle bounds, ThingsInTheWorld e, Point[] points, int xSpeed, int ySpeed,
			int flyingSpeed, boolean sticky) {
		super(bounds, e, xSpeed, ySpeed, flyingSpeed, sticky);
		this.points = points;
		stepY++;
		stepX++;
	}

	@Override
	protected void calcNextX() {
		if (goingLeft) {
			if (points[stepX].x >= myRectangle.x) {
				nextStepX();
			} else
				vector[0] -= xSpeed;
		} else if (goingRight) {
			if (points[stepX].x <= myRectangle.x) {
				nextStepX();
			} else
				vector[0] += xSpeed;
		} else {
			if (points[stepX].x != myRectangle.x) {
				if (points[stepX].x < myRectangle.x) {
					goingLeft = true;
				} else {
					goingRight = true;
				}
			}
		}
		if (vector[0] > flyingSpeed)
			vector[0] = flyingSpeed;
		else if (vector[0] < -flyingSpeed)
			vector[0] = -flyingSpeed;
		//
	}

	protected void nextStepY() {
		stepY++;
		goingUp = false;
		goingDown = false;
		if (stepY == points.length)
			stepY = 0;
		vector[1] = 0;
	}

	protected void nextStepX() {
		stepX++;
		goingLeft = false;
		goingRight = false;
		if (stepX == points.length)
			stepX = 0;
		vector[0] = 0;
	}

	@Override
	protected void calcNextY() {
		if (goingUp) {
			if (points[stepY].y >= myRectangle.y) {
				nextStepY();
			} else
				vector[1] -= ySpeed;
		} else if (goingDown) {
			if (points[stepY].y <= myRectangle.y) {
				nextStepY();
			} else
				vector[1] += ySpeed;
		} else {
			if (points[stepY].y != myRectangle.y) {
				if (points[stepY].y < myRectangle.y) {
					goingUp = true;
				} else {
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
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		super.baseDraw(g);
	}

}
