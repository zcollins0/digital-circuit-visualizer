package dcv;

// Class to implement an input. Should not have any children.
class Input extends Gate {
	
	Input(Gate top, Gate bottom) {
		super(top, bottom);
		top = null;
		bottom = null;
	}

	boolean signal;
	
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
