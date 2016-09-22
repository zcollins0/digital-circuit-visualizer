package dcv;

// Two input NAND gate - active when no or one input is active
class NAND extends AND {
	
	NAND(Gate top, Gate bottom) {
		super(top, bottom);
	}

	@Override
	boolean isActive() {
		return !(super.isActive());
	}
}
