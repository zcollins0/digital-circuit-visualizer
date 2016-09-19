package dcv;

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

}
