package dcv;

import java.util.ArrayList;

// Class to represent a circuit. The UI can own multiple circuit instances.
public class Circuit {
	
	// TODO: Allow multiple top level gates
	Gate top;
	Solver solver;
	ArrayList<Input> inputs;
	
	// Return whether top gate is active
	public boolean isActive() {
		return top.isActive();
	}
	
	// Add a solver to the circuit. Should be invoked when a solution is requested.
	public void giveSolver() {
		solver = new Solver(this);
	}
	
	// Set the top level gate. This should be invoked when the first gate is created.
	public void setTop(Gate g) {
		top = g;
	}
}
