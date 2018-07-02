package sudoku;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.variables.integer.IntegerVariable;

public class Sudoku {
	public void sudoku() {
		CPModel model = new CPModel();
		int n = 9; 
		int subn= 3; 
		IntegerVariable[][] cells = new IntegerVariable[n][n];
		
		for(int i= 0; i< n ; i++) {
			for(int j = 0; j < n ; j++) {
				cells[i][j] = Choco.makeIntVar(" ", 1,9); 
			}
		}
		
		//Pour chaque ligne: les nombres sont différents
		for(int i= 0 ; i< n; i++) {
			IntegerVariable[] X = new IntegerVariable[n];
			for(int j = 0; j<n ; j++) {
				X[j] = cells[i][j]; 
			}
			model.addConstraint(Choco.allDifferent(X));
		}
				
				
		
		//Pour chaque cologne: les nombres sont différents
		for(int j= 0 ; j< n; j++) {
			IntegerVariable[] Y = new IntegerVariable[n];
			for(int i = 0; i<n ; i++) {
				Y[i] = cells[i][j]; 
			}
			model.addConstraint(Choco.allDifferent(Y));
		}
		
		
		// Pour chaque sous-carré 3x3, les nombres sont différents
		
		for (int I = 0; I < subn; I++) {
	           for (int J = 0; J < subn; J++) {
	        	   IntegerVariable[] tab  = new IntegerVariable[n];
	        	   int idx = -1; 
	               for (int i  = 0; i < subn; i++) {
	            	   for(int j = 0; j<subn; j++) {
	            		   idx++; 
	            		   tab[idx] = cells[3*I+i][3*J+j]; 
	            	   }
	                 
	               }
	               model.addConstraint(Choco.allDifferent(tab));
	           }
	          
	        }
		
		
		CPSolver solver = new CPSolver();
		solver.read(model); 
		solver.solve(); 
		
		// affichage 
		for (int i = 0; i < n; i++) {
	           for (int j = 0; j < n; j++) {
	              
	              System.out.print( solver.getVar(cells[i][j]) + " ");
	           }
	           System.out.println();
	        }
		
	}
	
	
	
	public static void main(String[] args) {
		Sudoku sk = new Sudoku(); 
		sk.sudoku();
	}
}
