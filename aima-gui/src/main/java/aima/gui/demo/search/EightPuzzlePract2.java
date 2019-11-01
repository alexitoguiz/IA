package aima.gui.demo.search;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest2;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction2;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction2;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch2;
import aima.gui.demo.search.GenerateInitialEightPuzzleBoard;
import aima.core.util.math.Biseccion;

/**
 * @author Fernando Peña Bes (NIA: 756012)
 * 
 */

public class EightPuzzlePract2 {
	
	private static final int profundidadMax = 24;
	private static final int numExperimentos = 100;
	
	private static int nodosGenerados[] = {0, 0, 0, 0};
	// Almacenando los nodos generados por cada algoritmo en los 100 experimentos
	private static double factorRamificacionEfectivo[] = {0, 0, 0, 0};
	// Almacenando el factor de ramificación efectivo (b*) de cada algoritmo
	
	// Los vectores almacenan en posiciones diferentes los datos de cada algoritmo
	// 0 -> BFS
	// 1 -> IDS
	// 2 -> A*h(1) Missplaced Tille Heuristic
	// 3 -> A*h(2) Manhattan Heuristic
	
	static EightPuzzleBoard board = new EightPuzzleBoard();

	public static void main(String[] args) {
		System.out.println(new String(new char[91]).replace("\0", "-"));
		System.out.println("||    ||      Nodos Generados                   ||                  b*                   ||");
		System.out.println(new String(new char[91]).replace("\0", "-"));
		System.out.println("||   d||    BFS  |     IDS  |  A*h(1) |  A*h(2) ||    BFS  |    IDS  |  A*h(1) |  A*h(2) ||");
		System.out.println(new String(new char[91]).replace("\0", "-"));
		System.out.println(new String(new char[91]).replace("\0", "-"));
		
		// Generar objeto para calcular la bisección
		Biseccion bisec = new Biseccion();
		
		for (int i = 2; i <= profundidadMax; i++) {
			// Generar 100 experimientos aleatorios con la profundidad deseada
			
			// Establecer profundidad para calcular la bisección
			bisec.setDepth(i);
			
			int j = 0;
			while (j < numExperimentos) {
				// Generar tablero aleatorio
				EightPuzzleBoard inicial = GenerateInitialEightPuzzleBoard.randomIni();
				EightPuzzleBoard objetivo = GenerateInitialEightPuzzleBoard.random(i, inicial);
				EightPuzzleGoalTest2.setGoalState(objetivo);
				
				// Comprobar que la solución óptima de <inicial> hasta <objetivo> está a distancia <i>
				if (comprobarProfundidad(inicial, i)) {
					// Realizar experimento si los estados inicial y final son válidos
					nodosGenerados[0] += calcularNodosGenerados(new BreadthFirstSearch(new GraphSearch()), inicial);
					//nodosGenerados[1] += calcularNodosGenerados(new IterativeDeepeningSearch2(), inicial);
					nodosGenerados[2] += calcularNodosGenerados(
							new AStarSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction2()), inicial);
					nodosGenerados[3] += calcularNodosGenerados(
							new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction2()), inicial);
					j++;
				}
			}
			// Hacer media de nodos generados y calcular b* para cada uno de los algoritmos
			for (int k = 0; k < 4; k++) {
				if (k == 1) {}
				else {
				nodosGenerados[k] = nodosGenerados[k] / numExperimentos;
				bisec.setGeneratedNodes(nodosGenerados[k]);
				factorRamificacionEfectivo[k] = bisec.metodoDeBiseccion(1.0000001, 4.0, 0.00001);
				}
			}
			System.out.format(
				"||%4d||%7d  |%8d  |%7d  |%7d  ||%7.2f  |%7.2f  |%7.2f  |%7.2f  ||\n",i, 
				nodosGenerados[0], nodosGenerados[1], nodosGenerados[2], nodosGenerados[3],
				factorRamificacionEfectivo[0], factorRamificacionEfectivo[1], factorRamificacionEfectivo[2], factorRamificacionEfectivo[3]);
		}
		
		System.out.println(new String(new char[79]).replace("\0", "-"));
	}
	
	// Comprueba que la profundidad de la solución óptima del tablero sea igual a <distancia>
	private static Boolean comprobarProfundidad(Object tablero, int distancia) {
		int depth = 0;
		
		try {
			
			Problem problem = new Problem(tablero, EightPuzzleFunctionFactory.getActionsFunction(),
						EightPuzzleFunctionFactory.getResultFunction(), new EightPuzzleGoalTest2());
			
			SearchAgent agent = new SearchAgent(problem, 
					new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction2()));
	
			String pathcostM =agent.getInstrumentation().getProperty("pathCost");
			if (pathcostM!=null) depth = (int)Float.parseFloat(pathcostM);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return depth == distancia;
	}
	
	// Devuelve el número de nodos generados al ejecutar la búsqueda <search> desde el estado
	// inical <initialState>
	private static int calcularNodosGenerados(Search search, Object initialState) {
		int generatedNodes = 0;
		try {
			Problem problem = new Problem(initialState, EightPuzzleFunctionFactory.getActionsFunction(),
						EightPuzzleFunctionFactory.getResultFunction(), new EightPuzzleGoalTest2());
			
			SearchAgent agent = new SearchAgent(problem, search);
		
			if (agent.getInstrumentation().getProperty("nodesGenerated")!=null)
				generatedNodes = (int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesGenerated"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedNodes;
	}
}