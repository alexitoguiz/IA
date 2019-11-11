package aima.core.environment.canibals;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 * @author Fernando Peña Bes (NIA: 756012)
 * @date october 2019
 */
public class CanibalsFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			CanibalsBoard board = (CanibalsBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.canMovePerson(CanibalsBoard.M1C)) {
				actions.add(CanibalsBoard.M1C);
			}
			if (board.canMovePerson(CanibalsBoard.M2C)) {
				actions.add(CanibalsBoard.M2C);
			}
			if (board.canMovePerson(CanibalsBoard.M1M)) {
				actions.add(CanibalsBoard.M1M);
			}
			if (board.canMovePerson(CanibalsBoard.M2M)) {
				actions.add(CanibalsBoard.M2M);
			}
			if (board.canMovePerson(CanibalsBoard.M1M1C)) {
				actions.add(CanibalsBoard.M1M1C);
			}

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			CanibalsBoard board = (CanibalsBoard) s;

			if (CanibalsBoard.M1C.equals(a)
					&& board.canMovePerson(CanibalsBoard.M1C)) {
				CanibalsBoard newBoard = new CanibalsBoard(board);
				newBoard.move1C();
				return newBoard;
			} else if (CanibalsBoard.M2C.equals(a)
					&& board.canMovePerson(CanibalsBoard.M2C)) {
				CanibalsBoard newBoard = new CanibalsBoard(board);
				newBoard.move2C();
				return newBoard;
			}else if (CanibalsBoard.M1M.equals(a)
					&& board.canMovePerson(CanibalsBoard.M1M)) {
				CanibalsBoard newBoard = new CanibalsBoard(board);
				newBoard.move1M();
				return newBoard;
			} else if (CanibalsBoard.M2M.equals(a)
					&& board.canMovePerson(CanibalsBoard.M2M)) {
				CanibalsBoard newBoard = new CanibalsBoard(board);
				newBoard.move2M();
				return newBoard;
			} else if (CanibalsBoard.M1M1C.equals(a) && board.canMovePerson(CanibalsBoard.M1M1C)) {
				CanibalsBoard newBoard = new CanibalsBoard(board);
				newBoard.move1M1C();
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}