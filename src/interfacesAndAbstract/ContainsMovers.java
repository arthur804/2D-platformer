package interfacesAndAbstract;

public abstract class ContainsMovers {
	
	public final boolean ALWAYS_ABLE_TO_JUMP;
	public final boolean MOVE_IN_Y;
	
//	public final boolean ALWAYS_ABLE_TO_JUMP;
	public final boolean MOVE_IN_X;
	
	//TODO use this
	protected final boolean REQUIRES_ACTION;
	
	public ContainsMovers(boolean alwaysAbleToMove, boolean moveInY, boolean moveInX, boolean requiresActionToEnter){
		ALWAYS_ABLE_TO_JUMP = alwaysAbleToMove;
		MOVE_IN_Y = moveInY;
		MOVE_IN_X = moveInX;
		REQUIRES_ACTION = requiresActionToEnter;
	}

	//get jump		get x speed ezv
}
