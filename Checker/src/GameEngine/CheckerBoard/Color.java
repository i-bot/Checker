package GameEngine.CheckerBoard;

public enum Color {
	LIGHT, DARK;
	
	public static Color getOpponentColor(Color color){
		return (color == LIGHT)? DARK : LIGHT;
	}
}
