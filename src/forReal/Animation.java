package forReal;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

	private ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	private int witch = 0;
	
	public Animation(BufferedImage spriteSheet, int sizeSpriteX, int sizeSpriteY, int xPixelsBet,int yPixelsBet){
		for (int x = 0; x < spriteSheet.getWidth(); x+= sizeSpriteX + xPixelsBet) 
			for (int y = 0; y < spriteSheet.getHeight(); y+= sizeSpriteY + yPixelsBet) {
				frames.add(spriteSheet.getSubimage(x, y, sizeSpriteX, sizeSpriteY));
			}
	}
	
	public void nextFrame(){
		witch++;
		if (witch == frames.size()){
			witch = 0;
		}
	}
	public BufferedImage getCorrectFrame(){
		return frames.get(witch);
	}
	
	public void flip(){
		int width = -frames.get(0).getWidth();
		int size = frames.size();
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(width, 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			
		for(int i = 0; i < size; i++){
			frames.set(i, op.filter(frames.get(i), null));
		}
	}
}
