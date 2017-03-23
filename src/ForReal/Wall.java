package ForReal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.GameObject;

public class Wall extends GameObject{

	public Wall(Rectangle rec) {
		super(rec, 'w');
	}
	
	//Maybe Remove?
	public Wall(int x, int y, int width, int height) {
		super(new Rectangle(x, y, width, height), 'w');	
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		super.baseDraw(g);
	}
}
