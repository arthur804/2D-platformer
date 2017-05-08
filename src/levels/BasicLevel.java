package levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Arrays;

import eccentialItems.SMyPlayer;
import eccentialItems.TCamera;
import interfacesAndAbstract.ContainsMovers;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import staticClasses.ImagesOfLevel;
import staticClasses.RenderAndLocation;

public abstract class BasicLevel {

	private GameObject[] walls;
	private MyMovingObject[] otherMovingObjects;
	private ContainsMovers[] containers;
	protected SMyPlayer pl;
	private TCamera cam;
	//TODO think about this
	public static final ImagesOfLevel theBasicLevelImages = new ImagesOfLevel();
	
	public BasicLevel(GameObject[] walls, MyMovingObject[] otherMovingObjects, ContainsMovers[] containers){		
		this.walls = walls;
		this.otherMovingObjects = otherMovingObjects;
		pl = new SMyPlayer(new Point(50,500), theBasicLevelImages.playerSprites, theBasicLevelImages.playerSpritesWidth, theBasicLevelImages.playerSpritesHeight, theBasicLevelImages.xDif, theBasicLevelImages.yDif, theBasicLevelImages.imagePlayerArray);
		this.containers = containers;
		Arrays.sort(containers);
	}
	
	public void startCam(int widthOfScreen, int heightOfScreen){
		cam = new TCamera(widthOfScreen, heightOfScreen);
	}
	
	public void draw(Graphics2D g){
		
		cam.reCamera(pl);
		g.translate(- cam.x, cam.y);
		
		for(int i = 0; i < containers.length; i++){
			containers[i].draw(g);
		}
		
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
		testIfInSomething();
		
		for (int i = 0; i < otherMovingObjects.length; i++)
			otherMovingObjects[i].preUpdate();
		//how does this effect me, Player 
		RenderAndLocation.walltest(pl, walls);

		for (int i = 0; i < otherMovingObjects.length; i++){
			RenderAndLocation.movingWallCalculation(otherMovingObjects[i],pl, walls);
//			otherMovingObjects[0].update();
		}
		if (pl.dead){
			restartMoi();
			pl.dead = false;
		}
		pl.update();
//		System.out.println(pl.toString());
	}
	

	private void testIfInSomething() {
		boolean fart = true;
		for(int i = 0; i < containers.length; i++)
			if (containers[i].isItIn(pl)){
				pl.isIn(containers[i]);
				fart = false;
				break;
			}
		if (fart)
			pl.isIn(null);
	}

	public void controls(boolean[] keysPressed) {
		pl.controls(keysPressed);
	}

	public void restartMoi() {
		pl.absoluteLocation[0] = pl.absoluteLocation[1] = 0;
	}

}
