package dcv;

import java.util.ArrayList;

// Class to represent a circuit. The UI can own multiple circuit instances.
public class Circuit {
	
	// TODO: Allow multiple top level gates
	Gate top;
	Solver solver;
	ArrayList<Input> inputs;
	
	// Return whether top gate is active
	boolean isActive() {
		return top.isActive();
	}
	
	// Add a solver to the circuit. Should be invoked when a solution is requested.
	void giveSolver() {
		solver = new Solver(this);
	}
	
	void giveTop() {
		// Give options to add each gate here.
		// There's got to be some nice way to implement polymorphism.
		// We know what type of gate will be added based on the UI element selected.
	}
}
