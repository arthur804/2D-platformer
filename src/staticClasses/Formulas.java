package staticClasses;

public class Formulas {

	private Formulas(){}
	
	public static int maxPlayerFall = 15;
	
	public static int grafity(int point){
		if (point == 8)
			return 1;
		else
			return 0;
	}
	
	public static int walkingSpeed(int point){
		if (point == 8)
			return 1;
		else
			return 0;
	}
	
}
