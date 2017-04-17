package forReal;

import java.awt.Graphics2D;

import levels.*;
public class TLevelManager {


	private BasicLevel lvl2;
	
	
	public TLevelManager(int width, int height){
		lvl2 = new WallPlayTestLevel();		
		lvl2.startCam(width, height);
	}
	//TODO resize?
	public void draw(Graphics2D g){
		lvl2.draw(g);
	}
	
	public void update(){
		lvl2.preUpdate();
		lvl2.colision();
	}
	
	public void controls(boolean[] keysPressed){
		lvl2.controls(keysPressed);
		if (keysPressed[7]){
			restartLevel();
		}
	}
	
	public void restartLevel(){
		lvl2.restartMoi();
	}
}
