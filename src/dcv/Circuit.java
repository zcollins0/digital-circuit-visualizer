package dcv;

// Class to represent a circuit. The UI can own multiple circuit instances.
class Circuit {
	// Notes to self:
	// Best way to do this: make the circuit modular
	// Circuit owns a binary/ternary tree of circuit elements
	// Circuit structure has one parent gate, can add capability for multiple parents later
	// Probably best if this is a doubly linked tree

	Gate top;
	
	boolean isActive() {
		return top.isActive();
	}
}
