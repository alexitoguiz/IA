package aima.gui.sudoku.csp;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

/**
 * 
 * @author Fernando Peña Bes
 *
 */

// Implementa la restricción de los sudokus que impide que los valores de las celdas <var1> y <var2>
// sean iguales
public class SudokuConstraint implements Constraint {
	
	Variable var1;
	Variable var2;
	
	List<Variable> scope;
	
	public SudokuConstraint(Variable var1, Variable var2) {
		this.var1 = var1;
		this.var2 = var2;
		scope = new ArrayList<Variable>(2);
		scope.add(var1);
		scope.add(var2);
	}
	
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		Object value1 = assignment.getAssignment(var1);
		return value1 == null || !value1.equals(assignment.getAssignment(var2));
	}
}
