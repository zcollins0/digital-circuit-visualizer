package dcv;

class NOT extends Gate {

	NOT(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild != null) ? !topChild.isActive() : !bottomChild.isActive();
	}

}
