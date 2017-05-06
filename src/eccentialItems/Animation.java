package eccentialItems;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.ArrayList;

public class Animation {

	private ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	private BufferedImage[] framesFliped;
	protected int witch = 0;
	private boolean goingUp = true;
	private boolean lengthOfOne = false;
	private boolean fliped = true;
	protected int maxFrames, minFrames;
	
	public Animation(BufferedImage spriteSheet, int sizeSpriteX, int sizeSpriteY, int xPixelsBet,int yPixelsBet){
		for (int x = 0; x <= spriteSheet.getWidth(); x+= sizeSpriteX + xPixelsBet) 
			for (int y = 0; y < spriteSheet.getHeight(); y+= sizeSpriteY + yPixelsBet) {
				try {
					frames.add(spriteSheet.getSubimage(x, y, sizeSpriteX, sizeSpriteY));
				} catch (RasterFormatException e){
					
				}
			}
		framesFliped = new BufferedImage[frames.size()];
		if (frames.size() == 1)
			lengthOfOne = true;
		fillFliped();
		maxFrames = framesFliped.length;
		minFrames = -1;
	}
	
	public void nextFrame(){
		if (lengthOfOne)
			return;
		if (goingUp){
			witch++;
			if (witch == maxFrames){
				goingUp = false;
				witch--;
			}
		} else{
			witch--;
			if (witch == minFrames){
				goingUp = true;
				witch++;
			}
		}
	}
	public BufferedImage getCorrectFrame(){
		if (fliped)
			return frames.get(witch);
		else
			return framesFliped[witch];
	}
	
	public void flip(){
		fliped = !fliped;
	}
	private void fillFliped(){
		int width = -frames.get(0).getWidth();
		int size = frames.size();
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(width, 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			
		for(int i = 0; i < size; i++){
			framesFliped[i] = (op.filter(frames.get(i), null));
		}
		
	}
}
