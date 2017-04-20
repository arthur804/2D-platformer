package forReal;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
	
	public SMyPlayer(Point rec, BufferedImage spriteSheet, BufferedImage spriteSheetWalk, int sizeSpriteX, int sizeSpriteY, int xPixelsBet,int yPixelsBet){
		super(new Rectangle(rec, new Dimension(32, 32)), ThingsInTheWorld.PLAYER, spriteSheet, sizeSpriteX, sizeSpriteY, xPixelsBet, yPixelsBet);
		myAnimatorWalk = new Animation(spriteSheetWalk, sizeSpriteX, sizeSpriteY, xPixelsBet, yPixelsBet);
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

		if (vector[0] > 0) {
			goingRight = true;
		} else if (vector[0] < 0) {
			goingLeft = true;
		}
	}

	protected void calcNextY() {
		if (!touchingDown) {
			if (!goingUp && (touchingLeft || touchingRight)) {// Sliding walls
				// wallJump
				if (up)
					if (wallJump()) {
					}
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

		
	}

	private void reTrueY() {
		if (vector[1] < 0) {
			goingUp = true;
			goingDown = false;
		} else if (vector[1] > 0) {
			goingDown = true;
		}
		touchingUp = touchingDown = goingUp = false;
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
		if (lookingRight != imageIsLookingRight){
			//all animators need to be fliped here
			myAnimator.flip();
			myAnimatorWalk.flip();
			imageIsLookingRight = !imageIsLookingRight;
		}
		if (animationWalkFrames == CHANGE_FRAME_AMOUNT){
			animationWalkFrames = 0;
			myAnimator.nextFrame();
			myAnimatorWalk.nextFrame();
		}
		System.out.println(touchingDown + " x " + goingDown);
		if (goingDown && (left || right)){
			super.baseDraw(g, myRectangle, myAnimatorWalk.getCorrectFrame());
		}else
			super.baseDraw(g, myRectangle, myAnimator.getCorrectFrame());

	}

}
