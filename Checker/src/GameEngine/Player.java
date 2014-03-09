package GameEngine;

public abstract class Player {
	
	protected String name;
	protected Color color_player;
	
	public Player(Color color_player){
		this.color_player = color_player;
	}
	
	public String getName(){
		return name;
	}
	
	public Color getColor_Player(){
		return color_player;
	}
}
