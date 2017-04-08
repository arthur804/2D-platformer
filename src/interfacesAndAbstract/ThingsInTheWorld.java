package interfacesAndAbstract;

import staticClasses.Formulas;

public enum ThingsInTheWorld {

	WALL(0,Formulas.STANDARGD_WALLFALLINGSPEED,Formulas.STANDARGD_WALLJUMPHEIGHT,Formulas.STANDARGD_WALLJUMPDISTANCE,Formulas.STANDARGD_MORETHEMAXWALLSPEEDSLOWDOWN, Formulas.STANDARGD_MAXWALLSPEED), 
	CLIMBINGWALL(1,0,0,0,0,0), 
	PLAYER(2,0,0,0,0,0);
	
	private int numVal, WallSlide, WallJumpHeight, WallJumpDistance, maxSlowDown, maxSlidingSpeed;

	private ThingsInTheWorld(int numVal, int wallSlide, int WallJumpHeight, int WallJumpDistance, int MaxSlowDown, int MaxSlidingSpeed) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
    
    public int getSlide() {
        return WallSlide;
    }
    
    public int getWallJumpHeight() {
        return WallJumpHeight;
    }
    
    public int getWallJumpDistance() {
        return WallJumpDistance;
    }

	public int getMaxSlowDown() {
		return maxSlowDown;
	}
	
	public int getMaxSlidingSpeed() {
		return maxSlidingSpeed;
	}
}
