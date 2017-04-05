package levels;

import java.awt.Rectangle;

import ForReal.Wall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class WallPlayTestLevel extends BasicLevel{

	public WallPlayTestLevel() {
		

		
		super(
				new GameObject[]{new Wall(new Rectangle(3,587,424,72)),
						new Wall(new Rectangle(170,419,29,134)),
						new Wall(new Rectangle(266,412,43,200)),
						new Wall(new Rectangle(18,550,20,6)),
						new Wall(new Rectangle(68,495,26,10)),
						new Wall(new Rectangle(24,425,26,17)),
						new Wall(new Rectangle(90,380,63,13))
				},
				new MyMovingObject[]{
						
					}
				);
		// TODO Auto-generated constructor stub
	}

}
