package staticClasses;

import java.awt.Rectangle;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;

import forReal.SMovingWall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class RenderAndLocation {

	private RenderAndLocation() {
	}

	public static boolean isObjectInSight(Rectangle shape, int beginX, int beginY, int endX, int endY) {

		boolean seen = false;
		if (shape.x < endX && shape.y < endY) {
			if (shape.x < beginX) {
				if (shape.y > beginY) {
					seen = true;
				}
				if (shape.y + shape.height > beginY) {
					seen = true;
				}
			}
			if (shape.x + shape.getWidth() > beginX) {
				if (shape.y > beginY) {
					seen = true;
				}
				if (shape.y + shape.height > beginY) {
					seen = true;

				}
			}
		}
		return seen;
	}

	// ---------------- seen

	/**
	 * 0 top 1 left 2 down 3 right
	 */
	private static boolean wallTester(Rectangle shapeMoving, Rectangle shape, byte choice) {

		switch (choice) {
		default:
			return onTopWall(shapeMoving, shape);
		case 1:
			return rightTouchWall(shapeMoving, shape);
		case 2:
			return underWall(shapeMoving, shape);
		case 3:
			return leftTouchWall(shapeMoving, shape);
		}
	}

	private static boolean onTopWall(Rectangle shapeMoving, Rectangle shape) {
		return isTouching(shape.y, shape.x, shapeMoving.y, shapeMoving.x, shape.width, shape.height, 0,
				shapeMoving.width);
	}

	private static boolean underWall(Rectangle shapeMoving, Rectangle staticShape) {
		return isTouching(staticShape.y, staticShape.x, shapeMoving.y, shapeMoving.x, staticShape.width, 0,
				shapeMoving.height, shapeMoving.width);
	}

	private static boolean rightTouchWall(Rectangle shapeMoving, Rectangle staticShape) {
		return isTouching(staticShape.x, staticShape.y, shapeMoving.x, shapeMoving.y, staticShape.height, 0,
				shapeMoving.width, shapeMoving.height);
	}

	private static boolean leftTouchWall(Rectangle shapeMoving, Rectangle staticShape) {
		return isTouching(staticShape.x, staticShape.y, shapeMoving.x, shapeMoving.y, staticShape.height,
				staticShape.width, 0, shapeMoving.height);
	}

	/*
	 * var 1 shapex or y var 2 shapex or y int forinVar1, width height or non
	 * int forinVar2, width or height int size1, width or height int size2,
	 * width or height int forinsize1, int forinsize2
	 * 
	 */
	private static boolean isTouching(int var1, int var2, int forinVar1, int forinVar2, int size2, int size1,
			int forinsize1, int forinsize2) {
		boolean isToching = false;
		if (var1 + size1 == forinVar1 + forinsize1)
			if ((var2 < forinVar2 + forinsize2 && var2 + size2 > forinVar2 + forinsize2)
					|| (var2 < forinVar2 && var2 + size2 > forinVar2))
				isToching = true;
			else if ((var2 + size2 > forinVar2 && var2 + size2 < forinVar2 + forinsize2)
					|| (var2 > forinVar2 && var2 < forinVar2 + forinsize2))
				isToching = true;
			else if (var1 == forinVar1 && var2 == forinVar2)
				isToching = true;
			else if (var1 + size1 == forinVar1 + forinsize1 && var2 + size2 == forinVar2 + forinsize2)
				isToching = true;
		return isToching;
	}

	// ---------- location renderCalculation

	public static void walltest(MyMovingObject movingObject, GameObject[] staticObjects) {
		int nextX = movingObject.nextX();
		int nextY = movingObject.nextY();
		
		// ------------------------------------------------UP
		if (movingObject.goingUp) {

			/* Up and left */
			if (movingObject.goingLeft){
				calcLeftRightUpDown(movingObject, nextX, nextY, true, true, staticObjects);
			}
			/* Up and right */
			else if (movingObject.goingRight){
				calcLeftRightUpDown(movingObject, nextX, nextY, true, false, staticObjects);
			}
			/* just Up */
			else{
				calcUpDown(staticObjects, movingObject, nextY, true);
			}
		}
		// ---------------------------------DOWN  
		else if (movingObject.goingDown){

			/* Down and left */
			if (movingObject.goingLeft){
				calcLeftRightUpDown(movingObject, nextX, nextY, false, true, staticObjects);
			}
			/* down and right */
			else if (movingObject.goingRight){
				calcLeftRightUpDown(movingObject, nextX, nextY, false, false, staticObjects);
			}
			/* just down */
			else{
				calcUpDown(staticObjects, movingObject, nextY, false);
			}
		} else {
			if (movingObject.goingLeft){
				calcLeftRightOnly(movingObject, staticObjects, nextX, true);
			}
			/* down and right */
			else if (movingObject.goingRight){
				calcLeftRightOnly(movingObject, staticObjects, nextX, false);
			}
		}
	}
	
	public static void movingWallCalculation(MyMovingObject movingObject, GameObject[] staticObjects){
		int nextX = movingObject.nextX();
		int nextY = movingObject.nextY();

		if (movingObject.goingUp)
			calcUpDown(staticObjects, movingObject, nextY, true);
		
		else if (movingObject.goingDown)
			calcUpDown(staticObjects, movingObject, nextY, false);
		
		if (movingObject.goingLeft){
			calcLeftRightOnly(movingObject, staticObjects, nextX, true);
		}
		
		else if (movingObject.goingRight){
			calcLeftRightOnly(movingObject, staticObjects, nextX, false);
			
		}
	}

	/**
	 * 0 top 1 left 2 down 3 right
	 * 
	 * @param movingObject
	 * @param staticObjects2
	 */

	private static void calcLeftRightUpDown(MyMovingObject movingObject, int nextX, int nextY, boolean goingUp,
			boolean goingLeft, GameObject[] staticObjects) {
		int positiveX = Math.abs(nextX);
		int positiveY = Math.abs(nextY);
		boolean yIsBigger;
		int biggest;
		int smallestInSteps;

		if (positiveX < positiveY) {
//			amountTest = (int) positiveY;
//			divY = 1;
//			divX = (positiveX / positiveY) * numb;
			biggest = positiveY;
			smallestInSteps = positiveX;
			yIsBigger = true;
		} else {
//			amountTest = (int) positiveX;
//			divX = 1;
			biggest = positiveX;
			smallestInSteps = positiveY;
//			divY = (positiveY / positiveX) * numb;
			yIsBigger = false;
		}
		for (int big = 0; big <= biggest; big++) 
			for (int small = 0; small <= smallestInSteps; small++) {
			
			Rectangle tangle;
			
			if (yIsBigger)
				tangle = nextTangle(movingObject, small, big, goingUp, goingLeft);
			else
				tangle = nextTangle(movingObject, big, small, goingUp, goingLeft);
			if (!movingObject.touchingUp && !movingObject.touchingDown) {
				if (goingUp && !movingObject.touchingUp)
					for (int ii = 0; ii < staticObjects.length; ii++) {
						if (!staticObjects[ii].seen)
							continue;
						if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 0)) {
							movingObject.touchingUp = true;
							if (yIsBigger)
								movingObject.touchingY(-big, staticObjects[ii]);
							else
								movingObject.touchingY(-small, staticObjects[ii]);

							break;
						}
					}
				else if (!goingUp && !movingObject.touchingDown)
					for (int ii = 0; ii < staticObjects.length; ii++) {
						if (!staticObjects[ii].seen)
							continue;
						if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 2)) {
							movingObject.touchingDown = true;
							if (yIsBigger)
								movingObject.touchingY(big, staticObjects[ii]);
							else
								movingObject.touchingY(small, staticObjects[ii]);

							break;
						}
					}
			}
			if (!movingObject.touchingLeft && !movingObject.touchingRight) {
				
				if (goingLeft){
					for (int ii = 0; ii < staticObjects.length; ii++) {
						if (!staticObjects[ii].seen)
							continue;
							
						if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 3)) {
							movingObject.touchingLeft = true;
							if (yIsBigger){
								movingObject.touchingX(-small, staticObjects[ii]);
							}else{
								movingObject.touchingX(-big, staticObjects[ii]);
							}
							break;
						}
					}
				}
				else if (!goingLeft){
					for (int ii = 0; ii < staticObjects.length; ii++) {
						if (!staticObjects[ii].seen)
							continue;
						
						if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 1)) {
							movingObject.touchingRight = true;
							if (yIsBigger)
								movingObject.touchingX(small, staticObjects[ii]);
							else
								movingObject.touchingX(big, staticObjects[ii]); 
							break;
						}
					}
				}
			}
//			else if (movingObject.touchingDown || movingObject.touchingUp){
//				break;
//			}

		}
	}

	private static Rectangle nextTangle(MyMovingObject movingObject, int x, int y, boolean goingUp, boolean goingLeft) {
		if (goingUp)
			y = -y;
		if (goingLeft)
			x = -x;
		return movingObject.nextTangle(x, y);
	}

	private static void calcUpDown(GameObject[] staticObjects, MyMovingObject movingObject, int nextY,
			boolean goingUp) {
		byte choice;
		if (goingUp)
			choice = 0;
		else
			choice = 2;
		int amountTest;
		byte pos = 1;
		if (goingUp) {
			amountTest = (int) -nextY;
			pos = (byte) -pos;
		} else
			amountTest = (int) nextY;
		int awnser = -1;
		out: for (int i = 0; i <= amountTest; i++) {
			for (int ii = 0; ii < staticObjects.length; ii++){
				if (!staticObjects[ii].seen)
					continue;
				if (wallTester(movingObject.nextTangleOnlyY(i * pos), staticObjects[ii].myRectangle, (byte) choice)) {
					awnser = ii;
					if (goingUp)
						nextY = -i;
					else
						nextY = i;
					break out;
				}
			}
		}
		if (awnser != -1) {
			if (goingUp) {
				movingObject.touchingUp = true;
			} else {
				movingObject.touchingDown = true;
			}
			movingObject.touchingY(nextY, staticObjects[awnser]);
		}

	}
	
	private static void calcLeftRightOnly(MyMovingObject movingObject, GameObject[] staticObjects, int nextX,
			boolean goingLeft) {
		byte choice;
		if (goingLeft)
			choice = 3;
		else
			choice = 1;
		int amountTest;
		byte pos = 1;
		if (goingLeft) {
			amountTest = (int) -nextX;
			pos = (byte) -pos;
		} else
			amountTest = (int) nextX;
		int awnser = -1;
		out: for (int i = 0; i <= amountTest; i++) {
			for (int ii = 0; ii < staticObjects.length; ii++){
				if (!staticObjects[ii].seen)
					continue;
				if (wallTester(movingObject.nextTangleOnlyX(i * pos), staticObjects[ii].myRectangle, (byte) choice)) {
					awnser = ii;
					if (goingLeft)
						nextX = -i;
					else
						nextX = i;
					break out;
				}
			}
		}
		if (awnser != -1) {
			if (goingLeft) {
				movingObject.touchingLeft = true;
			} else {
				movingObject.touchingRight = true;
			}
			movingObject.touchingX(nextX, staticObjects[awnser]);
		}

	}
	
}
