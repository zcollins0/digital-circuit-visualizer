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
	
	// Solve one specific instance of the circuit
	// If the array of booleans is longer than the array of inputs, the remaining booleans are ignored
	// If the array of booleans is shorter, the remaining inputs are set to false
	boolean solveInstance(boolean[] states) {
		int i;
		for (i = 0; i < states.length; i++) {
			if (i == inputs.size()) break;
			inputs.get(i).signal = states[i];
		}
		if (i < inputs.size()) {
			for (; i < inputs.size(); i++) {
				inputs.get(i).signal = false;
			}
		}
		
		return circuit.top.isActive();
	}
}
