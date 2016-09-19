package dcv;

class NAND extends AND {
	
	NAND(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return !(super.isActive());
	}
}
