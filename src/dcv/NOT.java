package dcv;

// 1-input inverter. Should only have one child.
class NOT extends Gate {

	NOT(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return (topChild != null) ? !topChild.isActive() : !bottomChild.isActive();
	}
	
	@Override
	void addChild(Gate g, childPosition pos) {
		// Can only give one input to NOT
	}

}
