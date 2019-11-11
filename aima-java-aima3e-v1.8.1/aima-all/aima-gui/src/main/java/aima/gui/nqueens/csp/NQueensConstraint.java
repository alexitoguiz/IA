package aima.gui.nqueens.csp;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

/**
 * 
 * @author Fernando Peña Bes
 *
 */

// Implementa la restricción de los NQueenss que impide que los valores de las celdas <var1> y <var2>
// sean iguales

public class NQueensConstraint implements Constraint {
	
	Variable var1;
	Variable var2;
	
	List<Variable> scope;
	
	public NQueensConstraint(Variable var1, Variable var2) {
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

	@Override // Qi != Qj && |Qi - Qj| != |i - j|
	public boolean isSatisfiedWith(Assignment assignment) {
		Object value1 = assignment.getAssignment(var1);
		Object value2 = assignment.getAssignment(var2);
		return value1 == null || !value1.equals(value2);
	}
}
