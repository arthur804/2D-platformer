package levels;

import java.awt.Point;
import java.awt.Rectangle;

import forReal.LWall;
import forReal.SMyPlayer;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class LevelColision3 extends BasicLevel {

	

	public LevelColision3() {
		super(new GameObject[] { 
				new LWall(new Rectangle(37,40,0,0)),
				new LWall(new Rectangle(61,70,92,167)),
				new LWall(new Rectangle(2,184,61,51)),
				new LWall(new Rectangle(185,231,12,12)),
				new LWall(new Rectangle(145,255,55,92)),
				new LWall(new Rectangle(200,306,125,41)),
				new LWall(new Rectangle(169,86,4,15)),
				new LWall(new Rectangle(182,110,9,16)),
				new LWall(new Rectangle(202,136,7,17)),
				new LWall(new Rectangle(218,170,8,19)),
				new LWall(new Rectangle(235,205,9,18)),
				new LWall(new Rectangle(252,234,13,9)),
				new LWall(new Rectangle(278,251,7,8)),
				new LWall(new Rectangle(328,244,76,92)),
				new LWall(new Rectangle(396,222,220,117)),
				new LWall(new Rectangle(311,241,7,8)),
				new LWall(new Rectangle(306,287,9,9)),
				new LWall(new Rectangle(248,276,13,6)),
				new LWall(new Rectangle(224,258,8,8)),
				new LWall(new Rectangle(301,264,5,15)),
				new LWall(new Rectangle(180,177,10,15)),
				new LWall(new Rectangle(333,157,1,100)),
				new LWall(new Rectangle(442,407,726,119))

			}, new MyMovingObject[] {

		});

		pl = new SMyPlayer(new Point(70,0));
	}
}


