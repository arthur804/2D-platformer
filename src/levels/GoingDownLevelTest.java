package levels;

import java.awt.Point;

import ForReal.MyPlayer;
import ForReal.Wall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class GoingDownLevelTest extends BasicLevel {

	public GoingDownLevelTest() {
		super(
				new GameObject[]{
						new Wall(0, 00, 100, 100)},
				new MyMovingObject[]{
						
					}
				);

		//TODO remove this
		pl = new MyPlayer(new Point(50,500));
		pl.vector[1] = -100;
	}

}


