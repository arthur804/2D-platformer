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
	
	//TODO this is temporarly
	private Rectangle eyeTangle = new Rectangle(0,0,5,3);

	private final int MAXJUMPVECTOR = 600;//TODO 5
//	private int grafity = 0;
	private int walky = 0;
	
	public MyPlayer(Point rec) {
		super('p',new Rectangle(rec, new Dimension(16,16)));
		this.slowdownSpeed = 1;
		this.maxSpeed = 600;
		
	}
	
	public int nextX() {
		if (vector[0] >= 0)
			return calc(0);
		else
			return calc(0) - 1; // omdat 1300 - 30 = 12 TODO Fix
	}
	
	public int nextY() {
		return calc(1);
	}
	private int calc(int i){
		if (absoluteLocation[i] < 0)
			return (-absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
		else
			return (absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
	}
	/** plz dont use */
	public void calcNextX() {
		int speed = 30;
		int slowSpeed = 10;
				
//		if (touchingLeft)
//			return;
		if (vector[0] > 0) {
			if (left){ // going left
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

	//TODO remove this its teporarly for the eye
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
//		super.baseDraw(g);
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
