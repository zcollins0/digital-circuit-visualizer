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
		// One for making sure exceptions work correctly
		circuit = new dcv.Circuit();
		ANDTest();
		ORTest();
		NANDTest();
		NORTest();
		XORTest();
		NOTTest();
	}
	
	static void ANDTest() {
		AND gate = new AND(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, false, false, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	static void ORTest() {
		OR gate = new OR(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, true, true, true};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	static void NANDTest() {
		NAND gate = new NAND(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {true, true, true, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	static void NORTest() {
		NOR gate = new NOR(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {true, false, false, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
	static void XORTest() {
		XOR gate = new XOR(top, bottom);
		boolean[] results = runTest(gate);
		boolean[] expected = {false, true, true, false};
		if (!Arrays.equals(results, expected)) {
			Thread.dumpStack();
		}
	}
	
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
}
