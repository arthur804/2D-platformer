package interfacesAndAbstract;

import staticClasses.Formulas;

public enum ThingsInTheWorld {

	WALL(Formulas.STANDARDJUMPHEIGHT,
			Formulas.STANDARGD_WALKINGSPEED,
			Formulas.STANDARGD_SLOWDOWNSPEED,
			Formulas.STANDARD_MAXWALKINGSPEED,
			Formulas.STANDARGD_WALLFALLINGSPEED,
			Formulas.STANDARGD_WALLJUMPHEIGHT,
			Formulas.STANDARGD_WALLJUMPDISTANCE,
			Formulas.STANDARGD_MORETHEMAXWALLSPEEDSLOWDOWN, 
			Formulas.STANDARGD_MAXWALLSPEED	), 
	
	//private final int jumpHeight, walkingSpeed, slowDownWalkingSpeed, maxWalkingSpeed, wallSlide, wallJumpHeight, wallJumpDistance, maxSlowDown, maxSlidingSpeed;
	
	CLIMBINGWALL(0,0,0,0,0,0,0,0,0), 
	
	WATER(Formulas.WATER_JUMPHEIGHT, 
			Formulas.WATER_WALKINGSPEED,
			Formulas.WATER_SLOWDOWNWALKINGSPEED,
			Formulas.WATER_MAXWALKINGSPEED,
			Formulas.WATER_GLIDINGSPEED,
			Formulas.WATER_FALLINGSPEED,
			Formulas.WATER_MAXFALLINGSPEED,
			Formulas.WATER_MAXGLIDINGSPEED,
			Formulas.WATER_ARROWDOWN),
	
	NO_COLISION(0,0,0,0,0,0,0,0,0);
	
	private ThingsInTheWorld(int jumpHeight, int walkingSpeed, int slowdownSpeed, int maxWalkingSpeed, int wallSlideOrGliding, int wallJumpHeightOrFallingSpeed, int wallJumpDistanceOrMaxFallingSpeed, int maxSlowDownOrGlidingMax, int maxSlidingSpeedOrArrowDown) {
		this.jumpHeight = jumpHeight;
		this.walkingSpeed = walkingSpeed;
		this.slowDownWalkingSpeed = slowdownSpeed;
		this.maxWalkingSpeed = maxWalkingSpeed;
		this.wallSlideOrGliding = wallSlideOrGliding;
		this.wallJumpHeightOrFallingSpeed = wallJumpHeightOrFallingSpeed;
		this.wallJumpDistanceOrMaxFallingSpeed = wallJumpDistanceOrMaxFallingSpeed;
		this.maxSlowDownOrGlidingMax = maxSlowDownOrGlidingMax;
		this.maxSlidingSpeedOrArrowDown = maxSlidingSpeedOrArrowDown;
		walkSlowerThenSlowdown = walkingSpeed < slowdownSpeed;
	}
	
	private final int jumpHeight, walkingSpeed, slowDownWalkingSpeed, maxWalkingSpeed, wallSlideOrGliding, wallJumpHeightOrFallingSpeed, wallJumpDistanceOrMaxFallingSpeed, maxSlowDownOrGlidingMax, maxSlidingSpeedOrArrowDown;
	private final boolean walkSlowerThenSlowdown;
	
	public boolean isWalkSlowerThenSlowdown() {
		return walkSlowerThenSlowdown;
	}

	public int getJumpHeight() {
		return jumpHeight;
	}

	public int getWalkingSpeed() {
		return walkingSpeed;
	}

	public int getSlowDownWalkingSpeed() {
		return slowDownWalkingSpeed;
	}

	public int getMaxWalkingSpeed() {
		return maxWalkingSpeed;
	}

	public int getWallSlideOrGliding() {
		return wallSlideOrGliding;
	}

	public int getWallJumpHeightOrFallingSpeed() {
		return wallJumpHeightOrFallingSpeed;
	}

	public int getWallJumpDistanceOrMaxFallingSpeed() {
		return wallJumpDistanceOrMaxFallingSpeed;
	}

	public int getMaxSlowDownOrGlidingMax() {
		return maxSlowDownOrGlidingMax;
	}

	public int getMaxSlidingSpeedOrArrowDown() {
		return maxSlidingSpeedOrArrowDown;
	}

	
    
   
}
