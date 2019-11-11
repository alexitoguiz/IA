package aima.gui.demo.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aima.core.environment.nqueens.AttackingPairsHeuristic;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFitnessFunction;
import aima.core.environment.nqueens.NQueensFunctionFactory;
import aima.core.environment.nqueens.NQueensGoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.HillClimbingSearch;
import aima.core.search.local.Individual;
import aima.core.search.local.SimulatedAnnealingSearch;

import aima.core.search.local.Scheduler;

/**
 * @author Ravi Mohan
 * @author Fernando Peña Bes
 * 
 */

public class NQueensLocal {

	private static final int _boardSize = 8;
	private static final int numExperimentsHC = 10000;
	private static final int numExperimentsSA = 1000;
	
	private static int numFallos;
	private static int costeTotalFallos;
	private static int numExitos;
	private static int costeTotalExitos;

	private static int numIntentos;

	private static List<NQueensBoard> tablerosUsados;

	// Parámetros enfriamiento simulado
	//////////////////////////////////////////////////
	private static final int k = 10; // velocidad que tarda en comenzar a decrecer la temperatura
	private static final double lam = 0.001; // como de rápido desciende la temperatura
	private static final int limit = 300; // número máximo de iteraciones
	//////////////////////////////////////////////////

	// Parámetros iniciales algoritmo genético
	//////////////////////////////////////////////////
	private static final int sizePoblacion = 2;
	private static final double probMutacion = 0.2;
	//////////////////////////////////////////////////

	public static void main(String[] args) {

		nQueensHillClimbingSearch_Statistics(numExperimentsHC);
		//nQueensRandomRestartHillClimbing();
		
			//nQueensRestartHillClimbingSearch_Test(10000);

		//nQueensSimulatedAnnealing_Statistics(numExperimentsSA);
		//nQueensSimulatedAnnealingRestart();

		//nQueensGeneticAlgorithmSearch();
	
	}
	
	
	//////////////////////////////
	/////  Hill - Climbing  //////
	//////////////////////////////

	// Realiza numExperiments ejecuciones del algoritmo HillClimbing para el
	// problema de las nReinas desde tableros
	// aleatorios y diferentes. Una vez realizadas, calcula estadísticas sobre el
	// procentaje de éxitos y fallos, la
	// media de pasos al fallar y la media de pasos en éxito.
	private static void nQueensHillClimbingSearch_Statistics(int numExperiments) {
		System.out.println("\nNQueens HillClimbing con " + numExperiments + " estados iniciales diferentes  -->");
		numFallos = 0;
		numExitos = 0;
		costeTotalFallos = 0;
		costeTotalExitos = 0;

		tablerosUsados = new ArrayList<NQueensBoard>();

		for (int i = 0; i < numExperiments; i++) {
			nQueensHillClimbingSearch();
		}

		System.out.format("Fallos: %.2f\n", (double) numFallos / numExperiments * 100);
		System.out.format("Coste medio fallos: %.2f\n", (double) costeTotalFallos / numFallos);
		System.out.format("Exitos: %.2f\n", (double) numExitos / numExperiments * 100);
		System.out.format("Coste medio exitos: %.2f\n", (double) costeTotalExitos / numExitos);

	}

	// Intenta resolver el problema de las nReinas para un tablero inicial aleatorio
	// aplicando el algoritmo HillClimbling
	private static void nQueensHillClimbingSearch() {
		try {

			NQueensBoard randomBoard = generateUnusedRandomBoard();

			Problem problem = new Problem(randomBoard, NQueensFunctionFactory.getCActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
			HillClimbingSearch search = new HillClimbingSearch(new AttackingPairsHeuristic());
			SearchAgent agent = new SearchAgent(problem, search);

			int expandedNodes = 0;
			if (agent.getInstrumentation().getProperty("nodesExpanded") != null)
				expandedNodes = (int) Float.parseFloat(agent.getInstrumentation().getProperty("nodesExpanded"));

			if (search.getOutcome() == HillClimbingSearch.SearchOutcome.SOLUTION_FOUND) {
				numExitos++;
				costeTotalExitos += expandedNodes - 1;
			} else {
				numFallos++;
				costeTotalFallos += expandedNodes - 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Resuelve el problema de las nReinas aplicando el algoritmo RandomRestart y
	// muestra estadísticas de la ejecución
	private static void nQueensRandomRestartHillClimbing() {
		System.out.println("\nNQueens RestartHillClimbing -->");
		try {
			int expandedNodes = 0;
			boolean exito = false;

			numFallos = 0;
			numExitos = 0;
			costeTotalFallos = 0;
			costeTotalExitos = 0;
			numIntentos = 0;

			tablerosUsados = new ArrayList<NQueensBoard>();

			while (exito == false) {

				// nuevo intento
				numIntentos++;

				Problem problem = new Problem(generateUnusedRandomBoard(), NQueensFunctionFactory.getCActionsFunction(),
						NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
				HillClimbingSearch search = new HillClimbingSearch(new AttackingPairsHeuristic());
				SearchAgent agent = new SearchAgent(problem, search);

				if (agent.getInstrumentation().getProperty("nodesExpanded") != null)
					expandedNodes = (int) Float.parseFloat(agent.getInstrumentation().getProperty("nodesExpanded"));

				exito = search.getOutcome() == HillClimbingSearch.SearchOutcome.SOLUTION_FOUND;

				if (exito) {
					numExitos++;
					costeTotalExitos += expandedNodes - 1;
					System.out.println("Search Outcome=" + search.getOutcome());
					System.out.println("Final State=\n" + search.getLastSearchState());

					System.out.format("Numero de intentos: %d\n", numIntentos);
					System.out.format("Fallos: %d\n", numFallos);
					System.out.format("Coste medio fallos: %.2f\n", (double) costeTotalFallos / numFallos);
					System.out.format("Coste exito: %d\n", expandedNodes);
					System.out.format("Coste medio exito: %.2f\n", (double) costeTotalFallos + costeTotalExitos);

				} else {
					numFallos++;
					costeTotalFallos += expandedNodes - 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void nQueensRestartHillClimbingSearch_Test(int numExperiments) {
		System.out.println("\nNQueens HillClimbing Restart Test con " + numExperiments + " estados iniciales diferentes  -->");
		numFallos = 0;
		numExitos = 0;
		costeTotalFallos = 0;
		costeTotalExitos = 0;
		
		long reintentosTotales = 0;
		
		long costeTotal = 0;

		tablerosUsados = new ArrayList<NQueensBoard>();

		for (int i = 0; i < numExperiments; i++) {
			nQueensRandomRestartHillClimbing();
			reintentosTotales += numIntentos;
			costeTotal += costeTotalFallos + costeTotalExitos;
		}
		System.out.format("\nCoste medio: %.2f\n", (double) costeTotal / numExperiments);
		System.out.format("Media reintentos: %.2f\n", (double) reintentosTotales / numExperiments);

	}
	
	
	
	//////////////////////////////////
	/////  Simulated Annealing  //////
	//////////////////////////////////

	// Realiza numExperiments ejecuciones del algoritmo Simulated Annealing para el
	// problema de las nReinas desde tableros
	// aleatorios y diferentes. Una vez realizadas, calcula estadísticas sobre el
	// procentaje de éxitos y fallos, la
	// media de pasos al fallar y la media de pasos en éxito.
	private static void nQueensSimulatedAnnealing_Statistics(int numExperiments) {
		System.out
				.println("\nNQueens Simulated Annealing con " + numExperiments + " estados iniciales diferentes  -->");

		numFallos = 0;
		numExitos = 0;
		costeTotalFallos = 0;
		costeTotalExitos = 0;

		tablerosUsados = new ArrayList<NQueensBoard>();

		for (int i = 0; i < numExperiments; i++) {
			nQueensSimulatedAnnealingSearch();
		}

		System.out.format("Fallos: %.2f\n", (double) numFallos / numExperiments * 100);
		System.out.format("Coste medio fallos: %.2f\n", (double) costeTotalFallos / numFallos);
		System.out.format("Exitos: %.2f\n", (double) numExitos / numExperiments * 100);
		System.out.format("Coste medio exitos: %.2f\n", (double) costeTotalExitos / numExitos);
	}

	// Intenta resolver el problema de las nReinas para un tablero inicial aleatorio
	// aplicando el algoritmo SimulatedAnnealing
	private static void nQueensSimulatedAnnealingSearch() {
		try {
			Scheduler sched = new Scheduler(k, lam, limit);

			Problem problem = new Problem(generateUnusedRandomBoard(), NQueensFunctionFactory.getCActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(new AttackingPairsHeuristic(), sched);
			SearchAgent agent = new SearchAgent(problem, search);

			int expandedNodes = 0;

			if (agent.getInstrumentation().getProperty("nodesGenerated") != null)
				expandedNodes = (int) Float.parseFloat(agent.getInstrumentation().getProperty("nodesGenerated"));

			if (search.getOutcome() == SimulatedAnnealingSearch.SearchOutcome.SOLUTION_FOUND) {
				numExitos++;
				costeTotalExitos += expandedNodes - 1;
			} else {
				numFallos++;
				costeTotalFallos += expandedNodes - 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Resuelve el problema de las nReinas aplicando el algoritmo RandomRestart y
	// muestra estadísticas de la ejecución
	private static void nQueensSimulatedAnnealingRestart() {
		System.out.println("\nNQueens RestartHillClimbing -->");
		try {
			int expandedNodes = 0;
			boolean exito = false;

			numFallos = 0;
			numExitos = 0;
			costeTotalFallos = 0;
			costeTotalExitos = 0;
			numIntentos = 0;

			tablerosUsados = new ArrayList<NQueensBoard>();

			while (exito == false) {

				// nuevo intento
				numIntentos++;

				Scheduler sched = new Scheduler(k, lam, limit);

				Problem problem = new Problem(generateUnusedRandomBoard(), NQueensFunctionFactory.getCActionsFunction(),
						NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
				SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(new AttackingPairsHeuristic(), sched);
				SearchAgent agent = new SearchAgent(problem, search);

				if (agent.getInstrumentation().getProperty("nodesExpanded") != null)
					expandedNodes = (int) Float.parseFloat(agent.getInstrumentation().getProperty("nodesExpanded"));

				exito = search.getOutcome() == SimulatedAnnealingSearch.SearchOutcome.SOLUTION_FOUND;

				if (exito) {
					numExitos++;
					costeTotalExitos += expandedNodes - 1;
					System.out.println("Search Outcome=" + search.getOutcome());
					System.out.println("Final State=\n" + search.getLastSearchState());

					System.out.format("Numero de intentos: %d\n", numIntentos);
					System.out.format("Fallos: %d\n", numFallos);
					System.out.format("Coste exito: %d\n", expandedNodes);

				} else {
					numFallos++;
					costeTotalFallos += expandedNodes - 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	////////////////////////////////
	/////  Genetic Algorithm  //////
	////////////////////////////////

	// Resuelve el problema de las nReinas aplicando algoritmo genético
	public static void nQueensGeneticAlgorithmSearch() {
		System.out.println("\nNQueens GeneticAlgorithm  -->");
		try {

			NQueensFitnessFunction fitnessFunction = new NQueensFitnessFunction();
			// Generate an initial population
			Set<Individual<Integer>> population = new HashSet<Individual<Integer>>();
			for (int i = 0; i < sizePoblacion; i++) {
				population.add(fitnessFunction.generateRandomIndividual(_boardSize));
			}

			GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<Integer>(_boardSize,
					fitnessFunction.getFiniteAlphabetForBoardOfSize(_boardSize), probMutacion);

			// Run till goal is archieved
			Individual<Integer> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, fitnessFunction, 0L);

			System.out.println(
					"Parámetros iniciales:	Población: " + sizePoblacion + ", Probabilidad mutación: " + probMutacion);

			System.out.println("Mejor individuo=\n" + fitnessFunction.getBoardForIndividual(bestIndividual));
			System.out.println("Tamaño tablero      = " + _boardSize);
			System.out.println("Fitness             = " + fitnessFunction.getValue(bestIndividual));
			System.out.println("Es Objetivo         = " + fitnessFunction.isGoalState(bestIndividual));
			System.out.println("Tamaño de población = " + ga.getPopulationSize());
			System.out.println("Iteraciones         = " + ga.getIterations());
			System.out.println("Tiempo              = " + ga.getTimeInMilliseconds() + " ms.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	// Genera un tablero con reinas en posiciones aleatorias (una por columna) que
	// no esté guardado en tablerosUsados
	private static NQueensBoard generateUnusedRandomBoard() {

		NQueensBoard randomBoard = new NQueensBoard(_boardSize);

		// generar tablero random
		randomBoard.putNRandomQueens();
		
		// comprobar que el tablero no se haya usado ya
		while (tablerosUsados.contains(randomBoard)) {
			randomBoard.clear();
			randomBoard.putNRandomQueens();
		}

		tablerosUsados.add(randomBoard);
		

		return randomBoard;
	}
}