package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest2;

/**
 * @author Ravi Mohan
 * @author Fernando Peña
 * 
 */
public class MisplacedTilleHeuristicFunction2 implements HeuristicFunction {

	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		return getNumberOfMisplacedTiles(board);
	}

	private int getNumberOfMisplacedTiles(EightPuzzleBoard board) {
		int numberOfMisplacedTiles = 0;
		if (!(board.getLocationOf(0).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(0)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(1).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(1)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(2).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(2)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(3).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(3)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(4).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(4)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(5).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(5)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(6).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(6)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(7).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(7)))) {
			numberOfMisplacedTiles++;
		}
		if (!(board.getLocationOf(8).equals(EightPuzzleGoalTest2.posicionesObjetivo.get(8)))) {
			numberOfMisplacedTiles++;
		}
		// Subtract the gap position from the # of misplaced tiles
		// as its not actually a tile (see issue 73).
		if (numberOfMisplacedTiles > 0) {
			numberOfMisplacedTiles--;
		}
		return numberOfMisplacedTiles;
	}
}