package levels;

import java.awt.Graphics2D;
import java.awt.Point;

import forReal.SMovingWall;
import forReal.SMyPlayer;
import forReal.TCamera;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import staticClasses.ImagesOfLevel;
import staticClasses.RenderAndLocation;

public abstract class BasicLevel {

	private GameObject[] walls;
	private MyMovingObject[] otherMovingObjects;
	protected SMyPlayer pl;
	private TCamera cam;
	private MyMovingObject[] player;
	public static final ImagesOfLevel theBasicLevelImages = new ImagesOfLevel();
	
	public BasicLevel(GameObject[] walls, MyMovingObject[] otherMovingObjects){		
		this.walls = walls;
		this.otherMovingObjects = otherMovingObjects;
		pl = new SMyPlayer(new Point(50,500), theBasicLevelImages.playerSprites, theBasicLevelImages.playerSpritesWalk,theBasicLevelImages.playerSpritesWidth, theBasicLevelImages.playerSpritesHeight, theBasicLevelImages.xDif, theBasicLevelImages.yDif);
		 player = new MyMovingObject[]{ pl };
	}
	
	public void startCam(int widthOfScreen, int heightOfScreen){
		cam = new TCamera(widthOfScreen, heightOfScreen);
	}
	
	public void draw(Graphics2D g){
		
		cam.reCamera(pl);
		g.translate(- cam.x, cam.y);
		
		pl.draw(g);
		
		for(int i = 0; i < walls.length; i++){
			walls[i].draw(g);
		}
		
		for(int i = 0; i < otherMovingObjects.length; i++){
			otherMovingObjects[i].draw(g);
		}
	}
	
	public void colision(){
		
		pl.preUpdate();

		for (int i = 0; i < otherMovingObjects.length; i++)
			otherMovingObjects[i].preUpdate();
		//how does this effect me, Player 
		RenderAndLocation.walltest(pl, walls);

		for (int i = 0; i < otherMovingObjects.length; i++){
			RenderAndLocation.movingWallCalculation((SMovingWall)otherMovingObjects[i],pl, walls);
		}
		if (pl.dead){
			restartMoi();
			pl.dead = false;
		}
		pl.update();
	}
	

	public void controls(boolean[] keysPressed) {
		pl.controls(keysPressed);
	}

	public void restartMoi() {
		pl.absoluteLocation[0] = pl.absoluteLocation[1] = 0;
	}

}
