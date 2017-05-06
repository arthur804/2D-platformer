package eccentialItems;

import java.awt.image.BufferedImage;

/**
 * you give in animations the differetn amount of the different animation with me it will be 1 for standing still and 3 for most others
 * the order in witch you give them will be the number that you give
 */
public class MultipleStatesAnimation extends Animation{

	private int state = 0;
	private int[] animations;
	public MultipleStatesAnimation(BufferedImage spriteSheet, int sizeSpriteX, int sizeSpriteY, int xPixelsBet,int yPixelsBet, int[] animations){
		super(spriteSheet,sizeSpriteX,sizeSpriteY,xPixelsBet,yPixelsBet);
		this.animations = animations;
		maxFrames = animations[0];
	}
	
	/**
	 * 
	 * the first number in the list is 0
	 */
	public void setAnimationGetting(int counter){
		if (state == counter)
			return;
		minFrames = 0;
		int i = 0;
		for (;i < counter; i++){
		//maxFrames, minFrames
			//0 10
			minFrames += animations[i];
		}
		maxFrames = minFrames + animations[i];
		witch = minFrames;
		minFrames--;
		state = counter;
	}
	@Override
	public void nextFrame() {
		if (animations[state] == 1)
			return;
		super.nextFrame();
	}
}
