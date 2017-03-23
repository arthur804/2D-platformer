package staticClasses;

import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class RenderAndLocation {

	private RenderAndLocation() {
	}

	public static boolean isObjectInSight(GameObject object, int beginX, int beginY, int endX, int endY) {

		Rectangle shape = object.myRectangle;
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
	 * returns true if points are on top of this wall THIS TAKES THE POINT AT
	 * THE LEFT BOTTOM
	 */

	/**
	 * 0 top 1 left 2 down 3 right
	 */
	public static boolean wallTester(Rectangle shapeMoving, Rectangle shape, byte choice) {

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
		movingObject.reTrue();

		// ------------------------------------------------UP
		if (nextY < 0) {

			/* Up and left */
			if (nextX < 0){
				calcLeftRight(movingObject, nextX, nextY, true, true, staticObjects);
				
			}
			/* Up and right */
			else if (nextX > 0){
				calcLeftRight(movingObject, nextX, nextY, true, false, staticObjects);
			}
			/* just Up */
			else{
				calcUpDown(staticObjects, movingObject, nextY, true);
			}
		}
		// ---------------------------------DOWN  if your not going up your going down not going down is not possible otherwise i would need a calcLeftRightonly
		else {

			/* Down and left */
			if (nextX < 0){
				calcLeftRight(movingObject, nextX, nextY, false, true, staticObjects);
			}
			/* down and right */
			else if (nextX > 0){
				calcLeftRight(movingObject, nextX, nextY, false, false, staticObjects);
			}
			/* just down */
			else{
				calcUpDown(staticObjects, movingObject, nextY, false);
			}

		}
	}
	// private static void calcTheX(ArrayList<GameObject> staticObjects,
	// MyMovingObject movingObject, int nextY,
	// boolean goingLeft) {
	// }

	/**
	 * 0 top 1 left 2 down 3 right
	 * 
	 * @param movingObject
	 * @param staticObjects2
	 */

	private static void calcLeftRight(MyMovingObject movingObject, int nextX, int nextY, boolean goingUp,
			boolean goingLeft, GameObject[] staticObjects) {
		// TODO testing

//		int amountTest;
//		int divX;
//		int divY;
		int positiveX = Math.abs(nextX);
		int positiveY = Math.abs(nextY);
		boolean yIsBigger;
		int biggest;
		int smallest;

		if (positiveX < positiveY) {
//			amountTest = (int) positiveY;
//			divY = 1;
			byte numb = 1;
			if (nextX < 0)
				numb = -1;
//			divX = (positiveX / positiveY) * numb;
			biggest = positiveY;
			smallest = positiveX;
			yIsBigger = true;
		} else {
//			amountTest = (int) positiveX;
//			divX = 1;
			byte numb = 1;
			if (nextY < 0)
				numb = -1;
			biggest = positiveX;
			smallest = positiveY;
//			divY = (positiveY / positiveX) * numb;
			yIsBigger = false;
		}

		for (int big = 0; big <= biggest; big++) 
			for (int small = 0; small <= smallest; small++) {
			// if (movingObject.touchingRight || movingObject.touchingLeft)
			// break;
			
			Rectangle tangle;
			
			if (yIsBigger)
				tangle = movingObject.nextTangle(small, big);
			else
				tangle = movingObject.nextTangle(big, small);
			if (!movingObject.touchingUp && !movingObject.touchingDown) {
				if (goingUp)
					for (int ii = 0; ii < staticObjects.length; ii++) {
						if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 0)) {

							if (yIsBigger)
								movingObject.touchingY(big, staticObjects[ii].type);
							else
								movingObject.touchingY(small, staticObjects[ii].type);
							movingObject.touchingUp = true;

							break;
						}
					}
				else
					for (int ii = 0; ii < staticObjects.length; ii++) {
						if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 2)) {

							movingObject.touchingDown = true;
							if (yIsBigger)
								movingObject.touchingY(big, staticObjects[ii].type);
							else
								movingObject.touchingY(small, staticObjects[ii].type);

							break;
						}
					}
			}
			if (goingLeft)
				for (int ii = 0; ii < staticObjects.length; ii++) {
					if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 3)) {
						if (yIsBigger)
							movingObject.touchingX(small, staticObjects[ii].type);
						else
							movingObject.touchingX(big, staticObjects[ii].type);
					
						movingObject.touchingLeft = true;

						break;
					}
				}
			else // goingRight
				for (int ii = 0; ii < staticObjects.length; ii++) {
					if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 1)) {

						movingObject.touchingRight = true;
						if (yIsBigger)
							movingObject.touchingX(small, staticObjects[ii].type);
						else
							movingObject.touchingX(big, staticObjects[ii].type);

						break;
					}
				}
			if (movingObject.touchingRight || movingObject.touchingLeft && movingObject.touchingDown || movingObject.touchingUp)
				//System.exit(0);
				break;
		}
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
		if (nextY < 0) {
			amountTest = (int) -nextY;
			pos = (byte) -pos;
		} else
			amountTest = (int) nextY;
		int awnser = -1;
		out: for (int i = 0; i <= amountTest; i++) {
			for (int ii = 0; ii < staticObjects.length; ii++)
				if (wallTester(movingObject.nextTangleOnlyY(i * pos), staticObjects[ii].myRectangle, (byte) choice)) {
					awnser = ii;
					if (nextY < 0)
						nextY = -i;
					else
						nextY = i;
					break out;
				}
		}
		if (awnser != -1) {
			if (goingUp) {
				movingObject.touchingUp = true;
			} else {
				movingObject.touchingDown = true;
			}
			movingObject.touchingY(nextY, staticObjects[awnser].type);
		}

	}

}
