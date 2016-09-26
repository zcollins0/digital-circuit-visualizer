package dcvUnitTests;

import dcv.*;
import java.util.Arrays;

public class UnitTests {
	
	static dcv.Circuit circuit;
	static Input top = new Input(null, null, 'A');
	static Input bottom = new Input(null, null, 'B');

	public static void main(String[] args) {
		// TODO: Write all unit tests
		
		// One unit test for solveAll()
		// One for making sure exceptions are thrown when necessary
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
		
	}
	
	// Test for adding a child gate to an input
	static void InputChildTest() {
		boolean failed = true;
		try {
			top.addChildGate(new AND(null, null), Gate.childPosition.POS_TOP);
		}
		catch (dcv.InvalidNodeException e){
			failed = false;
		}
		
		if (failed) {
			Thread.dumpStack();
		}
	}
	
	// Test for adding a second child to an inverter
	static void NOTSecondChildTest() {
		
	}
	
	// Test for adding a third child to a gate
	static void GateThirdChildTest() {
		
	}
}
