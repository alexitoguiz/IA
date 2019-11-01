package aima.core.environment.canibals;

import aima.core.search.framework.GoalTest;

/**
 * @author Fernando Peña Bes (NIA: 756012)
 * @date october 2019
 */
public class CanibalsGoalTest implements GoalTest {
	CanibalsBoard goal = new CanibalsBoard(new int[] { 0, 0, 1, 3, 3});

	public boolean isGoalState(Object state) {
		CanibalsBoard board = (CanibalsBoard) state;
		return board.equals(goal);
	}
}