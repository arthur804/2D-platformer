package ForReal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;
import staticClasses.Formulas;

public class MyPlayer extends MyMovingObject {

	public boolean left, right, up, down;
	
	private boolean lookingRight = true;
	
	private boolean wallJumped = false;
	private boolean jumped = false;
	
	//TODO this is temporarly
	private Rectangle eyeTangle = new Rectangle(0,0,5,3);

	
	public MyPlayer(Point rec) {
		super(new Rectangle(rec, new Dimension(16,16)),ThingsInTheWorld.PLAYER);
		this.maxSpeed = 600;
	}
	
	public int nextX() {
		int nextX = calc(0);
		if (vector[0] > 0){
			goingRight = true;
			nextX += 1;
		} else if (vector[0] < 0){
			goingLeft = true;
			nextX -= 1;
		}
			return nextX; // omdat 1300 - 30 = 12 TODO Fix
	}
	
	public int nextY() {
		int nextY = calc(1);
		if (vector[1] < 0)
			goingUp = true;
		return nextY;
	}
	private int calc(int i){
		if (absoluteLocation[i] < 0)
			return (-absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
		else
			return (absoluteLocation[i]%INCREASE+vector[i])/INCREASE;
	}

	protected void calcNextX() {
		//If im standing on ice slowdownspeed will change  TODO
		int speed;
		int slowSpeed;
		if (touching[1] != null){
			speed = touching[1].getWalkingSpeed();
			slowSpeed = touching[1].getSlowDownSpeed(); 
		} else{
			speed = Formulas.STANDARGD_SPEEDFLYING;
			slowSpeed = Formulas.STANDARGD_SLOWDOWNSPEEDFLYING; 
		}
		
			
		if (wallJumped && up && !touchingDown)
			return;
		if (!up)
		{
			wallJumped = false;
			jumped = false;
		}
		
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
		if (vector[0] < -Formulas.STANDARD_MAXWALKINGSPEED)
			vector[0] = -Formulas.STANDARD_MAXWALKINGSPEED;

		if (vector[0] > Formulas.STANDARD_MAXWALKINGSPEED)
			vector[0] = Formulas.STANDARD_MAXWALKINGSPEED;
		
		
	}

	protected void calcNextY() {
		if (!touchingDown) {
			if (!goingUp && (touchingLeft || touchingRight)){//Sliding walls
				//wallJump
				if (up){
					wallJump();
				}
				//WallSliding
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
	}

	public void jump() {
		if (touching[1] != null)
		vector[1] = -touching[1].getJump();
		jumped = true;
	}
	
	private void wallJump(){
		if ((jumped || wallJumped) && up)
			return;
		
		if (touchingLeft){
			vector[0] = touching[0].getWallJumpDistance();
		} else if (touchingRight){
			vector[0] = -touching[0].getWallJumpDistance();
		} else
			return;
		vector[1] = -touching[0].getWallJumpHeight();
		wallJumped = true;
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

}
