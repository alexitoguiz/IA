package aima.gui.nqueens.csp;

import java.util.ArrayList;
import java.util.List;
import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;

public class NQueensProblem extends CSP {
 
     private static final int size = 8;
     
     private static List<Variable> variables = null;

     /**
      *
      * @return Devuelve la lista de variables del tablero de nQueens.
      *         Nombre Reina en columna [i], y número de columna i
      */
     private static List<Variable> collectVariables() {
          variables = new ArrayList<Variable>();
          for (int i = 0; i < size; i++) {
        	  variables.add(new NQueensVariable("Reina en columna [" + i + "]", i));
          }
          return variables;
     }
     /**
      *
      * @param var variable nQueens
      * @return Dominio de la variable,
      *         si tiene valor el domio es el valor. Sino el domino 0 - (size - 1)
      */
     private static List<Integer> getNQueensDomain(NQueensVariable var) {
          List<Integer> list = new ArrayList<Integer>();
          if (var.getValue() != 0) {
               	list.add(new Integer(var.getValue()));
               	return list;
          } else
        	  for (int i = 0; i < size; i++)
        	  	list.add(new Integer(i));
          return list;
     }
     
     

    
     /**
      * Define como un CSP. Define variables, sus dominios y restricciones.
      * @param pack
      */
     public NQueensProblem(NQueens board) {
    	     //variables
          super(collectVariables());
          initialize(board);
          for (int i = 0; i < size; i++) {
               //NQueensVariable x = (NQueensVariable) variables.get(i);
          }
          //Define dominios de variables
          Domain domain;
          for (Variable var : getVariables()) {
               domain = new Domain(getNQueensDomain((NQueensVariable) var));
               setDomain(var, domain);
          }
          //restricciones
          doConstraint();
     }
     
     public NQueensProblem() {
	     //variables
      super(collectVariables());
      
      for (int i = 0; i < size; i++) {
           //NQueensVariable x = (NQueensVariable) variables.get(i);
      }
      //Define dominios de variables
      Domain domain;
      for (Variable var : getVariables()) {
           domain = new Domain(getNQueensDomain((NQueensVariable) var));
           setDomain(var, domain);
      }
      //restricciones
      doConstraint();
 }
     
     
     
     /**
      * Inicializa las variables a partir del tablero dispnible con
      * las 8 reinas colocadas
      * @param pack
      */
     private void initialize(NQueens board) {
          for (int i = 0; i < size; i++) {
               NQueensVariable var1 = (NQueensVariable) variables.get(i);
               var1.setValue(board.getQueenAtCol(i));
          }
     }
     
     
     // Crea restricciones entre todas las columnas del tablero
     //
     private void doConstraint() {
    	 for (int i = 0; i < size; i++) {
    		 for (int j = 0; j < size; j++) {
    			 if (i != j) {
    				 addConstraint(new NQueensConstraint(variables.get(i), variables.get(j)));
    			 }
    		 }
     	}	
     }
}
