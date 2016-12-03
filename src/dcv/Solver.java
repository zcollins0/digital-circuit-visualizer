package dcv;

import java.util.ArrayList;

// Class to encapsulate the solving of a circuit
// There should be one solver per circuit, and the circuit passed into the solver should be the one that owns it.
public class Solver {
	ArrayList<Input> inputs;
	Circuit circuit;

	// Constructor for Solver: takes the owning circuit object
	Solver(Circuit circuit) {
		this.circuit = circuit;
		inputs = DigitalCircuitUI.inputList;
	}

	// Solve all permutations of inputs
	public boolean[] solveAll() {
		// TODO: Multithread this
		int inputSize = inputs.size();

		boolean[] results;
		Boolean[] states = new Boolean[inputSize];

		if(inputSize!=1){
			results = new boolean[(int) Math.pow(2, (states.length))];
		}
		else {
			results = new boolean[2];
		}

		double numStates = Math.pow(2, (states.length));
		for (int i = 0; i < numStates; i++) {
			String binaryString = Integer.toBinaryString(i);
			while (binaryString.length() != inputSize) {
				binaryString = "0" + binaryString;
			}
			char[] binaryChars = binaryString.toCharArray();
			for (int j = 0; j < binaryChars.length; j++) {
				states[j] = (binaryChars[j] == '1');
			}
			results[i] = solveInstance(states);
		}

		return results;
	}

	// Solve one specific instance of the circuit
	// If the array of booleans is longer than the array of inputs, the remaining booleans are ignored
	// If the array of booleans is shorter, the remaining inputs are set to false
	boolean solveInstance(Boolean[] states) {
		int i;
		for (i = 0; i < states.length; i++) {
			if (i == inputs.size())
				break;
			inputs.get(i).setActive(states[i]);
		}
		//TODO: this doesn't work right
		if (i < inputs.size()) {
			for (; i < inputs.size(); i++) {
				inputs.get(i).setActive(false);
			}
		}

		return circuit.top.isActive();
	}
}
