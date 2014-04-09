package GameEngine.AI_Interface;

import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;
import GameEngine.GameRules.Rule;

public interface AI_Interface {

	public Move computeNextMove(CheckerBoard checkerBoard, Rule rule, Color color);
	
}
