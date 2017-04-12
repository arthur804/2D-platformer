package ForReal;

import java.awt.Point;

import staticClasses.Formulas;

@SuppressWarnings("unused")
public class Camera {// if camera hasnt moved dont recalc seen

	
	private final int width,height;
	public int x = 0, y = 0;
	
	private final int distanceFromWall;
	private final int distanceFromFloors; 
	private final int cameraMovementSpeed;
	
	//TODO use this in calculating if camera needs to move
	private final int deadZone;
	private boolean playerLookingRightLastCheck;
	private int playerPrevXLocation;
	private static final int deadZoneSize = 100;
	
	public Camera(int width, int height){
		this.width = width;
		this.height = height;
		distanceFromWall = 100;
		distanceFromFloors = 100;
		cameraMovementSpeed = Formulas.STANDARGD_CAMERASPEED;
		
		//TODO think about this make falues static maybe?
		deadZone = this.width/2-deadZoneSize/2;
	}
	
	public void reCamera(MyPlayer player){
//		if (x == player.myRectangle.x && y == player.myRectangle.y)
//			return false;//als je x en y niet veranderen false ander true mario camera TODO
//		if (player.goingUp && player.walljumped)
		int cameraMovementSpeed = this.cameraMovementSpeed;
		if ((player.goingLeft || player.goingRight) && !player.goingUp)
			cameraMovementSpeed = cameraMovementSpeed*2;
		if(player.touchingLeft || player.touchingRight){
			if (player.touchingRight){
				//TODO
				camReCalculateX(true, !player.wallJumped, player.myRectangle.x,
						distanceFromWall - width/2, 
						-(-width/2 + player.myRectangle.x - distanceFromWall), cameraMovementSpeed);

//				if (player.myRectangle.x > -(width/2 + x - distanceFromWall)){
//					x -= cameraMovementSpeed;
//				}
//				if (player.myRectangle.x >= -(width/2 + x - distanceFromWall))
//					x = -(-width/2 + player.myRectangle.x + distanceFromWall);
				
			} else {
				//TODO
				camReCalculateX(false, !player.wallJumped, player.myRectangle.x,  
						width/2 - distanceFromWall, 
						-(-width/2 + player.myRectangle.x + distanceFromWall), -cameraMovementSpeed);
				
//				if (player.myRectangle.x < -(-width/2 + x + distanceFromWall))
//					x += cameraMovementSpeed;
//				if (player.myRectangle.x > -(-width/2 + x + distanceFromWall))
//					x = -(-width/2 + player.myRectangle.x + distanceFromWall);
			}
		}else if (player.lookingRight){
			camReCalculateX(false, !player.wallJumped, player.myRectangle.x, distanceFromWall, -(player.myRectangle.x - distanceFromWall), -cameraMovementSpeed);

			
		} else {
			camReCalculateX(true, !player.wallJumped, player.myRectangle.x,  width - distanceFromWall, -(-width + player.myRectangle.x + distanceFromWall), cameraMovementSpeed);
		

		}
//		if (player.goingUp){
//			if (player.myRectangle.y <= y + distanceFromFloors)
//				yDir = -cameraMovementSpeed;
//		} else 
//			if (player.myRectangle.y <= height + y - distanceFromFloors)
//				yDir = cameraMovementSpeed;
	}
	
	private void camReCalculateX(boolean bigger, boolean moving, int formula1, int formula2, int awnser, int camMove){
//		if (player.myRectangle.x > -(x - distanceFromWall)){
//		x -= cameraMovementSpeed;
//	}
//	if (player.myRectangle.x <= -(x - distanceFromWall))
//		x = -(player.myRectangle.x - distanceFromWall);
		
//		if (player.myRectangle.x < -(-width + x + distanceFromWall))
//			x += cameraMovementSpeed;
//		if (player.myRectangle.x > -(-width + x + distanceFromWall))
//			x = -(-width + player.myRectangle.x + distanceFromWall);
		
		if (bigger){
			if (formula1 < formula2 - x && moving)
				x += camMove;
			if (formula1 >= formula2 - x)
				x = awnser;			
		} else {
			if (formula1 > formula2 - x && moving)
				x += camMove;
			if (formula1 <= formula2 - x)
				x = awnser;
		}
	}
}
