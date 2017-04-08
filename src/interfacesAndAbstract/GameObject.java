package interfacesAndAbstract;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import staticClasses.RenderAndLocation;

public abstract class GameObject {

	public Rectangle myRectangle;
	public ThingsInTheWorld type;
	public boolean seen = true;
	//public image image or something for sprites
	
	public GameObject(Rectangle bounds, ThingsInTheWorld e){
		myRectangle = bounds;
		type = e;
	}
	protected void baseDraw(Graphics2D g) {
		if (!seen)
			return;
		//TODO
		g.fill(myRectangle);
	}
	
	public void sightTest(int beginX, int beginY, int endX, int endY){
		seen = RenderAndLocation.isObjectInSight(myRectangle, beginX, beginY, endX, endY);
	}
	
	public ThingsInTheWorld returnChar(){
		return type;
	}
	
	//TODO look at it
	
	public abstract void draw(Graphics2D g);
	
}
