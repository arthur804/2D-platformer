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
	
	public Camera(int width, int height){
		this.width = width;
		this.height = height;
		distanceFromWall = 50;
		distanceFromFloors = 50;
		cameraMovementSpeed = Formulas.STANDARGD_WALLFALLINGSPEED;
	}
	
	public void reCamera(MyPlayer player){
//		if (x == player.myRectangle.x && y == player.myRectangle.y)
//			return false;//als je x en y niet veranderen false ander true mario camera TODO
		int xDir = 0;
		int yDir = 0;
		System.out.println(-width  + x + distanceFromWall + " :-: " + player.myRectangle.x);
		if (player.lookingRight){
			if (player.myRectangle.x > -(x - distanceFromWall))
				xDir = -1;
		} else 
			if (player.myRectangle.x < -(-width  + x + distanceFromWall))
				xDir = 1;
//		if (player.goingUp){
//			if (player.myRectangle.y <= y + distanceFromFloors)
//				yDir = -cameraMovementSpeed;
//		} else 
//			if (player.myRectangle.y <= height + y - distanceFromFloors)
//				yDir = cameraMovementSpeed;
		
		x += xDir;
		y += yDir;
	}
	
	
}
