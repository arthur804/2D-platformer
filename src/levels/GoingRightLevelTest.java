package levels;

import java.awt.Point;

import ForReal.MyPlayer;
import ForReal.Wall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class GoingRightLevelTest extends BasicLevel {

	public GoingRightLevelTest() {
		super(
				new GameObject[]{
						new Wall(0, 70, 100, 300)},
				new MyMovingObject[]{
						
					}
				);

		//TODO remove this
		pl = new MyPlayer(new Point(200,250));
		pl.vector[1] = -1;
		pl.vector[0] = -1;
	}

}

