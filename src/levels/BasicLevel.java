package levels;

import java.awt.Graphics2D;
import java.awt.Point;

import ForReal.MyPlayer;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import staticClasses.RenderAndLocation;

public abstract class BasicLevel {

	private GameObject[] walls;
	private MyMovingObject[] guys;
	protected MyPlayer pl;
	
	public BasicLevel(GameObject[] walls, MyMovingObject[] guys){
		this.walls = walls;
		this.guys = guys;
		pl = new MyPlayer(new Point(50,500));
	}
	
	public void draw(Graphics2D g){
		
		pl.draw(g);
		
		for(int i = 0; i < walls.length; i++){
			walls[i].draw(g);
		}
		
		for(int i = 0; i < guys.length; i++){
			guys[i].draw(g);
		}
	}
	
	public void colision(){
		//how does this effect me Player 
		RenderAndLocation.walltest(pl, walls);
		
		//what are you guys doing with those walls
		for (int i = 0; i < guys.length; i++)
			RenderAndLocation.walltest(guys[i], walls);
		
		//Maybe Also check all guys against all other guys
	}
	
	public void update(){
		pl.update();
		for (int i = 0; i < guys.length; i++)
			guys[i].update();
	}

	public void preUpdate() {
		pl.preUpdate();
		for (int i = 0; i < guys.length; i++)
			guys[i].preUpdate();
	}

	public void controls(boolean[] keysPressed) {
		pl.controls(keysPressed);
		//TODO camera
	}

}
