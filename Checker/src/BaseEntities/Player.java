package BaseEntities;

import java.util.ArrayList;

public class Player extends BaseEntity{

	ArrayList<Attack> attacks;
	
	public Player(int x, int y) {
		super(x, y);
		attacks = new ArrayList<Attack>();
	}

	@Override
	public void computeNextPosition(float timeSinceLastFrame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void computeState(ArrayList<BaseEntity> entities) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Attack> getAttacks(){
		return attacks;
	}
	
}
