package levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import eccentialItems.SMyPlayer;
import eccentialItems.TCamera;
import interfacesAndAbstract.ContainsMovers;
import interfacesAndAbstract.GameObject;
import interfacesAndAbstract.MyMovingObject;
import movingWalls.BaseMovingWall;
import staticClasses.ImagesOfLevel;
import staticClasses.RenderAndLocation;

public abstract class BasicLevel {

	private GameObject[] walls;
	private BaseMovingWall[] otherMovingObjects;
	private ContainsMovers[] containers;
	protected SMyPlayer pl;
	private TCamera cam;
	private ArrayList<MyMovingObject> thrownItems = new ArrayList<MyMovingObject>(2);//TODO
	//TODO think about this
	public static final ImagesOfLevel theBasicLevelImages = new ImagesOfLevel();
	
	public BasicLevel(GameObject[] walls, BaseMovingWall[] otherMovingObjects, ContainsMovers[] containers, MyMovingObject spear){		
		this(walls, otherMovingObjects, containers);
		
		thrownItems.add(spear);
		//TODO remove me
	}
	
	public BasicLevel(GameObject[] walls, BaseMovingWall[] otherMovingObjects, ContainsMovers[] containers){		
		this.walls = walls;
		this.otherMovingObjects = otherMovingObjects;
		pl = new SMyPlayer(new Point(50,500), theBasicLevelImages.playerSprites, theBasicLevelImages.playerSpritesWidth, theBasicLevelImages.playerSpritesHeight, theBasicLevelImages.xDif, theBasicLevelImages.yDif, theBasicLevelImages.imagePlayerArray);
		this.containers = containers;
		Arrays.sort(containers);
		//TODO addMoreTo Me
	}
	
	public void startCam(int widthOfScreen, int heightOfScreen){
		cam = new TCamera(widthOfScreen, heightOfScreen);
	}
	
	public void draw(Graphics2D g){
		
		cam.reCamera(pl);
		g.translate(- cam.x, cam.y);
		int count = thrownItems.size();
		for(int i = 0; i < containers.length; i++){
			containers[i].draw(g);
		}
		for(int i = 0; i < count; i++){
			thrownItems.get(i).draw(g);
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
		if (pl.wannaThrowSpear())
			thrownItems.add(pl.throwSpear());
		testIfInSomething();

		int count = thrownItems.size();
		for(int i = 0; i < count; i++){
			thrownItems.get(i).preUpdate();
		}
		
		for (int i = 0; i < otherMovingObjects.length; i++)
			otherMovingObjects[i].preUpdate();
		//how does this effect me, Player 
		RenderAndLocation.walltest(pl, walls);

		for (int i = 0; i < otherMovingObjects.length; i++){
			RenderAndLocation.movingWallCalculation(otherMovingObjects[i],pl, walls, true);
			otherMovingObjects[i].update(); 
		}
		if (pl.dead){
			restartRelocate();
			pl.dead = false;
		}
		pl.update();
//		System.out.println(pl.toString());
		//TODO clision on everthing if needed
		for(int i = 0; i < count; i++){
			thrownItems.get(i).update();
		}
		
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

	public void restartRelocate() {
		pl.absoluteLocation[0] = pl.absoluteLocation[1] = 0;
	}

}
