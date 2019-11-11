package aima.gui.sudoku.csp;

import aima.core.search.csp.Variable;

/**
 * 
 * @author Fernando Peña
 *
 */

// La clase extiende Variable añadiendo datos necesarios para representar una celda de un sudoku.
// Los datos son el número de fila y columna de la celda y su valor
public class SudokuVariable extends Variable {
	
	private int x;
	private int y;
	private int value;
	
	public SudokuVariable(String name, int i, int j) {
		super(name);
		this.x = i;
		this.y = j;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setValue(int n) {
		this.value = n;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
