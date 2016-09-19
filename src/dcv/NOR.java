package dcv;

class NOR extends OR {
	
	NOR(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return !(super.isActive());
	}
}
