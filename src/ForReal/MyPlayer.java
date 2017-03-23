package ForReal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import interfacesAndAbstract.MyMovingObject;
import staticClasses.Formulas;

public class MyPlayer extends MyMovingObject {

	public boolean left, right, up, down;
	private int slowdownSpeed;
	private int speed;
	

	private int maxJumpVector = 5;
	private int grafity = 0;
	private int walky = 0;
	
	public MyPlayer(Point rec) {
		super(new Rectangle(rec, new Dimension(16,16)), 'p');
		this.slowdownSpeed = 1;
		this.maxSpeed = 3;
	}

	private int grafityCalc() {
		grafity++;
		int vecPlus = Formulas.grafity(grafity);
		if (vecPlus != 0)
			grafity = 0;
		return vecPlus;
	}
	
	private int walkCalc() {
		walky++;
		int vecPlus = Formulas.walkingSpeed(walky);
		if (vecPlus != 0)
			walky = 0;
		return vecPlus;
	}
	
	public int nextX() {
		if ((touchingLeft && vector[0] < 0) || (touchingRight && vector[0] > 0))
			return 0;
		return vector[0];
	}

	/** plz dont use */
	public void calcNextX() {
		speed = walkCalc();
		if (vector[0] > 0) {
			if (left && !right && !touchingLeft) {
				vector[0] -= speed;
			} else if (!left && right && !touchingRight) {
				vector[0] += speed;
			} else if (!touchingRight){
				vector[0] -= slowdownSpeed;
			}
		} else if (vector[0] < 0) {
			if (left && !right && !touchingLeft) {
				vector[0] -= speed;
			} else if (!left && right && !touchingRight) {
				vector[0] += speed;
			} else if (!touchingLeft){
				vector[0] += slowdownSpeed;
			}
			
		} else {
			if (!left && right && !touchingRight)
				vector[0] += speed;
			else if (left && !right && !touchingLeft)
				vector[0] -= speed;
			
		}
		if (vector[0] < -this.maxSpeed)
			vector[0] = -this.maxSpeed;

		if (vector[0] > this.maxSpeed)
			vector[0] = this.maxSpeed;
	}

	public int nextY() {
		if ((touchingUp && vector[1] < 0) || (touchingDown && vector[1] > 1))
			return 0;
		return vector[1];
	}

	/** plz dont use */
	public void calcNextY() {
		
		if (!touchingDown) {
			vector[1] += grafityCalc();
			// going Down
		} else if (up&&!touchingUp) {
			jump();
			// going Up
		}

		if (vector[1] < -maxJumpVector)
			vector[1] = -maxJumpVector;

		else if (vector[1] > Formulas.maxPlayerFall)
			vector[1] = Formulas.maxPlayerFall;
	}

	public void jump() {
		vector[1] = -maxJumpVector;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.yellow);
		super.baseDraw(g);
	}

	public int[] getCameraLocation(int height, int length) {
		// TODO
		return new int[] { 0, 0 };
	}

	public boolean canJump() {
		// TODO
		return false;
	}

	public void controls(boolean[] keysPressed) {
		left = keysPressed[0];
		up = keysPressed[1];
		right = keysPressed[3];
		down = keysPressed[2];
	}

}
