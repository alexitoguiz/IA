package aima.gui.sudoku.csp;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CSPStateListener;
import aima.core.search.csp.ImprovedBacktrackingStrategy;

import aima.gui.sudoku.csp.SudokuProblem;
import aima.gui.sudoku.csp.Sudoku;

/**
 * 
 * @author Fernando Peña Bes
 *
 */

public class SudokuApp {
	
	// Lista para guardar los sudokus a resolver
	private static List<Sudoku> listaSudokus;

	
	public static void main(String args[]) {
		listaSudokus = new ArrayList<Sudoku>();
		listaSudokus.addAll(Arrays.asList(Sudoku.listaSudokus2("src/main/java/aima/gui/sudoku/csp/easy50.txt")));
		listaSudokus.addAll(Arrays.asList(Sudoku.listaSudokus2("src/main/java/aima/gui/sudoku/csp/top95.txt")));
		listaSudokus.addAll(Arrays.asList(Sudoku.listaSudokus2("src/main/java/aima/gui/sudoku/csp/hardest.txt")));
				
		System.out.println("Número de sudokus a resolver: " + listaSudokus.size());		
		int solucionados = 0;
		
		for (Sudoku s : listaSudokus) {
			System.out.println("---------");
			if (solveSudoku(s)) {
				solucionados++;
			}
		}
		System.out.println("++++++++++");
		System.out.println("Número de sudokus solucionados: " + solucionados);
	}
	
	// Intenta resolver el sudoku <sudoku> aplicando la búsqueda backtracking usando MRV, Deg, AC3, y LCV) {
	// En el caso de que consiga resolverlo, imprime la solución por pantalla y devuelve <true>. Si no, devuelve false.
	public static boolean solveSudoku(Sudoku sudoku) {
		sudoku.imprimeSudoku();
		if (sudoku.completo()) {
			if (sudoku.correcto()) {
				System.out.println("SUDOKU COMPLETO Y CORRECTO");
				return true;
			}
			else {
				System.out.println("SUDOKU COMPLETO E INCORRECTO");
				return false;
			}
		}
		else {
			System.out.println("SUDOKU INCOMPLETO - Resolviendo");
		}
		
		SudokuProblem problema = new SudokuProblem(sudoku.pack_celdasAsignadas());
		ImprovedBacktrackingStrategy bts = new ImprovedBacktrackingStrategy(true, true, true, true);
		bts.addCSPStateListener(new CSPStateListener() {
			@ Override
			public void stateChanged(Assignment assignment, CSP csp) {
				//System.out.println("Assigment evolved : " + assignment);
			}
			@ Override
			public void stateChanged(CSP csp) {
				//System.out.println("CSP evolved : " + csp);
			}
		});
		
		double start = System.nanoTime();
		Assignment sol = bts.solve(problema);
		double end = System.nanoTime();
		
		System.out.println(sol);
		System.out.format("Time to solve = %.2f seconds\n", (end - start) / 1000000000);
		
		if (sol.isSolution(problema)) {
			System.out.println("SOLUCIÓN: ");
			new Sudoku(sol).imprimeSudoku();
			System.out.println("Sudoku solucionado correctamente");
			return true;
		}
		else {
			System.out.println("Error al resolver el sudoku");
			return false;
		}
	}
}
