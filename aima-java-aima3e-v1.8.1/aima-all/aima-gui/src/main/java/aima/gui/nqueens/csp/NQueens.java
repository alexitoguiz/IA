package aima.gui.nqueens.csp;

//import java.util.ArrayList;
//import java.util.List;
import java.util.Random;

/**
 * Represents a quadratic board with a matrix of squares on which queens can be
 * placed (only one per square) and moved.
 * 
 * @author Ravi Mohan
 * @author R. Lunde
 * @author Fernando Peña Bes
 */
public class NQueens {

	/**
	 * X---> increases left to right with zero based index Y increases top to
	 * bottom with zero based index | | V
	 */
	private int[] cols;
	private int size;
	
	public NQueens(int n) {
		size = n;
		cols = new int[size];
		for (int i = 0; i < size; i++) {
			cols[i] = 0;
		}
	}

	// Método que coloca de forma aleatorio una reina en cada columna
	public void putNRandomQueens() {
		Random r = new Random();
		for(int i = 0; i < size; i++) {
			cols[i] = r.nextInt(size);
		}
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			cols[i] = 0;
		}
	}

	public int getQueenAtCol(int col) { // Devuelve la fila en la que esta la reina de la columna <col>
		return cols[col];
	}
	
	public void moveQueenAtCol(int col, int row) { // Mueve la reina de la columna <col> a la fila <row>
		cols[col] = row;
	}

	public int getSize() {
		return size;
	}


	public void print() {
		System.out.println(getBoardPic());
	}

	public String getBoardPic() {
		StringBuffer buffer = new StringBuffer();
		for (int col = 0; (col < size); col++) { // col
			for (int row = 0; (row < size); row++) { // row
				if (cols[col] == row )
					buffer.append(" Q ");
				else
					buffer.append(" - ");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	@Override
	public String toString() {
		return getBoardPic();
	}
}