package levels;

import java.awt.Graphics2D;
import java.awt.Point;

import forReal.SMyPlayer;
import forReal.TCamera;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import staticClasses.RenderAndLocation;

public abstract class BasicLevel {

	private GameObject[] walls;
	private MyMovingObject[] guys;
	protected SMyPlayer pl;
	private TCamera cam;
	private MyMovingObject[] player;
	
	public BasicLevel(GameObject[] walls, MyMovingObject[] guys){		
		this.walls = walls;
		this.guys = guys;
		pl = new SMyPlayer(new Point(50,500));
		 player = new MyMovingObject[]{ pl };
	}
	
	public void startCam(int widthOfScreen, int heightOfScreen){
		cam = new TCamera(widthOfScreen, heightOfScreen);
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

		for (int i = 0; i < guys.length; i++){
			RenderAndLocation.walltest(guys[i], player);
			RenderAndLocation.walltest(guys[i], walls);
			guys[i].update();
		}
		RenderAndLocation.walltest(pl, guys);
		pl.update();
		
		//Maybe Also check all guys against all other guys
	}
	
//	public void update(){
//		for (int i = 0; i < guys.length; i++){
//			guys[i].update();
//		}
//	}

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
		pl = new SMyPlayer(new Point(50,500));
		 player = new MyMovingObject[]{ pl };
	}

}
