package dcvUnitTests;

import dcv.*;

public class UnitTests {
	
	static dcv.Circuit circuit;
	static Input top = new Input(null, null, 'A');
	static Input bottom = new Input(null, null, 'B');

	public static void main(String[] args) {
		// TODO: Write all unit tests
		
		// Should have one unit test for each type of gate.
		// One unit test for solveAll()
		// One for making sure exceptions work correctly
		circuit = new dcv.Circuit();
		ANDTest();
		ORTest();
		NANDTest();
		NORTest();
		NOTTest();
		XORTest();
	}
	
	static void ANDTest() {
		AND gate = new AND(top, bottom);
	}
	
	static void ORTest() {
		OR gate = new OR(top, bottom);
	}
	
	static void NANDTest() {
		NAND gate = new NAND(top, bottom);
	}
	
	static void NORTest() {
		NOR gate = new NOR(top, bottom);
	}
	
	static void NOTTest() {
		NOT gate = new NOT(top, null);
		top.setActive(true);
		if (gate.isActive()) {
			// Failed
		}
		top.setActive(false);
		if (!gate.isActive()) {
			// Failed
		}
	}
	
	static void XORTest() {
		XOR gate = new XOR(top, bottom);
	}
}
