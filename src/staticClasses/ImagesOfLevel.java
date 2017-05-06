package staticClasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesOfLevel {
	
	public ImagesOfLevel(){
		
		try {
//			playerSprites = ImageIO.read(new File(location + "JXPManStandStill.png"));
			playerSprites = ImageIO.read(new File(location + "JXPMANWALK.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	private static final String location = (System.getProperty("user.dir") + "//Images//");
	
//	public BufferedImage playerSprites;
	public BufferedImage playerSprites;
	public final int playerSpritesWidth = 14, playerSpritesHeight = 27, xDif = 1, yDif = 0;

	public final int[] imagePlayerArray = new int[]{1,3,1,3,1,1,1};
}
