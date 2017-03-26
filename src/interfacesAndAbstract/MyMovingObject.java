package interfacesAndAbstract;
import java.awt.Rectangle;

public abstract class MyMovingObject extends GameObject implements GameMoveInt{

	public MyMovingObject(Rectangle bounds, char t) {
		super(bounds, t);
	}

	public boolean touchingLeft, touchingRight, touchingUp, touchingDown;
	public int[] vector = new int[]{0,0};
	public int maxSpeed;
	public char[] touching = new char[2];
	
	
	public Rectangle nextTangleOnlyY(int i) {
		return new Rectangle(myRectangle.x, myRectangle.y + i, myRectangle.width, myRectangle.height);
	}
	public Rectangle nextTangleOnlyX(int i) {
		return new Rectangle(myRectangle.x + i, myRectangle.y, myRectangle.width, myRectangle.height);
	}
	public Rectangle nextTangle(int nextX, int nextY) {
		return new Rectangle(myRectangle.x + nextX, myRectangle.y + nextY, myRectangle.width, myRectangle.height);
	}

	public void touchingX(int x, char c) {
		touching[0] = c;
		vector[0] = 0;
		//TODO
		myRectangle.x = myRectangle.x + x;
	}
	
	public void touchingY(int y, char c) {
		touching[1] = c;
		vector[1] = 0;
		//TODO
		myRectangle.y = myRectangle.y + y;
	}
		
	public void reTrue(){
		touchingLeft = touchingRight = touchingUp = touchingDown = false;
	}
	
	public void update(){
		myRectangle.setLocation(myRectangle.x + vector[0], myRectangle.y + vector[1]);
	}

	public void preUpdate() {
		calcNextX();
		calcNextY();
	}

	
}
