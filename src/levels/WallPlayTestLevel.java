package levels;

import java.awt.Point;
import java.awt.Rectangle;

import forReal.LWall;
import forReal.SMovingWall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;

public class WallPlayTestLevel extends BasicLevel {

	public WallPlayTestLevel() {

		super(fillMeUp(), new MyMovingObject[] {
//				new SMovingWall(new Rectangle(100, 500, 40, 50), ThingsInTheWorld.WALL,
//						new Point[] { new Point(100, 500), new Point(150, 500) }, 10, 10, 100, false),
//				
//					new SMovingWall(new Rectangle(200, 500, 40, 70), ThingsInTheWorld.WALL,
//						new Point[] { new Point(200, 500), new Point(200, 450) }, 10, 10, 100, false), 
//					
					new SMovingWall(new Rectangle(250, 500, 40, 50), ThingsInTheWorld.WALL,
							new Point[] { new Point(250, 500), new Point(300, 550) }, 10, 10, 100, false), 
					
					new SMovingWall(new Rectangle(300, 550, 140, 50), ThingsInTheWorld.WALL,
						new Point[] { new Point(300, 500), new Point(310, 510) }, 10, 10, 100, false)

						});
	}
	
	private static GameObject[] fillMeUp(){
		
		GameObject[] fa = new GameObject[15];
		fa[0] = new LWall(new Rectangle(3, 587, 4240, 72));
		
		for (int i = 1; i < 15; i++){
			fa[i] = new LWall(new Rectangle(103 + i*30, 587-i*10, 20, 40));
		}
		
		fa = new GameObject[]{new LWall(new Rectangle(3, 587, 4240, 72))};
		return fa;
		
	}

}
