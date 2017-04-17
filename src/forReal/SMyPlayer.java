package forReal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;
import staticClasses.Formulas;

public class SMyPlayer extends MyMovingObject {

	public boolean left, right, up, down;

	public boolean lookingRight = true;

	public boolean wallJumped = false;
	private boolean jumped = false;

	// TODO this is temporarly
	private Rectangle eyeTangle = new Rectangle(0, 0, 5, 3);

	public SMyPlayer(Point rec) {
		super(new Rectangle(rec, new Dimension(16, 16)), ThingsInTheWorld.PLAYER);
	}

	protected void calcNextX() {
		// If im standing on ice slowdownspeed will change TODO
		int speed;
		int slowSpeed;

		// get what speed we are able to walk at
		if (touching[1] != null) {
			speed = touching[1].getWalkingSpeed();
			slowSpeed = touching[1].getSlowDownSpeed();
		} else {
			speed = Formulas.STANDARGD_SPEEDFLYING;
			slowSpeed = Formulas.STANDARGD_SLOWDOWNSPEEDFLYING;
		}

		// if you are flying after a wall jump dont calculate new vector
		if (wallJumped && up && !touchingDown)
			return;
		// when you dont press up you need to be able to jump again if you touch
		// something to jump TODO put this where it belongs
		if (!up) {
			wallJumped = false;
			jumped = false;
		}

		if (vector[0] > 0) {
			if (left) { // going left
				vector[0] -= speed;

				lookingRight = false;
			} else if (right){
				vector[0] += speed;
				lookingRight = true;
			}else {
				vector[0] -= slowSpeed;

				if (vector[0] < 0)
					vector[0] = 0;
			}

		} else if (vector[0] < 0) {
			if (left){
				vector[0] -= speed;
				lookingRight = false;
			} else if (right) {
				vector[0] += speed;
				lookingRight = true;
			} else {
				vector[0] += slowSpeed;
				if (vector[0] > 0)
					vector[0] = 0;
			}

		} else {
			if (!left && right) {
				vector[0] += speed;
				lookingRight = true;
			} else if (left && !right) {
				vector[0] -= speed;
				lookingRight = false;
			}
		}

		// TODO use what you are waling on
		if (vector[0] > Formulas.STANDARD_MAXWALKINGSPEED)
			vector[0] = Formulas.STANDARD_MAXWALKINGSPEED;
		else if (vector[0] < -Formulas.STANDARD_MAXWALKINGSPEED)
			vector[0] = -Formulas.STANDARD_MAXWALKINGSPEED;
		
		reTrueX();
		
		if (vector[0] > 0){
			goingRight = true;
		} else if (vector[0] < 0){
			goingLeft = true;
		}
	}

	protected void calcNextY() {
		if (!touchingDown) {
			if (!goingUp && (touchingLeft || touchingRight)) {// Sliding walls
				// wallJump
				if (up)
					if (wallJump()){}
					// WallSliding
					else if (vector[1] < touching[0].getMaxSlidingSpeed())
						vector[1] += touching[0].getSlide();
					else
						vector[1] -= touching[0].getMaxSlowDown();
			} else
				vector[1] += Formulas.FALINGSPEED;
			// going Down
		} else if (up) {
			jump();
			// going Up
		}
		
		reTrueY();
		
		if (vector[1] < 0){
			goingUp = true;
		} else if (vector[1] > 0){
			goingDown = true;
		}
	}

	private void reTrueY() {
		touchingUp = touchingDown = goingUp = goingDown = false;
		touching[1] = null;
		
	}
	
	private void reTrueX() {
		touchingLeft = touchingRight = goingLeft = goingRight = false;
		touching[0] = null;
		
	}

	public void jump() {
		if (touching[1] != null)
			vector[1] = -touching[1].getJump();
		jumped = true;
	}

	private boolean wallJump() {
		if ((jumped || wallJumped) && up)
			return false;

		if (touchingLeft) {
			vector[0] = touching[0].getWallJumpDistance();
		} else if (touchingRight) {
			vector[0] = -touching[0].getWallJumpDistance();
		}
		vector[1] = -touching[0].getWallJumpHeight();
		wallJumped = true;
		lookingRight = !lookingRight;
		return true;
	}

	// TODO remove this its teporarly for the eye
	@Override
	public void update() {
		absoluteLocation[0] += vector[0];
		absoluteLocation[1] += vector[1];

		myRectangle.setLocation(absoluteLocation[0] / INCREASE, absoluteLocation[1] / INCREASE);
		if (!lookingRight)
			eyeTangle.setLocation(myRectangle.x + 2, myRectangle.y + 3);
		else
			eyeTangle.setLocation(myRectangle.x + myRectangle.width - 2 - eyeTangle.width, myRectangle.y + 3);
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fill(myRectangle);
		g.setColor(Color.BLUE);
		g.fill(eyeTangle);
		// super.baseDraw(g);
	}

	// @Override
	// public int nextY() {
	// int number = super.nextY();
	// if (!goingUp){
	// goingDown = true;
	// number++; // otherwise it wont see someone standing on the floor as
	// someone standing on the floor
	// }
	// return number;
	// }

	public void controls(boolean[] keysPressed) {
		left = keysPressed[0];
		up = keysPressed[1];
		right = keysPressed[3];
		down = keysPressed[2];
	}

	

}
