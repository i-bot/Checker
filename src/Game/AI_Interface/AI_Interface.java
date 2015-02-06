package Game.AI_Interface;

import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Color;
import Game.CheckerBoard.Move;
import Game.GameRules.Rule;

public interface AI_Interface {

	public Move computeNextMove(CheckerBoard checkerBoard, Rule rule, Color color);
	
}
