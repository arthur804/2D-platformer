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
	
	private boolean lookingRight = true;
	
	private boolean wallJumpalbe = false;
	
	private int slowCalcWalk = 0;
	
	private final int FALINGSPEED = 20;

	private final int MAXJUMPVECTOR = 600;//TODO 5
//	private int grafity = 0;
	private int walky = 0;
	
	public MyPlayer(Point rec) {
		super('p',new Rectangle(rec, new Dimension(16,16)));
		this.slowdownSpeed = 1;
		this.maxSpeed = 600;
		
	}

//	private int grafityCalc() {
//		grafity++;
//		int vecPlus = Formulas.grafity(grafity);
//		if (vecPlus != 0)
//			grafity = 0;
//		return vecPlus;
//	}
	
//	private int walkCalc() {
//		//TODO
//		walky += 2;
//		int vecPlus = Formulas.walkingSpeed(walky);
//		if (vecPlus != 0)
//			walky = 0;
//		return vecPlus;
//	}
//	
//	private int slowWalkCalc(){
//		//TODO
//		slowCalcWalk++;
//		int vecPlus = Formulas.walkingSpeed(slowCalcWalk);
//		if (vecPlus != 0)
//			slowCalcWalk = 0;
//		return vecPlus;
//	}
	
	public int nextX() {
		if ((touchingLeft && vector[0] < 0) || (touchingRight && vector[0] > 0))
			return 0;
		return vector[0]/INCRESE;
	}

	/** plz dont use */
	public void calcNextX() {
		int speed = 30;
		int slowSpeed = 10;
				
		if (vector[0] > 0) {
			if (left){
				vector[0] -= speed;
				lookingRight = false;
			}else if (right)
				vector[0] += speed;
			else
				vector[0] -= slowSpeed;

			
		} else if (vector[0] < 0) {
			if (left)
				vector[0] -= speed;
			else if (right){
				vector[0] += speed;
				lookingRight = true;
			}else
				vector[0] += slowSpeed;
			
		} else {
			if (!left && right){
				vector[0] += speed;
				lookingRight = true;
			}else if (left && !right){
				vector[0] -= speed;
				lookingRight = false;
			}
		}
		if (vector[0] < -this.maxSpeed)
			vector[0] = -this.maxSpeed;

		if (vector[0] > this.maxSpeed)
			vector[0] = this.maxSpeed;
		System.out.println(vector[0]);
	}

	public int nextY() {
		if ((touchingUp && vector[1] < 0) || (touchingDown && vector[1] > 1))
			return 0;
		return vector[1]/INCRESE;
	}
	
	public void specialAction(char c){
		//TODO als ik iets dodelijks aan raak dan moet dat hier gevonden worden
	}

	/** plz dont use */
	public void calcNextY() {
		if (!touchingDown) {
			vector[1] += FALINGSPEED;
			// going Down
		} else if (up) {
			jump();
			// going Up
		}

		if (vector[1] < -MAXJUMPVECTOR)
			vector[1] = -MAXJUMPVECTOR;

		else if (vector[1] > MAXJUMPVECTOR)
			vector[1] = MAXJUMPVECTOR;

	}

	public void jump() {
		vector[1] = -MAXJUMPVECTOR;
//		grafity = 0;// omdat anders de sprong hoogte verschilt
		wallJumpalbe = false;
	}
	
	private void wallJump(byte n){
		if (!wallJumpalbe)
			return;
		vector[1] = -(MAXJUMPVECTOR/2);
		vector[0] = n*maxSpeed;
		wallJumpalbe = false;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.yellow);
		super.baseDraw(g);
	}

	public int[] getCameraLocation(int height, int length) {
		// TODO
		return new int[] { 0, 0 };
	}

	public void controls(boolean[] keysPressed) {
		left = keysPressed[0];
		up = keysPressed[1];
		right = keysPressed[3];
		down = keysPressed[2];
	}
	
//	@Override
//	public void reTrue(){
//		if (!(touchingLeft && left))
//			touchingLeft = false;
//		if (!(touchingRight && right))
//			touchingRight = false;
//		touchingUp = touchingDown = false;
//	}

}
