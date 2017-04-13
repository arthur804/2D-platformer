package forReal;

import java.awt.Point;

import staticClasses.Formulas;

public class TCamera {// if camera hasnt moved dont recalc seen

	
	private final int width,height;
	public int x = 0, y = 0;
	
	private final int distanceFromWall;
	private final int distanceFromFloors; 
	private final int cameraMovementSpeed;
	private int whait = 0;
	
	//TODO update the Y
	
	public TCamera(int width, int height){
		this.width = width;
		this.height = height;
		distanceFromWall = 100;
		distanceFromFloors = 100;
		cameraMovementSpeed = Formulas.STANDARGD_CAMERASPEED;
	
	}
	
	public void reCamera(SMyPlayer player){
//		if (x == player.myRectangle.x && y == player.myRectangle.y)
//			return false;//als je x en y niet veranderen false ander true mario camera TODO
//		if (player.goingUp && player.walljumped)
		int cameraMovementSpeed = -player.nextX();//this.cameraMovementSpeed;
		if (cameraMovementSpeed == 0 && player.touchingDown){
			if (whait > 50)
				if (player.lookingRight)
					cameraMovementSpeed = -this.cameraMovementSpeed;
				else 
					cameraMovementSpeed = this.cameraMovementSpeed;
			else
				whait++;
		} else
			whait = 0;
		
		if ((player.goingLeft || player.goingRight) && !player.goingUp)
			cameraMovementSpeed = cameraMovementSpeed*2;
		if(player.touchingLeft || player.touchingRight){
			if (player.touchingRight){
				//TODO
				camReCalculateX(true, !player.wallJumped, player.myRectangle.x,
						distanceFromWall - width/2, 
						-(-width/2 + player.myRectangle.x - distanceFromWall), cameraMovementSpeed);
			} else {
				camReCalculateX(true, !player.wallJumped, player.myRectangle.x,  
						width/2 - distanceFromWall, 
						-(-width/2 + player.myRectangle.x + distanceFromWall), cameraMovementSpeed);				
			}
		}else if (player.lookingRight){
			camReCalculateX(false, !player.wallJumped, player.myRectangle.x, 
					distanceFromWall, 
					-(player.myRectangle.x - distanceFromWall), cameraMovementSpeed);			
		} else {
			camReCalculateX(true, !player.wallJumped, player.myRectangle.x,  
					width - distanceFromWall, 
					-(-width + player.myRectangle.x + distanceFromWall), cameraMovementSpeed);
		

		}
//		if (player.goingUp){
//			if (player.myRectangle.y <= y + distanceFromFloors)
//				yDir = -cameraMovementSpeed;
//		} else 
//			if (player.myRectangle.y <= height + y - distanceFromFloors)
//				yDir = cameraMovementSpeed;
	}
	
	private void camReCalculateX(boolean bigger, boolean moving, int formula1, int formula2, int awnser, int camMove){
		
		if (bigger){
			if (formula1 < formula2 - x && moving)
				x += camMove;
			if (formula1 >= formula2 - x)
				x = awnser;
			if (formula1 >= formula2 - x)
				x = awnser;
		} else {
			if (formula1 > formula2 - x && moving)
				x += camMove;
			if (formula1 <= formula2 - x)
				x = awnser;
			if (formula1 <= formula2 - x)
				x = awnser;	
		}
	}
}
