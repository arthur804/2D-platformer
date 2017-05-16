package eccentialItems;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfacesAndAbstract.ContainsMovers;
import interfacesAndAbstract.MyMovingObject;
import interfacesAndAbstract.ThingsInTheWorld;
import staticClasses.Formulas;

public class Spear extends MyMovingObject{
	
	private boolean thrown;
	private boolean landed;
	private boolean wantsToDisappear;
	private ContainsMovers iAmIn = null;
	
	public Spear() {
		super(new Rectangle(10, 10, 10, 10), ThingsInTheWorld.NO_COLISION);
	}

	public void setCorner(int throwingCorner){
		vector[1] = ((int) (Formulas.STANDARD_THROWINGSPEED_SPEAR * Math.cos(Math.toRadians(throwingCorner))));
		vector[0] = ((int) (Formulas.STANDARD_THROWINGSPEED_SPEAR * Math.sin(Math.toRadians(throwingCorner))));
		thrown = true;
	}
	@Override
	protected void calcNextX() {
		if (iAmIn != null){
			vector[1] += iAmIn.spearSlowDownX;
		} else 
			vector[1] += Formulas.STANDARD_XSLOWDOWN_SPEAR;
	}

	@Override
	protected void calcNextY() {		
		if (iAmIn != null){
			vector[1] += iAmIn.SpearFallingSpeed;
		} else 
			vector[1] += Formulas.STANDARD_FALLINGSPEED_SPEAR;
		
		
	}
	@Override
	public void preUpdate() {
		if (thrown && !landed){
			super.preUpdate();
		}
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.orange);
		super.baseDraw(g);
		
	}

}
