package staticClasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesOfLevel {
	
	public ImagesOfLevel(){
		
		try {
			playerSprites = ImageIO.read(new File(location + "PlayerSprite.png"));
			playerSpritesWalk = ImageIO.read(new File(location + "PlayerSpriteWalk.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	private static final String location = (System.getProperty("user.dir") + "//Images//");
	
	public BufferedImage playerSprites;
	public BufferedImage playerSpritesWalk;
	public final int playerSpritesWidth = 21, playerSpritesHeight = 30, xDif = 1, yDif = 0;
}
