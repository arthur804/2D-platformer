package interfacesAndAbstract;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import eccentialItems.Animation;
import staticClasses.RenderAndLocation;

public abstract class GameObject {

	public Rectangle myRectangle;
	public ThingsInTheWorld type;
	public boolean seen = true;
	protected Animation myAnimator;
	public boolean canBeStepedOn = true;
	
	//public image image or something for sprites
	
	public GameObject(Rectangle bounds, ThingsInTheWorld e){
		myRectangle = bounds;
		type = e;
	}
	
	public GameObject(Rectangle bounds, ThingsInTheWorld e, BufferedImage spriteSheet, int sizeSpriteX, int sizeSpriteY, int xPixelsBet,int yPixelsBet){
		myRectangle = bounds;
		type = e;
		myAnimator = new Animation(spriteSheet, sizeSpriteX, sizeSpriteY, xPixelsBet, yPixelsBet);
	}
	
	public void updateAnimation(){
		myAnimator.nextFrame();
	}
	
	protected void baseDraw(Graphics2D g) {
		if (!seen)
			return;
		//TODO change this method
		g.fill(myRectangle);
	}
	
	protected void baseDraw(Graphics2D g, Rectangle rec, BufferedImage image) {
		if (!seen)
			return;
		//TODO
		if (image != null)
			g.drawImage(image, rec.x, rec.y, null);
		else
			g.fill(myRectangle);
	}
	
	//TODO
	public void sightTest(Rectangle cameraRectanlge){
		seen = RenderAndLocation.isObjectContained(myRectangle, cameraRectanlge);
	}
	
	public abstract void draw(Graphics2D g);
	
}
