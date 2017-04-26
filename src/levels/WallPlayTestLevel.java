package levels;

import java.awt.Point;
import java.awt.Rectangle;

import MovingWalls.JiggelingWall;
import MovingWalls.NormalMovingWall;
import forReal.LWall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;

public class WallPlayTestLevel extends BasicLevel {

	public WallPlayTestLevel() {

		super(fillMeUp(), new MyMovingObject[] {
				new NormalMovingWall(new Rectangle(200, 510, 40, 50), ThingsInTheWorld.WALL,
						new Point[] { new Point(200, 510), new Point(150, 510) }, 10, 10, 100, true),
				
					new NormalMovingWall(new Rectangle(200, 450, 40, 70), ThingsInTheWorld.WALL,
						new Point[] { new Point(200, 450), new Point(250, 450) }, 10, 10, 100, true), 
					
//					new NormalMovingWall(new Rectangle(250, 450, 40, 50), ThingsInTheWorld.WALL,
//							new Point[] { new Point(250, 450), new Point(300, 550) }, 10, 10, 100, true), 
					
					new NormalMovingWall(new Rectangle(300, 550, 140, 50), ThingsInTheWorld.WALL,
						new Point[] { new Point(300, 500), new Point(310, 600) }, 10, 10, 100, false),
					
					new JiggelingWall(new Rectangle(10, 500, 100, 30), ThingsInTheWorld.WALL, 3, 3, 200, 200 , 150, false, 5)
						});
	}
	
	private static GameObject[] fillMeUp(){
		int amount = 10000;
		GameObject[] fa = new GameObject[amount];
		fa[0] = new LWall(new Rectangle(3, 587, 4240, 72));
		
		for (int i = 1; i < amount; i++){
			fa[i] = new LWall(new Rectangle(103 + 30, 587-10, 20, 40));
			fa[i].canBeStepedOn = false;
		}
//		, new LWall(new Rectangle(3, 300, 4240, 70))
		fa[14] = new LWall(new Rectangle(500, 20, 20, 40));
		fa = new GameObject[]{new LWall(new Rectangle(3, 587, 4240, 72))};
		return fa;
		
	}

}
