package levels;

import java.awt.Point;

import ForReal.MyPlayer;
import ForReal.Wall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class GoingLeftTestLevel extends BasicLevel {

	public GoingLeftTestLevel() {
		super(
				new GameObject[]{
						new Wall(100, 70, 100, 100)},
				new MyMovingObject[]{
						
					}
				);

		//TODO remove this
		pl = new MyPlayer(new Point(50,50));
		pl.vector[1] = 1;
		pl.vector[0] = 1;
	}

}

