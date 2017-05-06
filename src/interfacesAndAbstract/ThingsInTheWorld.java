package interfacesAndAbstract;

import staticClasses.Formulas;

public enum ThingsInTheWorld {

	WALL(Formulas.STANDARDJUMPHEIGHT,Formulas.STANDARGD_SPEED,Formulas.STANDARGD_SLOWDOWNSPEED,Formulas.STANDARGD_WALLFALLINGSPEED,Formulas.STANDARGD_WALLJUMPHEIGHT,Formulas.STANDARGD_WALLJUMPDISTANCE,Formulas.STANDARGD_MORETHEMAXWALLSPEEDSLOWDOWN, Formulas.STANDARGD_MAXWALLSPEED), 
	CLIMBINGWALL(0,0,0,0,0,0,0,0), 
	WATER(0,0,0,0,0,0,0,0), 
	PLAYER(0,0,0,0,0,0,0,0);
	
	private final int wallSlide, walkingSpeed, slowDownWalkingSpeed, wallJumpHeight, wallJumpDistance, maxSlowDown, maxSlidingSpeed, jumpHeight;

	private ThingsInTheWorld(int jumpHeight, int walkingSpeed, int slowdownSpeed,int wallSlide, int wallJumpHeight, int wallJumpDistance, int maxSlowDown, int maxSlidingSpeed) {
        this.jumpHeight = jumpHeight;
        this.wallSlide = wallSlide;
        this.wallJumpHeight = wallJumpHeight;
        this.wallJumpDistance = wallJumpDistance;
        this.walkingSpeed = walkingSpeed;
        this.slowDownWalkingSpeed = slowdownSpeed;
        this.maxSlowDown = maxSlowDown;
        this.maxSlidingSpeed = maxSlidingSpeed;
    }
    
    public int getSlide() {
        return wallSlide;
    }
    
    public int getWalkingSpeed() {
        return walkingSpeed;
    }
    
    public int getSlowDownSpeed() {
        return slowDownWalkingSpeed;
    }
    
    public int getWallJumpHeight() {
        return wallJumpHeight;
    }
    
    public int getWallJumpDistance() {
        return wallJumpDistance;
    }

	public int getMaxSlowDown() {
		return maxSlowDown;
	}
	
	public int getMaxSlidingSpeed() {
		return maxSlidingSpeed;
	}

	public int getJump() {
		return jumpHeight;
	}
}
