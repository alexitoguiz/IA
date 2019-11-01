package aima.core.environment.fifteenpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Ravi Mohan
 * 
 */
public class ManhattanHeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		FifteenPuzzleBoard board = (FifteenPuzzleBoard) state;
		int retVal = 0;
		for (int i = 0; i < 15; i++) {
			XYLocation loc = board.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(i, loc);
		}
		return retVal;

	}

	public int evaluateManhattanDistanceOf(int i, XYLocation loc) {
		int retVal = -1;
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();
		switch (i) {
		
		case 0:
			retVal = Math.abs(xpos - 0) + Math.abs(ypos - 0);
			break;
		case 1:
			retVal = Math.abs(xpos - 0) + Math.abs(ypos - 1);
			break;
		case 2:
			retVal = Math.abs(xpos - 0) + Math.abs(ypos - 2);
			break;
		case 3:
			retVal = Math.abs(xpos - 0) + Math.abs(ypos - 3);
			break;
		case 4:
			retVal = Math.abs(xpos - 1) + Math.abs(ypos - 0);
			break;
		case 5:
			retVal = Math.abs(xpos - 1) + Math.abs(ypos - 1);
			break;
		case 6:
			retVal = Math.abs(xpos - 1) + Math.abs(ypos - 2);
			break;
		case 7:
			retVal = Math.abs(xpos - 1) + Math.abs(ypos - 3);
			break;
		case 8:
			retVal = Math.abs(xpos - 2) + Math.abs(ypos - 0);
			break;
		case 9:
			retVal = Math.abs(xpos - 2) + Math.abs(ypos - 1);
			break;
		case 10:
			retVal = Math.abs(xpos - 2) + Math.abs(ypos - 2);
			break;
		case 11:
			retVal = Math.abs(xpos - 2) + Math.abs(ypos - 3);
			break;
		case 12:
			retVal = Math.abs(xpos - 3) + Math.abs(ypos - 0);
			break;
		case 13:
			retVal = Math.abs(xpos - 3) + Math.abs(ypos - 1);
			break;
		case 14:
			retVal = Math.abs(xpos - 3) + Math.abs(ypos - 2);
			break;
		}
		return retVal;
	}
}