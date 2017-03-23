package levels;

import java.awt.Rectangle;

import ForReal.Wall;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;

public class Level_Colision extends BasicLevel {

	public Level_Colision() {
		super(new GameObject[] { new Wall(new Rectangle(169, 286, 230, 238)), new Wall(new Rectangle(0, 830, 57, 16)),
				new Wall(new Rectangle(-60, 805, 69, 42)), new Wall(new Rectangle(109, 758, 21, 25)),
				new Wall(new Rectangle(89, 818, 25, 18)), new Wall(new Rectangle(74, 837, 6, 22)),
				new Wall(new Rectangle(63, 853, 6, 13)), new Wall(new Rectangle(111, 799, 11, 19)),
				new Wall(new Rectangle(66, 789, 37, 6)), new Wall(new Rectangle(110, 758, 10, 24)),
				new Wall(new Rectangle(116, 741, 20, 43)), new Wall(new Rectangle(121, 668, 30, 28)),
				new Wall(new Rectangle(70, 645, 13, 36)), new Wall(new Rectangle(104, 618, 26, 26)),
				new Wall(new Rectangle(163, 606, 20, 33)), new Wall(new Rectangle(199, 703, 8, 24)),
				new Wall(new Rectangle(186, 651, 36, 22)), new Wall(new Rectangle(182, 761, 20, 41)),
				new Wall(new Rectangle(147, 821, 34, 43)), new Wall(new Rectangle(155, 721, 13, 22)),
				new Wall(new Rectangle(244, 747, 19, 35)), new Wall(new Rectangle(223, 828, 30, 33)),
				new Wall(new Rectangle(279, 756, 6, 39)), new Wall(new Rectangle(265, 683, 14, 43)),
				new Wall(new Rectangle(234, 711, 10, 16)), new Wall(new Rectangle(-17, 742, 31, 38)),
				new Wall(new Rectangle(41, 724, 14, 14)), new Wall(new Rectangle(45, 677, 5, 15)),
				new Wall(new Rectangle(58, 637, 16, 15)), new Wall(new Rectangle(83, 610, 11, 19)),
				new Wall(new Rectangle(145, 613, 13, 20)) }, new MyMovingObject[] {

		});
	};
}
