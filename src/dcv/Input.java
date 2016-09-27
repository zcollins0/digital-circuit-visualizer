package dcv;

// Class to implement an input. Should not have any children.
// The value of the input is explicitly set when solving the circuit.
public class Input extends Gate {
	
	// The name of the input (A, B, C...)
	char tag;
	
	public Input(char tag) {
		super(null, null);
		this.tag = tag;
	}
	
	private boolean signal;
	
	@Override
	public boolean isActive() {
		return signal;
	}
	
	@Override
	public void addChildGate(Gate g, childPosition pos)  throws InvalidNodeException {
		throw new InvalidNodeException("Cannot give child to input.");
	}
	
	public void setActive(boolean active) {
		signal = active;
	}

}
