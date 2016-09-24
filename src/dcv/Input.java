package dcv;

// Class to implement an input. Should not have any children.
// The value of the input is explicitly set when solving the circuit.
public class Input extends Gate {
	
	// The name of the input (A, B, C...)
	char tag;
	
	Input(Gate top, Gate bottom, char tag) {
		super(top, bottom);
		top = null;
		bottom = null;
		this.tag = tag;
	}

	private boolean signal;
	
	@Override
	boolean isActive() {
		return signal;
	}
	
	@Override
	void addChildGate(Gate g, childPosition pos)  throws InvalidNodeException {
		throw new InvalidNodeException("Cannot give child to input.");
	}
	
	void setActive(boolean active) {
		signal = active;
	}

}
