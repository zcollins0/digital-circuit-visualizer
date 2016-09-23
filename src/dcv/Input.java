package dcv;

// Class to implement an input. Should not have any children.
class Input extends Gate {
	
	// The name of the input (A, B, C...)
	String tag;
	
	Input(Gate top, Gate bottom, String tag) {
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
	void addChild(Gate g, childPosition pos)  throws InvalidNodeException {
		throw new InvalidNodeException("Cannot give child to input.");
	}
	
	void setActive(boolean active) {
		signal = active;
	}

}
