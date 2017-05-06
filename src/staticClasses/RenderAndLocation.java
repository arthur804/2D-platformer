package staticClasses;

import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;

import eccentialItems.SMyPlayer;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class RenderAndLocation {

	private RenderAndLocation() {
	}

	public static boolean isObjectContained(Rectangle shape, Rectangle container) {
		Point p = shape.getLocation();
		if (container.contains(p))
			return true;
		p.x += shape.width;
		if (container.contains(p))
			return true;
		p.y += shape.height;
		if (container.contains(p))
			return true;
		p.x -= shape.width;
		if (container.contains(p))
			return true;
		return false;
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
			if (movingObject.goingLeft) {
				calcLeftRightUpDown(movingObject, nextX, nextY, true, true, staticObjects);
			}
			/* Up and right */
			else if (movingObject.goingRight) {
				calcLeftRightUpDown(movingObject, nextX, nextY, true, false, staticObjects);
			}
			/* just Up */
			else {
				calcUpDown(staticObjects, movingObject, nextY, true);
			}
		}
		// ---------------------------------DOWN
		else if (movingObject.goingDown) {

			/* Down and left */
			if (movingObject.goingLeft) {
				calcLeftRightUpDown(movingObject, nextX, nextY, false, true, staticObjects);
			}
			/* down and right */
			else if (movingObject.goingRight) {
				calcLeftRightUpDown(movingObject, nextX, nextY, false, false, staticObjects);
			}
			/* just down */
			else {
				calcUpDown(staticObjects, movingObject, nextY, false);
			}
		} else {
			if (movingObject.goingLeft) {
				calcLeftRightOnly(movingObject, staticObjects, nextX, true);
			}
			/* down and right */
			else if (movingObject.goingRight) {
				calcLeftRightOnly(movingObject, staticObjects, nextX, false);
			}
		}
	}

//	private static int bitchboy = 0;

//	bitchboy++;
//	System.out.println(bitchboy);
	
	public static void movingWallCalculation(MyMovingObject movingWall, SMyPlayer player,
			GameObject[] staticObjects) {
		int nextX = movingWall.nextX();
		int nextY = movingWall.nextY();

		int positiveX = Math.abs(nextX);
		int positiveY = Math.abs(nextY);
		boolean yIsBigger;
		int biggest;
		int smallestInSteps;
		boolean touchingThisOne = false;
		
		boolean playerDown = player.goingDown;

		if (positiveX < positiveY) {
			biggest = positiveY;
			smallestInSteps = positiveX;
			yIsBigger = true;
		} else {
			biggest = positiveX;
			smallestInSteps = positiveY;
			yIsBigger = false;
		}

		Rectangle tangle;
		for (int big = 0; big <= biggest; big++)
			for (int small = 0; small <= smallestInSteps; small++) {

				if (yIsBigger)
					tangle = nextTangle(movingWall, small, big, movingWall.goingUp, movingWall.goingLeft);
				else
					tangle = nextTangle(movingWall, big, small, movingWall.goingUp, movingWall.goingLeft);

				if (movingWall.goingUp){
					if (wallTester(tangle, player.myRectangle, (byte) 0)) {
						movingWall.touchingY(0, player);
						touchingThisOne = true;

					}
				} else if (movingWall.goingDown){
					if (wallTester(tangle, player.myRectangle, (byte) 2)) {
						movingWall.touchingY(0, player);
						touchingThisOne = true;

					}
				}
				if (movingWall.goingLeft) {
					if (wallTester(tangle, player.myRectangle, (byte) 3)) {
						movingWall.touchingX(0, player);
						touchingThisOne = true;

					}

				} else if (movingWall.goingRight) {
					if (wallTester(tangle, player.myRectangle, (byte) 1)) {
						movingWall.touchingX(0, player);
						touchingThisOne = true;
					}
				}

			}
		if (touchingThisOne)
			isPlayerDead(player, movingWall.goingUp || movingWall.goingDown);
		movingWall.update(); // has to be here or have to make a new one witch
								// has this location and use that one

		// Do the same thing but with player

		nextX = player.nextX();
		nextY = player.nextY();

		positiveX = Math.abs(nextX);
		positiveY = Math.abs(nextY);

		if (positiveX < positiveY) {
			biggest = positiveY;
			smallestInSteps = positiveX;
			yIsBigger = true;
		} else {
			biggest = positiveX;
			smallestInSteps = positiveY;
			yIsBigger = false;
		}

		boolean yMove = (movingWall.goingUp || movingWall.goingDown);
		if (!touchingThisOne){
			if (player.goingDown && yMove) {
				tangle = nextTangle(player, 0, 0, player.goingUp, player.goingLeft);
				if (wallTester(tangle, movingWall.myRectangle, (byte) 0)) {
					movingWall.touchingY(0, player);
				}
			}
	
			for (int big = 0; big <= biggest; big++)
				for (int small = 0; small <= smallestInSteps; small++) {
	
					if (yIsBigger)
						tangle = nextTangle(player, small, big, player.goingUp, player.goingLeft);
					else
						tangle = nextTangle(player, big, small, player.goingUp, player.goingLeft);
	
					if (player.goingUp) {
						if (wallTester(tangle, movingWall.myRectangle, (byte) 0)) {
							movingWall.touchingY(0, player);
							touchingThisOne = true;
						}
	
					} else if (player.goingDown) {
						if (wallTester(tangle, movingWall.myRectangle, (byte) 2)) {
							movingWall.touchingY(0, player);
							touchingThisOne = true;
						}
					}
					if (player.goingLeft) {
						if (wallTester(tangle, movingWall.myRectangle, (byte) 3)) {
							movingWall.touchingX(0, player);
							touchingThisOne = true;
						}
	
					} else if (player.goingRight) {
						if (wallTester(tangle, movingWall.myRectangle, (byte) 1)) {
							movingWall.touchingX(0, player);
							touchingThisOne = true;
						}
					}
	
				}
		}
		if (touchingThisOne){
			if (playerDown != player.goingDown && player.goingDown) {
				if (yMove){
					walltest(player, staticObjects);
					if (player.touchingDown && player.touchingUp)
						player.dead = true;
					return;
				} else {
					player.touchingY(0, player);//TODO maybe?
				}
					
			} else if ((player.goingDown && player.touchingDown) && movingWall.goingDown){
				player.touchingDown = false;
				walltest(player, staticObjects);
				if (player.touchingDown){
					player.touchingNotMovingNow();
				} else
					player.touchingDown = true;
				//TODO
			}
			isPlayerDead(player, yMove);
		}

	}

	private static void isPlayerDead(SMyPlayer player, boolean goingUpOrDown) {
		if (player.touchingDown && player.touchingUp && goingUpOrDown) {
			player.dead = true;
		} else if (player.touchingLeft && player.touchingRight) {
			player.dead = true;
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
			biggest = positiveY;
			smallestInSteps = positiveX;
			yIsBigger = true;
		} else {
			biggest = positiveX;
			smallestInSteps = positiveY;
			yIsBigger = false;
		}

		for (int big = 0; big <= biggest; big++) {
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

					if (goingLeft) {
						for (int ii = 0; ii < staticObjects.length; ii++) {
							if (!staticObjects[ii].seen)
								continue;

							if (wallTester(tangle, staticObjects[ii].myRectangle, (byte) 3)) {
								movingObject.touchingLeft = true;
								if (yIsBigger) {
									movingObject.touchingX(-small, staticObjects[ii]);
								} else {
									movingObject.touchingX(-big, staticObjects[ii]);
								}
								break;
							}
						}
					} else if (!goingLeft) {
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
			}
			if (yIsBigger) {
				if (movingObject.touchingDown || movingObject.touchingUp)
					break;

			} else if (movingObject.touchingLeft || movingObject.touchingRight)
				break;

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
			for (int ii = 0; ii < staticObjects.length; ii++) {
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
			for (int ii = 0; ii < staticObjects.length; ii++) {
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
