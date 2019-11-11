package aima.gui.nqueens.csp;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CSPStateListener;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.MinConflictsStrategy;

import aima.gui.nqueens.csp.NQueens;
import aima.gui.nqueens.csp.NQueensProblem;

/**
 * 
 * @author Fernando Peña Bes
 *
 */

public class NQueensApp {
	
	private static final int size = 8;
	
	private static final int limMCS = 100;
	
	// Lista para guardar los NQueenss a resolver
	
	public static void main(String args[]) {
		
	
				
		//System.out.println("Número de NQueenss a resolver: " + listaNQueens.size());		
		//int solucionados = 0;
		NQueens board = new NQueens(size);
		board.putNRandomQueens();
		
		solveNQueens(new NQueensProblem(board));
		//System.out.println("++++++++++");
		//System.out.println("Número de NQueenss solucionados: " + solucionados);
	}
	
	// Intenta resolver el NQueens <NQueens> aplicando la búsqueda backtracking usando MRV, Deg, AC3, y LCV) {
	// En el caso de que consiga resolverlo, imprime la solución por pantalla y devuelve <true>. Si no, devuelve false.
	public static boolean solveNQueens(NQueensProblem problem) {
		
		MinConflictsStrategy mcs = new MinConflictsStrategy(limMCS);
		
		mcs.addCSPStateListener(new CSPStateListener() {
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
		Assignment sol = mcs.solve(problem);
		double end = System.nanoTime();
		
		System.out.println(sol);
		System.out.format("Time to solve = %.2f seconds\n", (end - start) / 1000000000);
		
		if (sol.isSolution(problem)) {
			System.out.println("SOLUCIÓN: ");
			//solucion.imprimeNQueens();
			System.out.println("NQueens solucionado correctamente");
			return true;
		}
		else {
			System.out.println("Error al resolver el NQueens");
			return false;
		}
	}
}
