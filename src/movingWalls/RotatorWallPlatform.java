package movingWalls;

import java.awt.Rectangle;

import interfacesAndAbstract.ThingsInTheWorld;

public class RotatorWallPlatform extends BaseMovingWall{

	private final int RADIUS;
	private double amount;
	private double add;
	private boolean goingLeftUp;
	private final boolean REVERSED;

	public RotatorWallPlatform(Rectangle bounds, ThingsInTheWorld e, double flyingSpeed,
			boolean sticky, boolean reversed, int radius, int amount) {
		super(bounds, e, 0, 0, 0, sticky);
//		int xDif = (rotationPoint.x - bounds.x)*INCREASE;
//		int yDif = (rotationPoint.y - bounds.y)*INCREASE;
//		System.out.println(xDif + "  " + yDif);
//		radius = (int) Math.sqrt((xDif*xDif)+(yDif*yDif))/100;
		add = 0.01 * flyingSpeed;
		RADIUS =  (int) (radius*flyingSpeed);
//		this.flyingSpeed = 100/this.flyingSpeed;
		int calco = 4;
		if (amount > calco) {
			amount = amount%calco;
			goingLeftUp = !goingLeftUp;
		}
		double plus = Math.PI/(calco-1);
		this.amount = plus*amount;
		this.REVERSED = reversed;
	}

	@Override
	public void preUpdate() {
		amount += add;
		if (amount >= Math.PI){
			amount = 0;
			goingLeftUp = !goingLeftUp;
		}
		calcNextX();
		calcNextY();
	}
	@Override
	protected void calcNextX() {
		//TODO -- http://jacksondunstan.com/articles/1190
		if (REVERSED)
			vector[0] = sinus();
		else
			vector[0] = coSinus();
		
		if (goingLeftUp){
			vector[0] = - vector[0];
		}
		if (vector[0] > 0) {
			goingRight = true;
			goingLeft = false;
		} else if (vector[0] < 0) {
			goingLeft = true;
			goingRight = false;
		}
		
	}

	@Override
	protected void calcNextY() {
		if (REVERSED)
			vector[1] = coSinus();
		else
			vector[1] = sinus();

		if (goingLeftUp){
			vector[1] = - vector[1];
		}
		if (vector[1] < 0) {
			goingUp = true;
			goingDown = false;
		} else if (vector[1] > 0) {
			goingDown = true;
			goingUp = false;
		}
	}
	
	private int sinus(){
		return ((int) (RADIUS * Math.sin(amount)));
	}
	
	private int coSinus(){
		return ((int) (RADIUS * Math.cos(amount)));
	}

}
