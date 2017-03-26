package interfacesAndAbstract;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import staticClasses.RenderAndLocation;

public abstract class GameObject implements GameInt{

	public Rectangle myRectangle;
	public char type;
	public boolean seen = true;
	//public image image or something for sprites
	
	public GameObject(Rectangle bounds, char t){
		myRectangle = bounds;
		type = t;
	}
	protected void baseDraw(Graphics2D g) {
		if (!seen)
			return;
		//TODO
		g.draw(myRectangle);
		g.fill(myRectangle);
	}
	
	public void sightTest(int beginX, int beginY, int endX, int endY){
		seen = RenderAndLocation.isObjectInSight(myRectangle, beginX, beginY, endX, endY);
	}
	
	public char returnChar(){
		return type;
	}
}
