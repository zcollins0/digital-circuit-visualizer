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
		AND gate = new AND(null, null);
	}
	
	static void ORTest() {
		
	}
	
	static void NANDTest() {
		
	}
	
	static void NORTest() {
		
	}
	
	static void NOTTest() {
		
	}
	
	static void XORTest() {
		
	}
}
