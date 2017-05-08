package eccentialItems;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import interfacesAndAbstract.ContainsMovers;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.PushAble;
import interfacesAndAbstract.ThingsInTheWorld;
import staticClasses.Formulas;

public class SMyPlayer extends PushAble {

	public boolean left, right, up, down;

	public boolean lookingRight = true;
	private boolean imageIsLookingRight = true;

	public boolean wallJumped = false;
	private boolean jumped = false;

	private int heightLaunch = 0;
	private int animationWalkFrames = 0;
	//you will only ever be albe to be in one thing this means that if you want to have under water plants and you have to be in water and in those plants you have to make an underwater variant of the plant and give it a higher priority 
	private ContainsMovers iAmIn = null;

	private static final int CHANGE_FRAME_AMOUNT = 10;
	private static final int STEP_UP = 5;

	public SMyPlayer(Point rec, BufferedImage spriteSheet, int sizeSpriteX,
			int sizeSpriteY, int xPixelsBet, int yPixelsBet, int[] playerSpriteArray) {
		super(new Rectangle(rec, new Dimension(sizeSpriteX, sizeSpriteY)), ThingsInTheWorld.PLAYER, spriteSheet, sizeSpriteX, sizeSpriteY,
				xPixelsBet, yPixelsBet);
		myAnimator = new MultipleStatesAnimation(spriteSheet, sizeSpriteX, sizeSpriteY, xPixelsBet, yPixelsBet, playerSpriteArray);
	}

	protected void calcNextX() {
		//TODO container make better
		int speed;
		int slowSpeed;
		int maxWalk;
		boolean isItBigger;
		if (iAmIn != null){
			if (iAmIn.MOVE_IN_X){
			ThingsInTheWorld type = iAmIn.type;
			speed = type.getWalkingSpeed();
			slowSpeed = type.getSlowDownWalkingSpeed();
			maxWalk = type.getMaxWalkingSpeed();
			isItBigger = type.isWalkSlowerThenSlowdown();
			} else {
				vector[0] = 0;
				return;
			}
		}else {
			if (touching[1] != null) {
				speed = touching[1].getWalkingSpeed();
				slowSpeed = touching[1].getSlowDownWalkingSpeed();
				isItBigger = touching[1].isWalkSlowerThenSlowdown();
			} else {
				speed = Formulas.STANDARGD_SPEEDFLYING;
				slowSpeed = Formulas.STANDARGD_SLOWDOWNSPEEDFLYING;
				isItBigger = false;
			}
			// TODO use what you are waling on
			maxWalk = Formulas.STANDARD_MAXWALKINGSPEED;
			if (touching[0] != null)
				maxWalk = touching[0].getMaxWalkingSpeed();
		
		}
		if (vector[0] > standardVector) {
			if (left) { // going left
				vector[0] -= speed;
				if (isItBigger)
					vector[0] -= slowSpeed;
				lookingRight = false;
			} else if (right) {
				vector[0] += speed;
				lookingRight = true;
			} else {
				vector[0] -= slowSpeed;

				if (vector[0] < standardVector)
					vector[0] = standardVector;
			}

		} else if (vector[0] < standardVector) {
			if (left) {
				vector[0] -= speed;
				lookingRight = false;
			} else if (right) {
				vector[0] += speed;
				if (isItBigger)
					vector[0] += slowSpeed;
				lookingRight = true;
			} else {
				vector[0] += slowSpeed;
				if (vector[0] > standardVector)
					vector[0] = standardVector;
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
		
		if (vector[0] > standardVector + maxWalk)
			vector[0] = standardVector + maxWalk;
		else if (vector[0] < standardVector - maxWalk)
			vector[0] = standardVector - maxWalk;
		reTrueX();

	}

	

	protected void calcNextY() {
		//TODO container
		if (iAmIn != null){
			
		}else{
			normalYCalc();
		}
		reTrueY();

	}

	private void normalYCalc(){
		if (!up) {
			if (vector[1] < -200){
				vector[1] = -200;
			} 
			wallJumped = false;
			jumped = false;
		}

		if (!touchingDown) {
			touchingNotMovingNow();
			if (!goingUp && (touchingLeft || touchingRight)) {// Sliding walls
				// wallJump
				if (up)
					if (wallJump()) {
					}
					// WallSliding
					else if (vector[1] < touching[0].getMaxSlidingSpeedOrArrowDown())
						vector[1] += touching[0].getWallSlideOrGliding();
					else
						vector[1] -= touching[0].getMaxSlowDownOrGlidingMax();
			} else
				vector[1] += Formulas.FALINGSPEED;
			// going Down
		} else if (up) {
			jump();
			// going Up
		}
	}
	private void reTrueY() {
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
		touchingLeft = touchingRight = goingLeft = goingRight = false;
		touching[0] = null;
		standardVector = 0;
		
		if (vector[0] > 0) {
			goingRight = true;
		} else if (vector[0] < 0) {
			goingLeft = true;
		}

	}

	public void jump() {
		if (touching[1] != null)
			vector[1] = -touching[1].getJumpHeight() + heightLaunch;
		jumped = true;
	}

	private boolean wallJump() {
		if ((jumped || wallJumped) && up)
			return false;

		if (touchingLeft) {
			vector[0] = touching[0].getWallJumpDistanceOrMaxFallingSpeed();
		} else if (touchingRight) {
			vector[0] = -touching[0].getWallJumpDistanceOrMaxFallingSpeed();
		}
		vector[1] = -touching[0].getWallJumpHeightOrFallingSpeed();
		wallJumped = true;
		lookingRight = !lookingRight;
		return true;
	}

	@Override
	public void preUpdate() {
		super.preUpdate();
		if (!touchingDown)
			animationWalkFrames += 2;
		else 
			animationWalkFrames ++;
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
			myAnimator.flip();
			imageIsLookingRight = !imageIsLookingRight;
		}
		if (animationWalkFrames >= CHANGE_FRAME_AMOUNT) {
			animationWalkFrames = 0;
			myAnimator.nextFrame();
		}
		if (goingDown && touchingDown && (left || right)) {
			((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(1);
		} else if (!touchingDown){
			if (goingDown){
				if ((left || right)){		
					((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(3);
				} else {
					((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(2);
				} 
			} else if (goingUp){
				int mvec = -Formulas.STANDARDJUMPHEIGHT / 4;
				if (vector[1] > mvec){
					((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(2);
				} else if (vector[1] > mvec*2){
					((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(6);
				} else if (vector[1] > mvec*3){
					((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(5);
				} else if (vector[1] < -INCREASE){//TODO
					((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(4);
				}
			} 
		} else {
			((MultipleStatesAnimation)(myAnimator)).setAnimationGetting(0);
		}
		super.baseDraw(g, myRectangle, myAnimator.getCorrectFrame());

	}

	@Override
	public void touchingX(int x, GameObject staticObject) {
		if (!goingUp && staticObject.canBeStepedOn && staticObject.myRectangle.y > myRectangle.y + STEP_UP && staticObject.myRectangle.y < myRectangle.y + myRectangle.width +1) {
			absoluteLocation[1] = (staticObject.myRectangle.y - myRectangle.height) * INCREASE;
			if (goingDown){
				vector[1] = 0;
			}
		} else
			super.touchingX(x, staticObject);
	}
	
	@Override
	public void pushedY(int newVector, boolean movingWallIsGoingUp, boolean playerIsAboveWall, boolean isSticky,
			int standardVector, int newLocation) {
		heightLaunch = newVector;
		super.pushedY(newVector, movingWallIsGoingUp, playerIsAboveWall, isSticky, standardVector, newLocation);
	}

	public void touchingNotMovingNow(){
		heightLaunch = 0;
	}

	public void isIn(ContainsMovers containsMovers) {
		iAmIn = containsMovers;
		
	}

	
}
