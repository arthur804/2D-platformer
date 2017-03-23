package ForReal;

import java.awt.Graphics2D;

import levels.*;
public class LevelManager {


	private BasicLevel lvl2;
	
	public LevelManager(){
		lvl2 = new LevelColision2();		
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
	}
}
