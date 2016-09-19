package dcv;

class OR extends Gate {

	OR(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild.isActive() || bottomChild.isActive());
	}

}
