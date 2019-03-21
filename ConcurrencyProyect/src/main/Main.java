package main;

import control.Control;
import control.QTMatrix;
import problem.Problem;

public class Main {

	public static void main(String[] args) {
		Problem problem = new Problem(Problem.PHILOSOPHERS_DINNER, Control.MONITOR,1000,100); 
		problem.start();
		 
		problem.qtmatrix.print();  
	}

}
