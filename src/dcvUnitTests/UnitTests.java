package dcvUnitTests;

import dcv.*;

import java.util.ArrayList;
import java.util.Arrays;

public class UnitTests {
	
	static dcv.Circuit circuit;
	static Input top = new Input('A');
	static Input bottom = new Input('B');

	public static void main(String[] args) {	

		circuit = new dcv.Circuit();
		
		// Tests for each type of gate
		ANDTest();
		ORTest();
		NANDTest();
		NORTest();
		XORTest();
		NOTTest();
		
		// Test for nested gates
		NestedGateTest();
		
		// Tests for exceptions
		InputChildTest();
		NOTSecondChildTest();
		GateThirdChildTest();
		
		// Test for the solveAll method
		SolveAllTest();
	}
	
	// Test for AND Gate
	static void ANDTest() {
		AND gate = new AND(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, false, false, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for OR Gate
	static void ORTest() {
		OR gate = new OR(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, true, true, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for NAND Gate
	static void NANDTest() {
		NAND gate = new NAND(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {true, true, true, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for NOR Gate
	static void NORTest() {
		NOR gate = new NOR(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {true, false, false, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for XOR Gate
	static void XORTest() {
		XOR gate = new XOR(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, true, true, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for NOT Gate (inverter)
	static void NOTTest() {
		NOT gate = new NOT(top, null);
		top.setActive(true);
		if (gate.isActive()) {
			Thread.dumpStack();
		}
		top.setActive(false);
		if (!gate.isActive()) {
			Thread.dumpStack();
		}
	}
	
	// Runs a test on a 2-input gate
	static boolean[] runTest(Gate g) {
		boolean[] results = new boolean[4];
		
		top.setActive(false);
		bottom.setActive(false);
		results[0] = g.isActive();
		
		top.setActive(false);
		bottom.setActive(true);
		results[1] = g.isActive();
		
		top.setActive(true);
		bottom.setActive(false);
		results[2] = g.isActive();
		
		top.setActive(true);
		bottom.setActive(true);
		results[3] = g.isActive();
		
		return results;
	}
	
	// Test for larger nested gate network
	static void NestedGateTest() {
		AND childGateTop = new AND(top, bottom);
		NOR childGateBottom = new NOR(top, bottom);
		
		OR topGate = new OR(childGateTop, childGateBottom);
		
		boolean[] results = runTest(topGate);
		boolean[] expected = {true, false, false, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for adding a child gate to an input
	static void InputChildTest() {
		boolean failed = true;
		
		try {
			top.addChildGate(new AND(null, null), Gate.childPosition.POS_TOP);
		}
		catch (InvalidNodeException e){
			failed = false;
		}
		
		if (failed) {
			Thread.dumpStack();
		}
	}
	
	// Test for adding a second child to an inverter
	static void NOTSecondChildTest() {
		NOT gate = new NOT(top, null);
		boolean failed = true;
		
		try {
			gate.addChildGate(bottom, Gate.childPosition.POS_BOTTOM);
		}
		catch (InvalidNodeException e){
			failed = false;
		}
		
		if (failed) {
			Thread.dumpStack();
		}
	}
	
	// Test for adding a third child to a gate
	static void GateThirdChildTest() {
		AND gate = new AND(top, bottom);
		boolean failed = true;
		
		try {
			gate.addChildGate(new OR(null, null), Gate.childPosition.POS_BOTTOM);
		}
		catch (InvalidNodeException e){
			failed = false;
		}
		
		if (failed) {
			Thread.dumpStack();
		}
	}
	
	// Test solving all instances of circuit
	static void SolveAllTest() {
		Circuit circuit = new Circuit();
		AND andgate = new AND(top, bottom);
		Input C = new Input('C');
		Input D = new Input('D');
		XOR xorgate = new XOR(C, D);
		circuit.setTop(new OR(andgate, xorgate));
		circuit.inputs = new ArrayList<Input>();
		circuit.inputs.add(top);
		circuit.inputs.add(bottom);
		circuit.inputs.add(C);
		circuit.inputs.add(D);
		
		circuit.giveSolver();
		boolean[] results = circuit.solver.solveAll();
		
		// Expected: true when C or D but not both are high
		// Also true when both A and B are high, which is the 4th row
		boolean[] expected = {false, true, true, false,
							false, true, true, false,
							false, true, true, false,
							true, true, true, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
}
