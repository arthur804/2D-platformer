package forReal;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;
import staticClasses.Formulas;

public class SMyPlayer extends MyMovingObject {

	public boolean left, right, up, down;

	public boolean lookingRight = true;
	private boolean imageIsLookingRight = true;

	public boolean wallJumped = false;
	private boolean jumped = false;

	private Animation myAnimatorWalk;
	private int animationWalkFrames = 0;
	private static final int CHANGE_FRAME_AMOUNT = 20;
	private static final int STEP_UP = 5;

	public SMyPlayer(Point rec, BufferedImage spriteSheet, BufferedImage spriteSheetWalk, int sizeSpriteX,
			int sizeSpriteY, int xPixelsBet, int yPixelsBet) {
		super(new Rectangle(rec, new Dimension(30, 30)), ThingsInTheWorld.PLAYER, spriteSheet, sizeSpriteX, sizeSpriteY,
				xPixelsBet, yPixelsBet);
		myAnimatorWalk = new Animation(spriteSheetWalk, sizeSpriteX, sizeSpriteY, xPixelsBet, yPixelsBet);
	}

	protected void calcNextX() {
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

		if (vector[0] > 0) {
			if (left) { // going left
				vector[0] -= speed;

				lookingRight = false;
			} else if (right) {
				vector[0] += speed;
				lookingRight = true;
			} else {
				vector[0] -= slowSpeed;

				if (vector[0] < 0)
					vector[0] = 0;
			}

		} else if (vector[0] < 0) {
			if (left) {
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

	}

	protected void calcNextY() {
		vector[1] = 40000;
//		if (!up) {
//			wallJumped = false;
//			jumped = false;
//		}
//
//		if (!touchingDown) {
//			if (!goingUp && (touchingLeft || touchingRight)) {// Sliding walls
//				// wallJump
//				if (up)
//					if (wallJump()) {
//					}
//					// WallSliding
//					else if (vector[1] < touching[0].getMaxSlidingSpeed())
//						vector[1] += touching[0].getSlide();
//					else
//						vector[1] -= touching[0].getMaxSlowDown();
//			} else
//				vector[1] += Formulas.FALINGSPEED;
//			// going Down
//		} else if (up) {
//			jump();
//			// going Up
//		}
//
		reTrueY();

	}

	private void reTrueY() {
		if (touchingUp && touchingDown)
			dead = true;
		if (vector[1] < 0) {
			goingUp = true;
			goingDown = false;
		} else if (vector[1] > 0) {
			goingDown = true;
			goingUp = false;
		}
		touchingUp = touchingDown = false;
		touching[1] = null;

	}

	private void reTrueX() {
		if (touchingLeft && touchingRight)
			dead = true;
		touchingLeft = touchingRight = goingLeft = goingRight = false;
		touching[0] = null;
		
		if (vector[0] > 0) {
			goingRight = true;
		} else if (vector[0] < 0) {
			goingLeft = true;
		}

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

	@Override
	public void preUpdate() {
		super.preUpdate();
		animationWalkFrames++;
	}

	public void controls(boolean[] keysPressed) {
		left = keysPressed[0];
		up = keysPressed[1];
		right = keysPressed[3];
		down = keysPressed[2];
	}

	@Override
	public void draw(Graphics2D g) {
		if (lookingRight != imageIsLookingRight) {
			// all animators need to be fliped here
			myAnimator.flip();
			myAnimatorWalk.flip();
			imageIsLookingRight = !imageIsLookingRight;
		}
		if (animationWalkFrames == CHANGE_FRAME_AMOUNT) {
			animationWalkFrames = 0;
			myAnimator.nextFrame();
			myAnimatorWalk.nextFrame();
		}
		if (goingDown && (left || right)) {
			super.baseDraw(g, myRectangle, myAnimatorWalk.getCorrectFrame());
		} else
			super.baseDraw(g, myRectangle, myAnimator.getCorrectFrame());

	}

	@Override
	public void touchingX(int x, GameObject staticObject) {
		if (staticObject.myRectangle.y > myRectangle.y + STEP_UP && staticObject.myRectangle.y < myRectangle.y + myRectangle.width +1) {
			absoluteLocation[1] = (staticObject.myRectangle.y - myRectangle.height) * INCREASE;
			if (staticObject instanceof SMovingWall) {
				((SMovingWall) staticObject).touchingX(0, this);
				return;
			}
			if (goingDown)
				vector[1] = 0;
		} else
			super.touchingX(x, staticObject);
	}

}
