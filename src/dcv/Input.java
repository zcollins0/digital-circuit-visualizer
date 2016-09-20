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
	
	void setActive(boolean active) {
		signal = active;
	}

}
