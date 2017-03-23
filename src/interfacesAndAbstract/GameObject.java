package interfacesAndAbstract;
import java.awt.Graphics2D;
import java.awt.Rectangle;

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
		//TODO
		g.draw(myRectangle);
		g.fill(myRectangle);
	}
	
	public char returnChar(){
		return type;
	}
}
