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
//				new LWall(new Rectangle(90, 380, 63, 130))
				},
				
				new MyMovingObject[] { new SMovingWall(new Rectangle(50, 550, 40, 50), ThingsInTheWorld.WALL,
						new Point[] { new Point(50, 550), new Point(50, 470) }, 10, 10, 100, false) });
		// TODO Auto-generated constructor stub
	}

}
