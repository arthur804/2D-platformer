package containers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.ContainsMovers;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;
import staticClasses.Formulas;

public class Water extends ContainsMovers{

	public Water(Rectangle bounds, int rainLevelMinRequired) {
		super(bounds, ThingsInTheWorld.WATER, true, true, true, false, 1, true, Formulas.NOJUMPOUT, 5, Formulas.WATER_MAXARROWDOWNSPEED, Formulas.WATER_LEAVINGVETOR, Formulas.WATER_FALLINGSPEED_SPEAR, Formulas.STANDARD_XSLOWDOWN_SPEAR);
		// TODO Auto-generated constructor stub
		this.rainLevelMinRequired = rainLevelMinRequired;
	}

	//0 is always 1 is just afer the rain 2 is second day, 3 is during dry day
	public final int rainLevelMinRequired;

	@Override
	public boolean actionPerformed(MyMovingObject staticObject) {
		return true;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		super.baseDraw(g);
	}
}
