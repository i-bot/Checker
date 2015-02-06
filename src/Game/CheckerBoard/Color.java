package Game.CheckerBoard;

public enum Color {
	LIGHT, DARK;
	
	public static Color getOpponentsColor(Color color){
		return (color == LIGHT)? DARK : LIGHT;
	}
}
