package ForReal;

import java.awt.Graphics2D;

import levels.*;
public class LevelManager {


	private BasicLevel lvl2;
	
	public LevelManager(){
		lvl2 = new WallPlayTestLevel();		
	}
	
	public void draw(Graphics2D g){
		lvl2.draw(g);
	}
	
	public void update(){
		lvl2.preUpdate();
		lvl2.colision();
		lvl2.update();
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
