package levels;

import java.awt.Point;
import java.awt.Rectangle;

import forReal.LWall;
import forReal.SMyPlayer;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class LevelColision2 extends BasicLevel{

	public LevelColision2() {
		super(
				new GameObject[]{
						new LWall(new Rectangle(26 + 100,341,120,45)),
						new LWall(new Rectangle(-10 + 100,04,35,343)),
						new LWall(new Rectangle(145 + 100,67,34,297))
				},
				new MyMovingObject[]{
						
					}
				);

		pl = new SMyPlayer(new Point(50 + 100,320));
	}
	
}
