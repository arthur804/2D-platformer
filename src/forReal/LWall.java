package forReal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.ThingsInTheWorld;

public class LWall extends GameObject{

	public LWall(Rectangle rec) {
		super(rec, ThingsInTheWorld.WALL);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		super.baseDraw(g);
	}
}
