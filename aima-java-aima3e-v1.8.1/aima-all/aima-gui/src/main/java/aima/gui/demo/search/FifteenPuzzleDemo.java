package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.fifteenpuzzle.FifteenPuzzleBoard;
import aima.core.environment.fifteenpuzzle.FifteenPuzzleFunctionFactory;
import aima.core.environment.fifteenpuzzle.FifteenPuzzleGoalTest;
import aima.core.environment.fifteenpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.fifteenpuzzle.MisplacedTilleHeuristicFunction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

/**
 * @author Ravi Mohan
 * @author Fernando Peña Bes
 * 
 */

public class FifteenPuzzleDemo {
	static FifteenPuzzleBoard boardWithThreeMoveSolution = new FifteenPuzzleBoard(
			new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 12, 13, 14, 11, 15});;

	static FifteenPuzzleBoard random1 = new FifteenPuzzleBoard(
			new int[] { 1, 0, 2, 3, 5, 7, 8, 4, 9, 6, 10, 12, 13, 14, 11, 15 });

	static FifteenPuzzleBoard extreme = new FifteenPuzzleBoard(
			new int[] { 1, 0, 2, 3, 5, 7, 4, 6, 14, 8, 10, 12, 9, 13, 11, 15 });

	public static void main(String[] args) {
		fifteenPuzzleDLSDemo();
		fifteenPuzzleIDLSDemo();
		fifteenPuzzleGreedyBestFirstDemo(); // (tiempo)
		fifteenPuzzleGreedyBestFirstManhattanDemo(); // (tiempo)
		fifteenPuzzleAStarDemo();
		fifteenPuzzleAStarManhattanDemo();
		fifteenPuzzleSimulatedAnnealingDemo();
	}

	private static void fifteenPuzzleDLSDemo() {
		System.out.println("\nFifteenPuzzleDemo recursive DLS (9) -->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution, FifteenPuzzleFunctionFactory
					.getActionsFunction(), FifteenPuzzleFunctionFactory
					.getResultFunction(), new FifteenPuzzleGoalTest());
			Search search = new DepthLimitedSearch(9);
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fifteenPuzzleIDLSDemo() {
		System.out.println("\nFifteenPuzzleDemo Iterative DLS -->");
		try {
			Problem problem = new Problem(random1, FifteenPuzzleFunctionFactory
					.getActionsFunction(), FifteenPuzzleFunctionFactory
					.getResultFunction(), new FifteenPuzzleGoalTest());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fifteenPuzzleGreedyBestFirstDemo() {
		System.out
				.println("\nFifteenPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)-->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution,
					FifteenPuzzleFunctionFactory.getActionsFunction(),
					FifteenPuzzleFunctionFactory.getResultFunction(),
					new FifteenPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(),
					new MisplacedTilleHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fifteenPuzzleGreedyBestFirstManhattanDemo() {
		System.out
				.println("\nFifteenPuzzleDemo Greedy Best First Search (ManhattanHeursitic)-->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution,
					FifteenPuzzleFunctionFactory.getActionsFunction(),
					FifteenPuzzleFunctionFactory.getResultFunction(),
					new FifteenPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(),
					new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fifteenPuzzleAStarDemo() {
		System.out
				.println("\nFifteenPuzzleDemo AStar Search (MisplacedTileHeursitic)-->");
		try {
			Problem problem = new Problem(random1, FifteenPuzzleFunctionFactory
					.getActionsFunction(), FifteenPuzzleFunctionFactory
					.getResultFunction(), new FifteenPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(),
					new MisplacedTilleHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fifteenPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nFifteenPuzzleDemo Simulated Annealing  Search -->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution, FifteenPuzzleFunctionFactory
					.getActionsFunction(), FifteenPuzzleFunctionFactory
					.getResultFunction(), new FifteenPuzzleGoalTest());
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(
					new MisplacedTilleHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fifteenPuzzleAStarManhattanDemo() {
		System.out
				.println("\nFifteenPuzzleDemo AStar Search (ManhattanHeursitic)-->");
		try {
			Problem problem = new Problem(random1, FifteenPuzzleFunctionFactory
					.getActionsFunction(), FifteenPuzzleFunctionFactory
					.getResultFunction(), new FifteenPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(),
					new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

}