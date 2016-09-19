package dcv;

class AND extends Gate {
	
	AND(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild.isActive() && bottomChild.isActive());
	}
}
