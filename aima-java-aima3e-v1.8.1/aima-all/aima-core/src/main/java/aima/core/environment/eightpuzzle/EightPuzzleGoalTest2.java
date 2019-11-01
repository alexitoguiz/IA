package aima.core.environment.eightpuzzle;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.search.framework.GoalTest;
import java.util.List;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Ravi Mohan
 * @author Fernando Peña
 * 
 */
public class EightPuzzleGoalTest2 implements GoalTest {
	static EightPuzzleBoard goal = new EightPuzzleBoard(new int[] { 0, 1, 2, 3, 4, 5,
			6, 7, 8 });
	
	static List<XYLocation> posicionesObjetivo = goal.getPositions();
	
	public boolean isGoalState(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		return board.equals(goal);
	}
	
	public static void setGoalState(EightPuzzleBoard board) {
		goal = board;
		posicionesObjetivo = goal.getPositions();
	}
	
	public static EightPuzzleBoard getGoalState() {
		return goal;
	}
}