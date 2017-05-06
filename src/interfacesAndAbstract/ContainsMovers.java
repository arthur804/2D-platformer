package interfacesAndAbstract;

import java.awt.Rectangle;

import staticClasses.RenderAndLocation;

public abstract class ContainsMovers extends GameObject {
	
	public final boolean ALWAYS_ABLE_TO_JUMP;
	public final boolean MOVE_IN_Y;
	
//	public final boolean ALWAYS_ABLE_TO_JUMP;
	public final boolean MOVE_IN_X;
	
	//TODO use this
	protected final boolean REQUIRES_ACTION;
	protected boolean actionPreformed;
	
	//TODO sort this and first test the highest priority and then stop when all of the same priority have been checked and one of that priority has indeed the thing contained in it
	public final int PRIORITY;
	
	public ContainsMovers(Rectangle bounds, ThingsInTheWorld e, boolean alwaysAbleToMove, boolean moveInY, boolean moveInX, boolean requiresActionToEnter, int priority){
		super(bounds, e);
		ALWAYS_ABLE_TO_JUMP = alwaysAbleToMove;
		MOVE_IN_Y = moveInY;
		MOVE_IN_X = moveInX;
		REQUIRES_ACTION = requiresActionToEnter;
		actionPreformed = !REQUIRES_ACTION;
		PRIORITY = priority;
	}

	//get jump		get x speed ezv
	
	
	public boolean isItIn(GameObject object){
		if (!seen || !actionPreformed)
			return false;
		return RenderAndLocation.isObjectContained(object.myRectangle, myRectangle);
	}
	
	public abstract boolean actionPerformed(MyMovingObject staticObject);

	
}
