package dcv;

import java.util.ArrayList;

public class Solver {
	ArrayList<Input> inputs;
	Circuit circuit;
	
	Solver(ArrayList<Input> inputs, Circuit circuit) {
		this.inputs = inputs;
		this.circuit = circuit;
	}
	
	void solveAll() {
		// Do all possible scenarios
		// This part should use multithreading
	}
}
