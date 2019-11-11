package aima.gui.nqueens.csp;

import aima.core.search.csp.Variable;

/**
 * 
 * @author Fernando Peña
 *
 */

// La clase extiende Variable añadiendo datos necesarios para representar una celda de un NQueens.
// Los datos son el número de fila y columna de la celda y su valor
public class NQueensVariable extends Variable {
	
	private int col;
	
	private int value;
	
	public NQueensVariable(String name, int col) {
		super(name);
		this.col = col;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setValue(int n) {
		this.value = n;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
