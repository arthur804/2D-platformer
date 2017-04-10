package levels;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.activity.InvalidActivityException;

import ForReal.Camera;
import ForReal.MyPlayer;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import staticClasses.RenderAndLocation;

public abstract class BasicLevel {

	private GameObject[] walls;
	private MyMovingObject[] guys;
	protected MyPlayer pl;
	private Camera cam;
	
	public BasicLevel(GameObject[] walls, MyMovingObject[] guys){		
		this.walls = walls;
		this.guys = guys;
		pl = new MyPlayer(new Point(50,500));
	}
	
	public void startCam(int widthOfScreen, int heightOfScreen){
		cam = new Camera(widthOfScreen, heightOfScreen);
	}
	
	public void draw(Graphics2D g){
		
		cam.reCamera(pl);
		g.translate(cam.x, cam.y);
		
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

	public void restartMoi() {
		// TODO 
		pl = new MyPlayer(new Point(50,500));
	}

}
