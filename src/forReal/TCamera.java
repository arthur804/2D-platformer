package forReal;

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
		int playerx = player.myRectangle.x;
		boolean cameraFocusGoesRight = player.lookingRight;
		
		if (cameraFocusGoesRight)
			x += player.nextX()/4;
		else
			x -= player.nextX()/4;
		
		//out of bounds player
		if (playerx < distanceFromWall + x)
			x = playerx - distanceFromWall;
		
		else if (playerx > -distanceFromWall + width + x)
			x = playerx + distanceFromWall - width;
	}
	
}
