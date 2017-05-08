package interfacesAndAbstract;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class PushAble extends MyMovingObject{

	public PushAble(Rectangle bounds, ThingsInTheWorld e) {
		super(bounds, e);
	}
	
	 public PushAble(Rectangle bounds, ThingsInTheWorld e, BufferedImage spriteSheet, int sizeSpriteX, int sizeSpriteY, int xPixelsBet,int yPixelsBet){
		super(bounds, e, spriteSheet, sizeSpriteX, sizeSpriteY, xPixelsBet, yPixelsBet);
	}	


	public void pushedX(int newVector, boolean movingwallIsGoingLeft, boolean playerIsOnLeft) {
		if (playerIsOnLeft){
			touchingRight = true;
				
			if (movingwallIsGoingLeft){
				if (vector[0] > newVector)
					vector[0] = newVector;
				goingRight = true;
			}
			else{
				vector[0] = 0;				
			}
			
		} else {
			touchingLeft = true;
			if (!movingwallIsGoingLeft){
				if (vector[0] < newVector)
					vector[0] = newVector;
				goingLeft = true;
			} else{
				vector[0] = 0;
			}
			
		}
	}
	

	public void pushedY(int newVector, boolean movingWallIsGoingUp,
			boolean playerIsAboveWall, boolean isSticky, int standardVector, int newLocation) {
		absoluteLocation[1] = newLocation;
		if (playerIsAboveWall){
			touchingDown = true;
			goingDown = true;
			if (movingWallIsGoingUp){
				if (vector[1] + INCREASE > newVector){
					vector[1] = newVector;
				}
				if (isSticky)
					this.standardVector = standardVector;
			}else {
				vector[1] = newVector;
				if (isSticky)
					this.standardVector = standardVector;
				absoluteLocation[1] -= newVector;
			}
		} else {
			goingDown = true;
			goingUp = false;
			if (movingWallIsGoingUp){
				vector[1] = 0; // if you want to hang on to something that moves you need to adapt this
			} else {
				touchingUp = true;
				if (vector[1] < newVector)
					vector[1] = newVector;
			}
		}
		
	}
	
	public void jiggleY(int newVector, boolean goingUp, int newLocation) {
		// TODO Auto-generated method stub
		absoluteLocation[1] = newLocation;
		if (goingUp){
			goingDown = true;
			this.goingUp = false;
			touchingDown = true;
			if (vector[1] > newVector)
				vector[1] = newVector; 
		} else {
			touchingUp = true;
			goingDown = true;
			this.goingUp = false;
			if (vector[1] < newVector)
				vector[1] = newVector + INCREASE; 
			newLocation += vector[1];
		}
	}
}
