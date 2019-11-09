package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest2;

/**
 * @author Ravi Mohan
 * @author Fernando Peña Bes
 * 
 */
public class ManhattanHeuristicFunction2 implements HeuristicFunction {

	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		int retVal = 0;
		for (int i = 1; i < 9; i++) {
			XYLocation loc = board.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(i, loc);
		}
		return retVal;

	}

	public int evaluateManhattanDistanceOf(int i, XYLocation loc) {
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();
		int xposObjetivo = EightPuzzleGoalTest2.posicionesObjetivo.get(i).getXCoOrdinate();
		int yposObjetivo = EightPuzzleGoalTest2.posicionesObjetivo.get(i).getYCoOrdinate();
		return Math.abs(xpos - xposObjetivo) + Math.abs(ypos - yposObjetivo);
	}
}