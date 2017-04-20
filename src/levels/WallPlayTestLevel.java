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

		super(new GameObject[] { new LWall(new Rectangle(3, 587, 4240, 72)),
				// new Wall(new Rectangle(170,419,29,134)),
				// new Wall(new Rectangle(266,412,43,200)),
				// new Wall(new Rectangle(18,550,20,6)),
				// new Wall(new Rectangle(68,495,26,10)),
				// new Wall(new Rectangle(24,425,26,17)),
				// new LWall(new Rectangle(90, 380, 63, 130))
		},

				new MyMovingObject[] {
						new SMovingWall(new Rectangle(100, 500, 40, 50), ThingsInTheWorld.WALL,
							new Point[] { new Point(100, 500), new Point(150, 500) }, 10, 10, 100, false),
						new SMovingWall(new Rectangle(200, 500, 40, 70), ThingsInTheWorld.WALL,
							new Point[] { new Point(200, 500), new Point(200, 450) }, 10, 10, 100, false), 
						
//						new SMovingWall(new Rectangle(250, 500, 40, 50), ThingsInTheWorld.WALL,
//								new Point[] { new Point(250, 500), new Point(300, 550) }, 10, 10, 100, false), 
						
						new SMovingWall(new Rectangle(300, 550, 140, 50), ThingsInTheWorld.WALL,
							new Point[] { new Point(300, 500), new Point(310, 510) }, 10, 10, 100, false)

						});
		// TODO Auto-generated constructor stub
	}

}
