package levels;

import java.awt.Point;

import ForReal.MyPlayer;
import ForReal.Wall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class GoingUpLevelTest extends BasicLevel {

	public GoingUpLevelTest() {
		super(
				new GameObject[]{
						new Wall(0, 500, 100, 100)},
				new MyMovingObject[]{
						
					}
				);

		//TODO remove this
		pl = new MyPlayer(new Point(50,50));
		pl.vector[1] = 1;
	}

}
