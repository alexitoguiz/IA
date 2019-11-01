package aima.gui.demo.search;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;

/**
 * @author Fernando Peña Bes (NIA: 756012)
 * 
 */

public class EightPuzzlePract1 {
	static EightPuzzleBoard boardWithThreeMoveSolution = new EightPuzzleBoard(
			new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });;

	static EightPuzzleBoard random1 = new EightPuzzleBoard(new int[] { 1, 4, 2,
			7, 5, 8, 3, 0, 6 });

	static EightPuzzleBoard extreme = new EightPuzzleBoard(new int[] { 0, 8, 7,
			6, 5, 4, 3, 2, 1 });

	public static void main(String[] args) {
		System.out.format("%19s|%11s|%11s|%11s|%11s|%11s\n", "Problema", "Profundidad", "Expand", "Q.Size", "MaxQS", "tiempo");
		System.out.println(new String(new char[79]).replace("\0", "-"));

		// Estado incial con solución a 3 pasos
		
		eightPuzzleSearch(new BreadthFirstSearch(new GraphSearch()), boardWithThreeMoveSolution, "BFS-G-3");
		eightPuzzleSearch(new BreadthFirstSearch(new TreeSearch()), boardWithThreeMoveSolution, "BFS-T-3");
		
		eightPuzzleSearch(new DepthFirstSearch(new GraphSearch()), boardWithThreeMoveSolution, "DFS-G-3");
		// (tiempo) eightPuzzleSearch(new DepthFirstSearch(new TreeSearch()), boardWithThreeMoveSolution, "DFS-T-3");
		
		eightPuzzleSearch(new DepthLimitedSearch(9), boardWithThreeMoveSolution, "DLS-9-3");
		eightPuzzleSearch(new DepthLimitedSearch(3), boardWithThreeMoveSolution, "DLS-3-3");
		
		eightPuzzleSearch(new IterativeDeepeningSearch(), boardWithThreeMoveSolution, "IDS-3");
		
		eightPuzzleSearch(new UniformCostSearch(new GraphSearch()), boardWithThreeMoveSolution, "UCS-G-3");
		eightPuzzleSearch(new UniformCostSearch(new TreeSearch()), boardWithThreeMoveSolution, "UCS-T-3");
		
		
		eightPuzzleSearch(new GreedyBestFirstSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction()), 
												boardWithThreeMoveSolution, "GBFS-MissTileH-G-3");
		eightPuzzleSearch(new GreedyBestFirstSearch(new GraphSearch(), new ManhattanHeuristicFunction()),
												boardWithThreeMoveSolution, "GBFS-ManhH-G-3");
		// (time) eightPuzzleSearch(new GreedyBestFirstSearch(new TreeSearch(), new MisplacedTilleHeuristicFunction()), 
		//										boardWithThreeMoveSolution, "GBFS-MissTileH-T-3");
		eightPuzzleSearch(new GreedyBestFirstSearch(new TreeSearch(), new ManhattanHeuristicFunction()),
												boardWithThreeMoveSolution, "GBFS-ManhH-T-3");
		
		
		eightPuzzleSearch(new AStarSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction()), 
												boardWithThreeMoveSolution, "A*-MissTileH-G-3");
		eightPuzzleSearch(new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction()),
												boardWithThreeMoveSolution, "A*-ManhH-G-3");
		eightPuzzleSearch(new AStarSearch(new TreeSearch(), new MisplacedTilleHeuristicFunction()), 
												boardWithThreeMoveSolution, "A*-MissTileH-T-3");
		eightPuzzleSearch(new AStarSearch(new TreeSearch(), new ManhattanHeuristicFunction()),
												boardWithThreeMoveSolution, "A*-ManhH-T-3");
		
		
		
		// Estado inicial con solución a 9 pasos
		
		eightPuzzleSearch(new BreadthFirstSearch(new GraphSearch()), random1, "BFS-G-9");
		eightPuzzleSearch(new BreadthFirstSearch(new TreeSearch()), random1, "BFS-T-9");
		
		eightPuzzleSearch(new DepthFirstSearch(new GraphSearch()), random1, "DFS-G-9");
		// (tiempo) eightPuzzleSearch(new DepthFirstSearch(new TreeSearch()), random1, "DFS-T-9");
		
		eightPuzzleSearch(new DepthLimitedSearch(9), random1, "DLS-9-9");
		eightPuzzleSearch(new DepthLimitedSearch(3), random1, "DLS-3-9");
		
		eightPuzzleSearch(new IterativeDeepeningSearch(), random1, "IDS-9");
		
		eightPuzzleSearch(new UniformCostSearch(new GraphSearch()), random1, "UCS-G-9");
		eightPuzzleSearch(new UniformCostSearch(new TreeSearch()), random1, "UCS-T-9");
		
		eightPuzzleSearch(new GreedyBestFirstSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction()), 
						random1, "GBFS-MissTileH-G-9");
		eightPuzzleSearch(new GreedyBestFirstSearch(new GraphSearch(), new ManhattanHeuristicFunction()),
						random1, "GBFS-ManhH-G-9");
		// eightPuzzleSearch(new GreedyBestFirstSearch(new TreeSearch(), new MisplacedTilleHeuristicFunction()), 
		//				random1, "GBFS-MissTileH-T-9");
		eightPuzzleSearch(new GreedyBestFirstSearch(new TreeSearch(), new ManhattanHeuristicFunction()),
						random1, "GBFS-ManhH-T-9");
		
		
		eightPuzzleSearch(new AStarSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction()), 
						random1, "A*-MissTileH-G-9");
		eightPuzzleSearch(new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction()),
						random1, "A*-ManhH-G-9");
		eightPuzzleSearch(new AStarSearch(new TreeSearch(), new MisplacedTilleHeuristicFunction()), 
						random1, "A*-MissTileH-T-9");
		eightPuzzleSearch(new AStarSearch(new TreeSearch(), new ManhattanHeuristicFunction()),
						random1, "A*-ManhH-T-9");

		
		
		// Estado inicial con solución a 30 pasos
		
		eightPuzzleSearch(new BreadthFirstSearch(new GraphSearch()), extreme, "BFS-G-30");
		// (espacio) eightPuzzleSearch(new BreadthFirstSearch(new TreeSearch()), extreme, "BFS-T-30");
		
		eightPuzzleSearch(new DepthFirstSearch(new GraphSearch()), extreme, "DFS-G-30");
		// (tiempo) eightPuzzleSearch(new DepthFirstSearch(new TreeSearch()), extreme, "DFS-T-30");
		
		eightPuzzleSearch(new DepthLimitedSearch(9), extreme, "DLS-9-30");
		eightPuzzleSearch(new DepthLimitedSearch(3), extreme, "DLS-3-30");
		
		// (tiempo) eightPuzzleSearch(new IterativeDeepeningSearch(), extreme, "IDS-30");
		
		eightPuzzleSearch(new UniformCostSearch(new GraphSearch()), extreme, "UCS-G-30");
		// (tiempo) eightPuzzleSearch(new UniformCostSearch(new TreeSearch()), extreme, "UCS-T-30");
		
		eightPuzzleSearch(new GreedyBestFirstSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction()), 
						extreme, "GBFS-MissTileH-G-30");
		eightPuzzleSearch(new GreedyBestFirstSearch(new GraphSearch(), new ManhattanHeuristicFunction()),
						extreme, "GBFS-ManhH-G-30");
		// (time) eightPuzzleSearch(new GreedyBestFirstSearch(new TreeSearch(), new MisplacedTilleHeuristicFunction()), 
		//				extreme, "GBFS-MissTileH-T-30");
		// (time) eightPuzzleSearch(new GreedyBestFirstSearch(new TreeSearch(), new ManhattanHeuristicFunction()),
		//				extreme, "GBFS-ManhH-T-30");
		
		
		eightPuzzleSearch(new AStarSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction()), 
						extreme, "A*-MissTileH-G-30");
		eightPuzzleSearch(new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction()),
						extreme, "A*-ManhH-G-30");
		//eightPuzzleSearch(new AStarSearch(new TreeSearch(), new MisplacedTilleHeuristicFunction()), 
		//				extreme, "A*-MissTileH-T-30");
		//eightPuzzleSearch(new AStarSearch(new TreeSearch(), new ManhattanHeuristicFunction()),
		//				extreme, "A*-ManhH-T-30");

	}
	
	// Función a la que se le pasa el tipo de búsqueda y el estado inicial. Ejecuta la búsqueda con el estado
	// y inicial definido y muestra las diferentes métricas de de la búsqueda (nodos expandidos, 
	// profundidad de la solución, tiempo de ejecución, tamaño de la frontera y tamaño máximo de la frontera)
	private static void eightPuzzleSearch(Search search, Object initialState, String testName) {
		try {
			Problem problem = new Problem(initialState, EightPuzzleFunctionFactory.getActionsFunction(),
						EightPuzzleFunctionFactory.getResultFunction(), new EightPuzzleGoalTest());
			
			long start = System.nanoTime();
			SearchAgent agent = new SearchAgent(problem, search);
			long finish = System.nanoTime();
			
			int depth, expandedNodes, queueSize, maxQueueSize;
					
			String pathcostM =agent.getInstrumentation().getProperty("pathCost");
			if (pathcostM!=null) depth = (int)Float.parseFloat(pathcostM);
			else depth = 0;
			if (agent.getInstrumentation().getProperty("nodesExpanded")==null) expandedNodes= 0; 
			else expandedNodes =
						(int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesExpanded"));
			if (agent.getInstrumentation().getProperty("queueSize")==null) queueSize=0;
			else queueSize = (int)Float.parseFloat(agent.getInstrumentation().getProperty("queueSize"));
			if (agent.getInstrumentation().getProperty("maxQueueSize")==null) maxQueueSize= 0;
			else maxQueueSize =
					(int)Float.parseFloat(agent.getInstrumentation().getProperty("maxQueueSize"));
			
			System.out.format("%19s|%11d|%11d|%11d|%11d|%8d ms\n", testName, depth, expandedNodes, queueSize, maxQueueSize, 
																	(finish-start)/1000000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}