package eccentialItems;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;

public class Spear extends MyMovingObject{
	
	private static final int STANDARD_THROWINGSPEED = 6;
	

	public Spear(Rectangle bounds, ThingsInTheWorld e, int throwingCorner) {
		super(bounds, e);
		vector[1] = ((int) (STANDARD_THROWINGSPEED * Math.cos(Math.toRadians(throwingCorner))));
		vector[0] = ((int) (STANDARD_THROWINGSPEED * Math.sin(Math.toRadians(throwingCorner))));
		System.out.println(vector[1] + "  " + vector[0]);
	}

	@Override
	protected void calcNextX() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void calcNextY() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.orange);
		super.baseDraw(g);
		
	}

}
