package dcvUnitTests;

import dcv.*;

import java.util.ArrayList;
import java.util.Arrays;

public class UnitTests {
	
	static dcv.Circuit circuit;
	static Input A = new Input('A');
	static Input B = new Input('B');

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
		AND gate = new AND(A, B);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, false, false, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for OR Gate
	static void ORTest() {
		OR gate = new OR(A, B);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, true, true, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for NAND Gate
	static void NANDTest() {
		NAND gate = new NAND(A, B);
		boolean[] results = runTest(gate);
		boolean[] expected = {true, true, true, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for NOR Gate
	static void NORTest() {
		NOR gate = new NOR(A, B);
		boolean[] results = runTest(gate);
		boolean[] expected = {true, false, false, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for XOR Gate
	static void XORTest() {
		XOR gate = new XOR(A, B);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, true, true, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	// Test for NOT Gate (inverter)
	static void NOTTest() {
		NOT gate = new NOT(A);
		A.setActive(true);
		if (gate.isActive()) {
			Thread.dumpStack();
		}
		A.setActive(false);
		if (!gate.isActive()) {
			Thread.dumpStack();
		}
	}
	
	// Runs a test on a 2-input gate
	static boolean[] runTest(Gate g) {
		boolean[] results = new boolean[4];
		
		A.setActive(false);
		B.setActive(false);
		results[0] = g.isActive();
		
		A.setActive(false);
		B.setActive(true);
		results[1] = g.isActive();
		
		A.setActive(true);
		B.setActive(false);
		results[2] = g.isActive();
		
		A.setActive(true);
		B.setActive(true);
		results[3] = g.isActive();
		
		return results;
	}
	
	// Test for larger nested gate network
	static void NestedGateTest() {
		AND childGateTop = new AND(A, B);
		NOR childGateBottom = new NOR(A, B);
		
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
			A.addChildGate(new AND(null, null));
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
		NOT gate = new NOT(A);
		boolean failed = true;
		
		try {
			gate.addChildGate(B);
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
		AND gate = new AND(A, B);
		boolean failed = true;
		
		try {
			gate.addChildGate(new OR(null, null));
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
		AND andgate = new AND(A, B);
		Input C = new Input('C');
		Input D = new Input('D');
		XOR xorgate = new XOR(C, D);
		circuit.setTop(new OR(andgate, xorgate));
		circuit.inputs = new ArrayList<Input>();
		circuit.inputs.add(A);
		circuit.inputs.add(B);
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
