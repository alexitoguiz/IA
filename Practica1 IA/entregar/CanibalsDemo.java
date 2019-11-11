package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.canibals.CanibalsBoard;
import aima.core.environment.canibals.CanibalsFunctionFactory;
import aima.core.environment.canibals.CanibalsGoalTest;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.TreeSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.UniformCostSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

/**
 * @author Fernando Peña Bes (NIA: 756012)
 * @date october 2019
 */

// Ejecución del juego de Misioneros y Caníbales aplicando diferetes algoritmos de búsqueda
// no informada
public class CanibalsDemo {
	static CanibalsBoard initialBoard = new CanibalsBoard(new int[] { 3, 3, 0, 0, 0 });

	public static void main(String[] args) {
		canibalsBFS_G_Demo();
		canibalsBFS_T_Demo();

		canibalsDFS_G_Demo();
		// (tiempo) canibalsDFS_T_Demo();
		System.out.println("\nMisioneros y canibales BFS - Graph  --> ---");
		
		canibalsUCS_G_Demo();
		canibalsUCS_T_Demo();

		canibalsDLSDemo();
		canibalsIDSDemo();
	}

	private static void canibalsBFS_G_Demo() {
		System.out.println("\nMisioneros y canibales BFS - Graph  -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new BreadthFirstSearch(new GraphSearch());
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void canibalsBFS_T_Demo() {
		System.out.println("\nMisioneros y canibales BFS - Tree  -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new BreadthFirstSearch(new TreeSearch());
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void canibalsDFS_G_Demo() {
		System.out.println("\nMisioneros y canibales DFS - Graph  -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new DepthFirstSearch(new GraphSearch());
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void canibalsDFS_T_Demo() {
		System.out.println("\nMisioneros y canibales DFS - Tree  -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new DepthFirstSearch(new TreeSearch());
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void canibalsUCS_G_Demo() {
		System.out.println("\nMisioneros y canibales UCS - Graph  -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new UniformCostSearch(new GraphSearch());
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void canibalsUCS_T_Demo() {
		System.out.println("\nMisioneros y canibales UCS - Tree  -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new UniformCostSearch(new TreeSearch());
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void canibalsDLSDemo() {
		System.out.println("\nMisioneros y canibales DLS(11) -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new DepthLimitedSearch(11);
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void canibalsIDSDemo() {
		System.out.println("\nMisioneros y canibales IDS -->");
		try {
			Problem problem = new Problem(initialBoard, CanibalsFunctionFactory.getActionsFunction(),
					CanibalsFunctionFactory.getResultFunction(), new CanibalsGoalTest());
			Search search = new IterativeDeepeningSearch();
			long antes = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long despues = System.nanoTime();
			printInstrumentation(agent.getInstrumentation());
			System.out.format("%d ms\n", (despues - antes) / 1000000);
			executeActions(agent.getActions(), problem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void executeActions(List<Action> actions, Problem problem) {

		Object initialState = problem.getInitialState();
		ResultFunction resultFunction = problem.getResultFunction();
		System.out.format("%46s", "M C   B   M C\n");
		System.out.format("%46s", "-------------\n");
		Object state = initialState;
		System.out.format("%24s", "ESTADO INICIAL");
		System.out.print("\t");
		System.out.println(state);

		for (Action action : actions) {
			System.out.format("%24s", action.toString());
			System.out.print("\t");
			state = resultFunction.result(state, action);
			System.out.println(state);
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

	/*
	 * private static void printActions(List<Action> actions) { for (int i = 0; i <
	 * actions.size(); i++) { String action = actions.get(i).toString();
	 * System.out.println(action); } }
	 */
}