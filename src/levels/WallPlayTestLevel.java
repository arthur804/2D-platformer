package levels;

import java.awt.Point;
import java.awt.Rectangle;

import containers.Water;
import interfacesAndAbstract.ContainsMovers;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.ThingsInTheWorld;
import movingWalls.BaseMovingWall;
import movingWalls.NormalMovingWall;
import otherGameObjects.Wall;

public class WallPlayTestLevel extends BasicLevel {

	public WallPlayTestLevel() {
		super(fillMeUpWall(), fillMeUpMove(), fillMeUpContainer());
	}
	
	private static GameObject[] fillMeUpWall(){
		int amount = 100;
		GameObject[] fa = new GameObject[amount];
		fa[0] = new Wall(new Rectangle(3, 587, 4240, 72));
		
		for (int i = 1; i < amount; i++){
			fa[i] = new Wall(new Rectangle(103 + 30, 587-10, 20, 40));
			fa[i].canBeStepedOn = false;
		}
//		, new LWall(new Rectangle(3, 300, 4240, 70))
		fa[14] = new Wall(new Rectangle(500, 20, 20, 40));
		fa = new GameObject[]{new Wall(new Rectangle(3, 587, 4240, 2))};/*, new LWall(new Rectangle(200, 510, 150, 50))*/
		return fa;
		
	}
	private static BaseMovingWall[] fillMeUpMove(){
		return new BaseMovingWall[] {
//				new NormalMovingWall(new Rectangle(200, 510, 40, 50), ThingsInTheWorld.WALL,
//				new Point[] { new Point(200, 510), new Point(150, 510) }, 10, 10, 100, true),
		
//			new NormalMovingWall(new Rectangle(200, 580, 40, 40), ThingsInTheWorld.WALL,
//				new Point[] { new Point(200, 580), new Point(200, 590) }, 10, 10, 100, true), 
			
			new NormalMovingWall(new Rectangle(300, 450, 40, 50), ThingsInTheWorld.WALL,
					new Point[] { new Point(300, 450), new Point(300, 550) }, 10, 10, 1000, true), 
			
//			new NormalMovingWall(new Rectangle(300, 550, 140, 50), ThingsInTheWorld.WALL,
//				new Point[] { new Point(300, 500), new Point(310, 600) }, 10, 10, 100, false),
			
		
//		new RotatorWallPlatform(new Rectangle(300, 500, 140, 50), ThingsInTheWorld.WALL,
//				1.5, true, true, 10, 6),
		
			
//			new JiggelingWall(new Rectangle(10, 500, 100, 30), ThingsInTheWorld.WALL, 30, 30, 200, 200 , 150, false)
			
				};
		
	}
	

	private static ContainsMovers[] fillMeUpContainer(){
		return new ContainsMovers[] {
				new Water(new Rectangle(300, 100, 600, 600), 0)
		};
	}
}
